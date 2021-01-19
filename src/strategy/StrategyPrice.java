package strategy;

import altereddata.DistributorsAltered;
import altereddata.ProducersAltered;

import java.util.ArrayList;

public class StrategyPrice implements IStrategy {

    private final double constanta = 110;

    /**
     * metoda ce va cauta producatori pentru distrubuitorii
     * cu strategia Price
     * @param d distribuitorul ce cauta producatori
     * @param producersAltereds lista de producatori in care se va cauta
     */
    @Override
    public void strategy(DistributorsAltered d, ArrayList<ProducersAltered> producersAltereds) {
        while (d.compare()) {
            double min = constanta;
            int indice = 0;
            int maxQuantity = 0;
            for (int j = 0; j < producersAltereds.size(); j++) {
                if (producersAltereds.get(j).getMaxDistributors()
                        > producersAltereds.get(j).getDistributors().size()) {
                    if (!producersAltereds.get(j).containsDistributor(d)) {
                        if (producersAltereds.get(j).getPriceKW() < min) {
                            min = producersAltereds.get(j).getPriceKW();
                            indice = j;
                            maxQuantity = producersAltereds.get(j).getEnergyPerDistributor();
                        }
                        if (producersAltereds.get(j).getPriceKW() ==  min) {
                            if (producersAltereds.get(j).getEnergyPerDistributor() > maxQuantity) {
                                indice = j;
                                maxQuantity = producersAltereds.get(j).getEnergyPerDistributor();
                            }
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
