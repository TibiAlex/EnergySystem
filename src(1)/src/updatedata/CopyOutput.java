package updatedata;

import altereddata.ConsumersAltered;
import altereddata.DistributorsAltered;
import altereddata.ProducersAltered;
import outputdata.ConsumersOutput;
import outputdata.Contract;
import outputdata.DistributorsOutput;
import outputdata.ProducersOutput;

import java.util.ArrayList;

public class CopyOutput {
    /**
     * Metoda ce copiaza consumatorii prelucrati intr-un
     * vector ce poate fi afisat
     * @param consumersAltereds consuatorii prelucrati
     * @return returnam consumatorii de afisat
     */
    public ArrayList<ConsumersOutput> copyConsumersOutput(
            ArrayList<ConsumersAltered> consumersAltereds) {
        ArrayList<ConsumersOutput> consumersOutputs = new ArrayList<>();
        for (ConsumersAltered consumersAltered : consumersAltereds) {
            ConsumersOutput consumersOutput = new ConsumersOutput(
                    consumersAltered.getId(),
                    consumersAltered.isBankrupt(),
                    consumersAltered.getInitialBudget());
            consumersOutputs.add(consumersOutput);
        }
        return consumersOutputs;
    }

    /**
     * Metoda ce copiaza distribuitorii prelucrati intr-un
     * vector ce poate fi afisat
     * @param distributorsAltereds distributorii prelucrati
     * @return returnam distribuitorii de afisat
     */
    public ArrayList<DistributorsOutput> copyDistributorsOutput(
            ArrayList<DistributorsAltered> distributorsAltereds) {
        ArrayList<DistributorsOutput> distributorsOutputs = new ArrayList<>();
        for (DistributorsAltered distributorsAltered : distributorsAltereds) {
            ArrayList<Contract> contracts = new ArrayList<>();
            for (int j = 0; j < distributorsAltered.getConsumers().size(); j++) {
                Contract contract = new Contract(
                        distributorsAltered.getConsumers().get(j).getId(),
                        distributorsAltered.getConsumers().get(j).getActualDistributorPrice(),
                        distributorsAltered.getConsumers().get(j).getMonthsLeft());
                contracts.add(contract);
            }
            DistributorsOutput distributorsOutput = new DistributorsOutput(
                    distributorsAltered.getId(),
                    distributorsAltered.getEnergyNeededKW(),
                    distributorsAltered.getContractPrice(),
                    distributorsAltered.getInitialBudget(),
                    distributorsAltered.getProducerStrategy(),
                    distributorsAltered.isBankrupt(),
                    contracts);
            distributorsOutputs.add(distributorsOutput);
        }
        return distributorsOutputs;
    }

    /**
     * Metoda ce copiaza producatorii prelucrati intr-un vector
     * ce poate fi afisat
     * @param producersAltereds producatorii prelucratii
     * @return returnam producatorii de afisat
     */
    public ArrayList<ProducersOutput> copyProducersOutput(
            ArrayList<ProducersAltered> producersAltereds) {
        ArrayList<ProducersOutput> producersOutputs = new ArrayList<>();
        for (ProducersAltered producersAltered : producersAltereds) {
            ProducersOutput producersOutput = new ProducersOutput(
                    producersAltered.getId(),
                    producersAltered.getMaxDistributors(),
                    producersAltered.getPriceKW(),
                    producersAltered.getEnergyType(),
                    producersAltered.getEnergyPerDistributor(),
                    producersAltered.getMonthlyStats());
            producersOutputs.add(producersOutput);
        }
        return producersOutputs;
    }
}
