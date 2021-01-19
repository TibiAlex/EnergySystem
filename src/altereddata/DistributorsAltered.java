package altereddata;

import strategies.EnergyChoiceStrategyType;

import java.util.ArrayList;

public class DistributorsAltered {

    private int id;
    private int contractLength;
    private int initialBudget;
    private int initialInfrastructureCost;
    private int energyNeededKW;
    private EnergyChoiceStrategyType producerStrategy;
    private double productionCost;
    private int enoughQuantity;
    private int contractPrice;
    private ArrayList<ConsumersAltered> consumers;
    private boolean isBankrupt;
    private long costProducator;

    public DistributorsAltered() { }

    public DistributorsAltered(final int id, final int contractLength,
                               final int initialBudget, final int initialInfrastructureCost,
                               final int energyNeededKW,
                               final EnergyChoiceStrategyType producerStrategy) {
        this.id = id;
        this.contractLength = contractLength;
        this.initialBudget = initialBudget;
        this.initialInfrastructureCost = initialInfrastructureCost;
        this.energyNeededKW = energyNeededKW;
        this.producerStrategy = producerStrategy;
        this.productionCost = 0;
        this.contractPrice = 0;
        this.consumers = new ArrayList<>();
        this.isBankrupt = false;
        this.enoughQuantity = 0;
        this.costProducator = 0;
    }

    public final int getId() {
        return id;
    }

    public final void setId(final int id) {
        this.id = id;
    }

    public final int getContractLength() {
        return contractLength;
    }

    public final void setContractLength(final int contractLength) {
        this.contractLength = contractLength;
    }

    public final int getInitialBudget() {
        return initialBudget;
    }

    public final void setInitialBudget(final int initialBudget) {
        this.initialBudget = initialBudget;
    }

    public final int getInitialInfrastructureCost() {
        return initialInfrastructureCost;
    }

    public final void setInitialInfrastructureCost(final int initialInfrastructureCost) {
        this.initialInfrastructureCost = initialInfrastructureCost;
    }

    public final int getEnergyNeededKW() {
        return energyNeededKW;
    }

