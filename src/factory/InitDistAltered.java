package factory;

import altereddata.DistributorsAltered;
import inputdata.Distributors;
import inputdata.Input;

import java.util.ArrayList;

public class InitDistAltered implements Factory {

    /**
     * metoda care copiaza datele de intrare pentru a fi editate
     * @param obj variabila ce contine toate datele de intrare
     * @return se returneaza arraylistul cu toti distribuitorii de prelucrat
     */
    @Override
    public Object initialise(Object obj) {
        Input input = (Input) obj;
        ArrayList<Distributors> distributors = input.getInitialData().getDistributors();
        ArrayList<DistributorsAltered> distributorsAltereds = new ArrayList<>();
        for (Distributors distributor : distributors) {
            DistributorsAltered distributorsAltered =
                    new DistributorsAltered(distributor.getId(),
                            distributor.getContractLength(),
                            distributor.getInitialBudget(),
                            distributor.getInitialInfrastructureCost(),
                            distributor.getEnergyNeededKW(),
                            distributor.getProducerStrategy());
            distributorsAltereds.add(distributorsAltered);
        }
        return distributorsAltereds;
    }
}
