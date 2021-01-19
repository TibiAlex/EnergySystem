package altereddata;

import observer.IObserver;
import strategies.EnergyChoiceStrategyType;
import strategy.Context;
import strategy.StrategyGreen;
import strategy.StrategyPrice;
import strategy.StrategyQuantity;

import java.util.ArrayList;

public class DistributorsAltered implements IObserver {

    private int id;
    private int contractLength;
    private int initialBudget;
    private int initialInfrastructureCost;
    private int energyNeededKW;
    private EnergyChoiceStrategyType producerStrategy;
    private long costProducator;
    private int enoughQuantity;
    private double productionCost;
    private int contractPrice;
    private final double constanta = 0.2;
    private final int zece = 10;
    private ArrayList<ConsumersAltered> consumers;
    private boolean isBankrupt;

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

    public final int getContractPrice() {
        return contractPrice;
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

    public final int getEnoughQuantity() {
        return enoughQuantity;
    }

    /**
     * metoda ce stabileste tipul de strategie
     * @param producersAltereds arraylist cu toti producatorii
     */
    public void strategy(ArrayList<ProducersAltered> producersAltereds) {
        if (producerStrategy.equals(EnergyChoiceStrategyType.GREEN)) {
            Context context = new Context(new StrategyGreen());
            context.executeStrategy(this, producersAltereds);
        }

        if (producerStrategy.equals(EnergyChoiceStrategyType.PRICE)) {
            Context context = new Context(new StrategyPrice());
            context.executeStrategy(this, producersAltereds);
        }

        if (producerStrategy.equals(EnergyChoiceStrategyType.QUANTITY)) {
            Context context = new Context(new StrategyQuantity());
            context.executeStrategy(this, producersAltereds);
        }
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
        costProducator = Math.round(Math.floor(productionCost / zece));

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

    /**
     * metoda updateaza nevoia de enegie
     * in functie de schimbarile din luna respectiva
     * @param old cantitatea de energie veche
     * @param price pretul
     */
    @Override
    public void update(int old, double price) {
        enoughQuantity -= old;
        productionCost -= old * price;
    }
}
