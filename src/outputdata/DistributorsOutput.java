package outputdata;

import strategies.EnergyChoiceStrategyType;

import java.util.ArrayList;

public class DistributorsOutput {

    private int id;
    private int energyNeededKW;
    private int contractCost;
    private int budget;
    private EnergyChoiceStrategyType producerStrategy;
    private boolean isBankrupt;
    private ArrayList<Contract> contracts = new ArrayList<>();

    public DistributorsOutput() { }

    public DistributorsOutput(final int id, final int energyNeededKW,
                              final int contractCost, final int budget,
                              final EnergyChoiceStrategyType producerStrategy,
                              final boolean isBankrupt, ArrayList<Contract> contracts) {
        this.id = id;
        this.energyNeededKW = energyNeededKW;
        this.contractCost = contractCost;
        this.budget = budget;
        this.producerStrategy = producerStrategy;
        this.isBankrupt = isBankrupt;
        this.contracts = contracts;
    }

    public final int getId() {
        return id;
    }

    public final void setId(final int id) {
        this.id = id;
    }

    public final int getEnergyNeededKW() {
        return energyNeededKW;
    }

    public final void setEnergyNeededKW(final int energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public final int getContractCost() {
        return contractCost;
    }

    public final void setContractCost(final int contractCost) {
        this.contractCost = contractCost;
    }

    public final int getBudget() {
        return budget;
    }

    public final void setBudget(final int budget) {
        this.budget = budget;
    }

    public final EnergyChoiceStrategyType getProducerStrategy() {
        return producerStrategy;
    }

    public final void setProducerStrategy(final EnergyChoiceStrategyType producerStrategy) {
        this.producerStrategy = producerStrategy;
    }

    public final boolean isIsBankrupt() {
        return isBankrupt;
    }

    public final ArrayList<Contract> getContracts() {
        return contracts;
    }

    public final void setContracts(final ArrayList<Contract> contracts) {
        this.contracts = contracts;
    }

    /**
     * metoda afisaza distribuitorii prelucrati
     * @return campurile afisate sunt id-ul distribuitorilor,
     * cantitatea de energie necesara, costul contractului,
     * strategia de alegere a producatorului , isBankrupt
     * si contractele ramase la finalul simularii
     */
    @Override
    public String toString() {
        return "DistributorsOutput{"
                +
                "id="
                +
                id
                +
                ", energyNeededKW="
                +
                energyNeededKW
                +
                ", contractCost="
                +
                contractCost
                +
                ", budget="
                +
                budget
                +
                ", producerStrategy="
                +
                producerStrategy
                +
                ", isBankrupt="
                +
                isBankrupt
                +
                ", contracts="
                +
                contracts
                +
                '}';
    }
}
