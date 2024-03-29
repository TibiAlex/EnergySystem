package inputdata;

import strategies.EnergyChoiceStrategyType;

public class Distributors {

    private int  id;
    private int contractLength;
    private int initialBudget;
    private int initialInfrastructureCost;
    private int energyNeededKW;
    private EnergyChoiceStrategyType producerStrategy;

    public final int getId() {
        return id;
    }

    public final int getContractLength() {
        return contractLength;
    }

    public final int getInitialBudget() {
        return initialBudget;
    }

    public final int getInitialInfrastructureCost() {
        return initialInfrastructureCost;
    }

    public final int getEnergyNeededKW() {
        return energyNeededKW;
    }

    public final EnergyChoiceStrategyType getProducerStrategy() {
        return producerStrategy;
    }

    public final void setId(final int id) {
        this.id = id;
    }

    public final void setContractLength(final int contractLength) {
        this.contractLength = contractLength;
    }

    public final void setInitialBudget(final int initialBudget) {
        this.initialBudget = initialBudget;
    }

    public final void setInitialInfrastructureCost(final int initialInfrastructureCost) {
        this.initialInfrastructureCost = initialInfrastructureCost;
    }

    public final void setEnergyNeededKW(final int energyNedeedKW) {
        this.energyNeededKW = energyNedeedKW;
    }

    public final void setProducerStrategy(final EnergyChoiceStrategyType producerStrategy) {
        this.producerStrategy = producerStrategy;
    }
}