    public final void setEnergyNeededKW(final int energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public final EnergyChoiceStrategyType getProducerStrategy() {
        return producerStrategy;
    }

    public final void setProducerStrategy(final EnergyChoiceStrategyType producerStrategy) {
        this.producerStrategy = producerStrategy;
    }

    public final double getProductionCost() {
        return productionCost;
    }

    public final void setProductionCost(final double productionCost) {
        this.productionCost = productionCost;
    }

    public final int getContractPrice() {
        return contractPrice;
    }

    public final void setContractPrice(final int contractPrice) {
        this.contractPrice = contractPrice;
    }

    public final ArrayList<ConsumersAltered> getConsumers() {
        return consumers;
    }

    public final void setConsumers(final ArrayList<ConsumersAltered> consumers) {
        this.consumers = consumers;
    }

    public final boolean isBankrupt() {
        return isBankrupt;
    }

    public final void setBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public final int getEnoughQuantity() {
        return enoughQuantity;
    }

    public final void setEnoughQuantity(final int enoughQuantity) {
        this.enoughQuantity = enoughQuantity;
    }

    public final long getCostProducator() {
        return costProducator;
    }

    public final void setCostProducator(final long costProducator) {
        this.costProducator = costProducator;
    }

    @Override
    public String toString() {
        return "DistributorsAltered{"
                +
                "id="
                +
                id
                +
                ", contractLength="
                +
                contractLength
                +
                ", initialBudget="
                +
                initialBudget
                +
                ", initialInfrastructureCost="
                +
                initialInfrastructureCost
                +
                ", energyNeededKW="
                +
                energyNeededKW
                +
                ", producerStrategy="
                +
                producerStrategy
                +
                ", productionCost="
                +
                productionCost
                +
                ", enoughQuantity="
                +
                enoughQuantity
                +
                ", contractPrice="
                +
                contractPrice
                +
                ", consumers="
                +
                consumers
                +
                ", isBankrupt="
                +
                isBankrupt
                +
                ", costProducator="
                +
                costProducator
                +
                '}';
    }

    /**
     * metoda ce stabileste tipul de strategie
     * @param producersAltereds arraylist cu toti producatorii
     */
    public void strategy(ArrayList<ProducersAltered> producersAltereds) {
        if (producerStrategy.equals(EnergyChoiceStrategyType.GREEN)) {
            strategyGreen(producersAltereds);
        }

        if (producerStrategy.equals(EnergyChoiceStrategyType.PRICE)) {
            strategyPrice(producersAltereds);
        }

        if (producerStrategy.equals(EnergyChoiceStrategyType.QUANTITY)) {
            strategyQuantiy(producersAltereds);
        }
    }

    /**
     * metoda ce scade din energia primita
     * energia unui prod schimbat
     * @param value valoarea primita de la prod respectiv
     */
    public void scadere(int value, double price) {
        enoughQuantity -= value;
        productionCost -= value * price;
    }

    /**
     * metoda ce calculeaza bugetul distribuitorului
     * la finalul lunii
     */
    public void calculateBudget() {
        initialBudget -= (initialInfrastructureCost
                + costProducator * consumers.size());
        if (this.initialBudget < 0) {
            this.isBankrupt = true;
        }
    }

    /**
     * metoda ce adauga banii platiti de consumatori lunar
     * bugetului distribuitorului
     * @param value valoarea platita de un consumator
     */
    public void addConsumerMoney(int value) {
        initialBudget += value;
    }

    /**
     * metoda pentru calcularea costului
     * contractului pentru consumator
     */
    public void calculareContractCost() {
        double constanta = 0.2;
        long profit = Math.round(Math.floor(constanta * costProducator));
        if (consumers.size() == 0) {
            contractPrice = (int) (initialInfrastructureCost + costProducator + profit);
        } else {
            contractPrice = (int) Math.round(Math.floor(
                    initialInfrastructureCost / consumers.size()) + profit + costProducator);
        }
    }

    /**
     * metoda pentru a calcula costul de productie pentru
     * un distribuitor dupa ce acesta isi alege producatorii
     */
    public void calculareProdCost() {
        costProducator = Math.round(Math.floor(productionCost / 10));

    }

    /**
     * metoda pentru a calcula producatorii pentru
     * un distribuitor cu strategia Green
     * @param producersAltereds arraylist cu producatorii
     */
    public void strategyGreen(ArrayList<ProducersAltered> producersAltereds) {
        while (compare()) {
            double minPrice = 110000;
            int indice = 0;
            int maxQuantity = 0;
            for (int i = 0; i < producersAltereds.size(); i++) {
                if (producersAltereds.get(i).getMaxDistributors()
                        > producersAltereds.get(i).getDistrbutorID().size()) {
                    if (!producersAltereds.get(i).containsDistributor(id)) {
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
            if (minPrice != 110000) {
                addEnergy(producersAltereds.get(indice).getEnergyPerDistributor());
                addPrice(producersAltereds.get(indice).calculateCost());
                producersAltereds.get(indice).getDistrbutorID().add(id);
            } else {
                strategyPrice(producersAltereds);
            }
        }
    }

    /**
     * metoda pentru a calcula producatorii pentru
     * un distribuitor cu strategia Price
     * @param producersAltereds arraylist cu producatorii
     */
    public void strategyPrice(ArrayList<ProducersAltered> producersAltereds) {
        while (compare()) {
            double min = 1000;
            int indice = 0;
            for (int j = 0; j < producersAltereds.size(); j++) {
                if (producersAltereds.get(j).getMaxDistributors()
                        > producersAltereds.get(j).getDistrbutorID().size()) {
                    if (!producersAltereds.get(j).containsDistributor(id)) {
                        if (producersAltereds.get(j).getPriceKW() < min) {
                            min = producersAltereds.get(j).getPriceKW();
                            indice = j;
                        }
                    }
                }
            }
            addEnergy(producersAltereds.get(indice).getEnergyPerDistributor());
            addPrice(producersAltereds.get(indice).calculateCost());
            producersAltereds.get(indice).getDistrbutorID().add(id);
        }
        if (compare()) {
            strategyQuantiy(producersAltereds);
        }
    }

    /**
     * metoda ce calculeaza producatorii pentru un distribuitor
     * cu strategia Quantity
     * @param producersAltereds arraylist cu toti producatorii
     */
    public void strategyQuantiy(ArrayList<ProducersAltered> producersAltereds) {
        while (compare()) {
            int max = 0;
            int indice = 0;
            for (int j = 0; j < producersAltereds.size(); j++) {
                if (producersAltereds.get(j).getMaxDistributors()
                        > producersAltereds.get(j).getDistrbutorID().size()) {
                    if (!producersAltereds.get(j).containsDistributor(id)) {
                        if (max < producersAltereds.get(j).getEnergyPerDistributor()) {
                            max = producersAltereds.get(j).getEnergyPerDistributor();
                            indice = j;
                        }
                    }
                }
            }
            addEnergy(producersAltereds.get(indice).getEnergyPerDistributor());
            addPrice(producersAltereds.get(indice).calculateCost());
            producersAltereds.get(indice).getDistrbutorID().add(id);
        }
    }

    /**
     * metoda pentru a verifica daca s-a atins cantitatea de energie minima
     * @return returneaza daca mai este nevoie de energie
     */
    public boolean compare() {
        return enoughQuantity < energyNeededKW;
    }

    /**
     * metoda ce calculeaza costul total pentru un distribuitor
     * @param cost costul unui producator
     */
    public void addPrice(double cost) {
        productionCost += cost;
    }

    /**
     * metoda pentru a verifica in permanenta dac am atins
     * cantitatea ceruta de energie
     * @param value valoarea ce trebuie adaugata
     */
    public void addEnergy(int value) {
        enoughQuantity += value;
    }
}
