import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import factory.Factory;
import factory.FactoryType;
import inputdata.Input;
import inputdata.MonthlyUpdate;
import outputdata.ConsumersOutput;
import outputdata.DistributorsOutput;
import outputdata.Output;
import altereddata.ConsumersAltered;
import altereddata.DistributorsAltered;
import altereddata.ProducersAltered;
import outputdata.ProducersOutput;
import updatedata.SetChanges;

/**
 * Entry point to the simulation
 */
public final class Main {

    private Main() { }

    /**
     * Main function which reads the input file and starts simulation
     *
     * @param args input and output files
     * @throws Exception might error when reading/writing/opening files, parsing JSON
     */
    public static void main(final String[] args) throws Exception {

        //citim datele din fisierele json
        ObjectMapper obMap = new ObjectMapper();
        Input input = obMap.readValue(new File(args[0]), Input.class);

        SetChanges setChanges = SetChanges.getInstance();
        Method m = Method.getInstance();
        FactoryType factoryType = new FactoryType();

        //Copiem datele de intrare in 3 arraylisturi pentru a fi mai usor de prelucrat
        Factory dist = factoryType.typeOfVariables("Dist");
        ArrayList<DistributorsAltered> distributorsAltereds =
                (ArrayList<DistributorsAltered>) dist.initialise(input);

        Factory cons  = factoryType.typeOfVariables("Cons");
        ArrayList<ConsumersAltered> consumersAltereds =
                (ArrayList<ConsumersAltered>) cons.initialise(input);

        Factory prod = factoryType.typeOfVariables("Prod");
        ArrayList<ProducersAltered> producersAltereds =
                (ArrayList<ProducersAltered>) prod.initialise(input);

        //setarea producatorilor pentru distribuitori in functie de strategie
        distributorsAltereds.forEach(d -> d.strategy(producersAltereds));

        //calcularea indicelui celui mai ieftin contract si calcularea preturilor distribuitorilor
        int indice = m.minimID(distributorsAltereds);

        //legarea tuturor consumatorilor inainte de prima luna la distribuitorul cel mai ieftin
        distributorsAltereds.get(indice).getConsumers().addAll(consumersAltereds);
        for (ConsumersAltered consumersAltered : consumersAltereds) {
            consumersAltered.setDetails(distributorsAltereds.get(indice));
        }

        // consumatorii si distributorii isi recalculeaza bugetul
        consumersAltereds.forEach(ConsumersAltered::calculateBudget);
        distributorsAltereds.forEach(DistributorsAltered::calculateBudget);

        // arraylist pentru schimbarile lunare
        ArrayList<MonthlyUpdate> monthlyUpdates = input.getMonthlyUpdates();

        /*
          incepem simularea de numberOfTurns ori
          in care consumatorii, distributorii si producatorii
          primesc modificari si necesita recalculari in permanenta
         */
        for (int k = 0; k < input.getNumberOfTurns(); k++) {

            // schimbarile pentru distribuitori si recalcularea pretului minim
            setChanges.setDistributorChanges(distributorsAltereds, monthlyUpdates, k);
            indice = m.minimID(distributorsAltereds);

            // adaugam noii consumatori in arraylist
            setChanges.setConsumerChanges(consumersAltereds, monthlyUpdates, k);

            for (ConsumersAltered altered : consumersAltereds) {
                //Consumatorii isi aleg un nou distribuitor daca este nevoie
                altered.chooseDist(distributorsAltereds.get(indice));
                //Consumatorii platesc
                altered.calculateBudget();
                //Consumatorii care sunt bankrupt sunt scosi de la distributori
                if (altered.isBankrupt()) {
                      altered.getActualDistributor().getConsumers().remove(altered);
                }
            }

            //distributorii platesc
            distributorsAltereds.forEach(DistributorsAltered::calculateBudget);

            // scoatem distributorii care au dat faliment de la producatori
            for (ProducersAltered producersAltered : producersAltereds) {
                for (int j = 0; j < producersAltered.getDistributors().size(); j++) {
                    if (producersAltered.getDistributors().get(j).isBankrupt()) {
                        producersAltered.remove(producersAltered.getDistributors().get(j));
                    }
                }
            }

            // producatorii isi reactualizeaza valorile
            // scoatem dist de la producatorii care s-au schimbat
            setChanges.setProducerChanges(producersAltereds, monthlyUpdates, k);

            // le gasim dist un nou producator
            for (DistributorsAltered distributorsAltered : distributorsAltereds) {
                if (distributorsAltered.getEnergyNeededKW()
                        > distributorsAltered.getEnoughQuantity()) {
                    distributorsAltered.strategy(producersAltereds);
                }
            }

            // updatam distributorii lunari ai producatorilor
            for (ProducersAltered producersAltered : producersAltereds) {
                producersAltered.setMonthsInfo(k + 1);
            }
            producersAltereds.forEach(p -> p.setOldEnergyPerDistributor(0));

            // verificam daca jocul mai continua
            AtomicInteger c = new AtomicInteger();
            distributorsAltereds.forEach(d -> {
                if (!d.isBankrupt()) {
                    c.set(1);
                }
            });
            if (c.get() == 0) {
                break;
            }
        }

        //Copiem datele din cele 3 arraylist-uri in alte 3 arraylisturi pentru a putea fi afisate
        Factory consOut = factoryType.typeOfVariables("ConsOut");
        ArrayList<ConsumersOutput> consumersOutputs =
                (ArrayList<ConsumersOutput>) consOut.initialise(consumersAltereds);

        Factory distOut = factoryType.typeOfVariables("DistOut");
        ArrayList<DistributorsOutput> distributorsOutputs =
                (ArrayList<DistributorsOutput>) distOut.initialise(distributorsAltereds);

        Factory prodOut = factoryType.typeOfVariables("ProdOut");
        ArrayList<ProducersOutput> producersOutputs =
                (ArrayList<ProducersOutput>) prodOut.initialise(producersAltereds);

        // Output
        Output output = new Output(consumersOutputs, distributorsOutputs, producersOutputs);
        obMap.writerWithDefaultPrettyPrinter().writeValue(new File(args[1]), output);
    }
}
