package outputdata;

import altereddata.MonthlyStats;
import entities.EnergyType;

import java.util.ArrayList;

public class ProducersOutput {

    private int id;
    private int maxDistributors;
    private double priceKW;
    private EnergyType energyType;
    private int energyPerDistributor;
    private ArrayList<MonthlyStats> monthlyStats;

    public ProducersOutput() { }

    public ProducersOutput(final int id, final int maxDistributors,
                           final double priceKW, final EnergyType energyType,
                           final int energyPerDistributor,
                           final ArrayList<MonthlyStats> monthlyStats) {
        this.id = id;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyType = energyType;
        this.energyPerDistributor = energyPerDistributor;
        this.monthlyStats = monthlyStats;
    }

    public final int getId() {
        return id;
    }

    public final void setId(final int id) {
        this.id = id;
    }

    public final int getMaxDistributors() {
        return maxDistributors;
    }

    public final void setMaxDistributors(final int maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    public final double getPriceKW() {
        return priceKW;
    }

    public final void setPriceKW(final double priceKW) {
        this.priceKW = priceKW;
    }

    public final EnergyType getEnergyType() {
        return energyType;
    }

    public final void setEnergyType(final EnergyType energyType) {
        this.energyType = energyType;
    }

    public final int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public final void setEnergyPerDistributor(final int energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    public final ArrayList<MonthlyStats> getMonthlyStats() {
        return monthlyStats;
    }

    public final void setMonthlyStats(final ArrayList<MonthlyStats> monthlyStats) {
        this.monthlyStats = monthlyStats;
    }

    @Override
    public String toString() {
        return "ProducersOutput{"
                +
                "id="
                +
                id
                +
                ", maxDistributors="
                +
                maxDistributors
                +
                ", priceKW="
                +
                priceKW
                +
                ", energyType="
                +
                energyType
                +
                ", energyPerDistributor="
                +
                energyPerDistributor
                +
                ", monthlyStats="
                +
                monthlyStats
                +
                '}';
    }
}
