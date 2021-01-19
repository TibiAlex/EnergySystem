import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;

import inputdata.Producers;
import inputdata.Consumers;
import inputdata.Distributors;
import inputdata.Input;
import inputdata.MonthlyUpdate;
import outputdata.Output;
import altereddata.ConsumersAltered;
import altereddata.DistributorsAltered;
import altereddata.ProducersAltered;
import updatedata.CopyInput;
import updatedata.CopyOutput;
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

        CopyInput copyInput = new CopyInput();
        SetChanges setChanges = new SetChanges();
        CopyOutput copyOutput = new CopyOutput();
        Method m = new Method();

        //arraylist cu distribuitorii, consumatorii si producatorii care urmeaza sa fie editate
        ArrayList<Distributors> distributors = input.getInitialData().getDistributors();
        ArrayList<DistributorsAltered> distributorsAltereds = new ArrayList<>();
        copyInput.copyDistributors(distributors, distributorsAltereds);

        ArrayList<Consumers> consumers = input.getInitialData().getConsumers();
        ArrayList<ConsumersAltered> consumersAltereds = new ArrayList<>();
        copyInput.copyConsumers(consumersAltereds, consumers);

        ArrayList<Producers> producers = input.getInitialData().getProducers();
        ArrayList<ProducersAltered> producersAltereds = new ArrayList<>();
        copyInput.copyProducers(producers, producersAltereds);

        //setarea producatorilor pentru distribuitori in functie de strategie
        distributorsAltereds.forEach(d -> {
            d.setEnoughQuantity(0);
            d.strategy(producersAltereds);
        });

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

            // producatorii isi reactualizeaza valorile
            setChanges.setProducerChanges(producersAltereds, monthlyUpdates, k);

            // scoatem distributorii care au dat faliment de la producatori
            for (ProducersAltered producersAltered : producersAltereds) {
                for (int j = 0; j < producersAltered.getDistrbutorID().size(); j++) {
                    int value = producersAltered.getDistrbutorID().get(j);
                    if (distributorsAltereds.get(value).isBankrupt()) {
                        producersAltered.getDistrbutorID().remove(
                                distributorsAltereds.get(value).getId());
                    }
                }
            }

            // scoatem dist de la producatorii care s-au schimbat
            for (ProducersAltered producersAltered : producersAltereds) {
                if (producersAltered.schimbat()
                        && producersAltered.getOldEnergyPerDistributor() != 0) {
                    producersAltered.schimbare(distributorsAltereds);
                }
            }


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
            int d = 0;
            for (DistributorsAltered distributorsAltered : distributorsAltereds) {
                if (distributorsAltered.isBankrupt()) {
                    d++;
                }
            }
            if (d == distributorsAltereds.size()) {
                break;
            }
        }

        // Output
        Output output = new Output(copyOutput.copyConsumersOutput(consumersAltereds),
                copyOutput.copyDistributorsOutput(distributorsAltereds),
                copyOutput.copyProducersOutput(producersAltereds));
        obMap.writerWithDefaultPrettyPrinter().writeValue(new File(args[1]), output);
    }
}
