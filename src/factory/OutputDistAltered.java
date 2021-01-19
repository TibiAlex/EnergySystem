package factory;

import altereddata.DistributorsAltered;
import outputdata.Contract;
import outputdata.DistributorsOutput;

import java.util.ArrayList;

public class OutputDistAltered implements Factory {

    /**
     * metoda ce initializeaza datele de iesire
     * @param obj obiectul trimis ca parametru
     * @return se returneaza distribuitorii de afisat
     */
    @Override
    public Object initialise(Object obj) {
        ArrayList<DistributorsAltered> distributorsAltereds = (ArrayList<DistributorsAltered>) obj;
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
}
