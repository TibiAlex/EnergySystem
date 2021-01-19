package inputdata;

import entities.EnergyType;

public class Producers {

    private int id;
    private EnergyType energyType;
    private int maxDistributors;
    private double priceKW;
    private int energyPerDistributor;

    public final int getId() {
        return id;
    }

    public final EnergyType getEnergyType() {
        return energyType;
    }

    public final int getMaxDistributors() {
        return maxDistributors;
    }

    public final double getPriceKW() {
        return priceKW;
    }

    public final int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public final void setId(final int id) {
        this.id = id;
    }

    public final void setEnergyType(final EnergyType energyType) {
        this.energyType = energyType;
    }

    public final void setMaxDistributors(final int maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    public final void setPriceKW(final double priceKW) {
        this.priceKW = priceKW;
    }

    public final void setEnergyPerDistributor(final int energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }
}
