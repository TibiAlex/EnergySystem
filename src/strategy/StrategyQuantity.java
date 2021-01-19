package strategy;

import altereddata.DistributorsAltered;
import altereddata.ProducersAltered;

import java.util.ArrayList;

public class StrategyQuantity implements IStrategy {

    /**
     * metoda ce gaseste producatorii pentru un distributor
     * cu strategia Quantity
     * @param d distribuitorul ce cauta producatori
     * @param producersAltereds producatorii in care se va cauta
     */
    @Override
    public void strategy(DistributorsAltered d, ArrayList<ProducersAltered> producersAltereds) {
        while (d.compare()) {
            int max = 0;
            int indice = 0;
            for (int j = 0; j < producersAltereds.size(); j++) {
                if (producersAltereds.get(j).getMaxDistributors()
                        > producersAltereds.get(j).getDistributors().size()) {
                    if (!producersAltereds.get(j).containsDistributor(d)) {
                        if (max < producersAltereds.get(j).getEnergyPerDistributor()) {
                            max = producersAltereds.get(j).getEnergyPerDistributor();
                            indice = j;
                        }
                    }
                }
            }
            d.addEnergy(producersAltereds.get(indice).getEnergyPerDistributor());
            d.addPrice(producersAltereds.get(indice).calculateCost());
            producersAltereds.get(indice).add(d);
        }
    }
}
