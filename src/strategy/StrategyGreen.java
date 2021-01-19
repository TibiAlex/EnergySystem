package strategy;

import altereddata.DistributorsAltered;
import altereddata.ProducersAltered;

import java.util.ArrayList;

public class StrategyGreen implements IStrategy {

    private final double constanta = 110000;

    /**
     * metoda pentru gasirea de producatori pt dist
     * cu strategia GREEN
     * @param d dist ce are nevoie de producatori
     * @param producersAltereds lista de producatori in care se va cauta
     */
    @Override
    public void strategy(DistributorsAltered d,
                         ArrayList<ProducersAltered> producersAltereds) {
        while (d.compare()) {
            double minPrice = constanta;
            int indice = 0;
            int maxQuantity = 0;
            for (int i = 0; i < producersAltereds.size(); i++) {
                if (producersAltereds.get(i).getMaxDistributors()
                        > producersAltereds.get(i).getDistributors().size()) {
                    if (!producersAltereds.get(i).containsDistributor(d)) {
                        if (producersAltereds.get(i).getEnergyType().isRenewable()) {
                            if (producersAltereds.get(i).calculateCost() < minPrice) {
                                indice = i;
                                minPrice = producersAltereds.get(i).calculateCost();
                                maxQuantity = producersAltereds.get(i).
                                        getEnergyPerDistributor();
                            }
                            if (producersAltereds.get(i).calculateCost() == minPrice) {
                                if (maxQuantity < producersAltereds.get(i).
                                        getEnergyPerDistributor()) {
                                    indice = i;
                                    maxQuantity = producersAltereds.get(i).
                                            getEnergyPerDistributor();
                                }
                            }
                        }
                    }
                }
            }
            if (minPrice != constanta) {
                d.addEnergy(producersAltereds.get(indice).getEnergyPerDistributor());
                d.addPrice(producersAltereds.get(indice).calculateCost());
                producersAltereds.get(indice).add(d);
            } else {
                Context context = new Context(new StrategyPrice());
                context.executeStrategy(d, producersAltereds);
            }
        }
    }
}
