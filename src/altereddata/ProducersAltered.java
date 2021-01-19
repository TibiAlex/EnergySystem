package altereddata;

import entities.EnergyType;
import observer.IObservable;

import java.util.ArrayList;

public class ProducersAltered implements IObservable {

    private int id;
    private EnergyType energyType;
    private int maxDistributors;
    private double priceKW;
    private int energyPerDistributor;
    private ArrayList<MonthlyStats> monthlyStats;
    private ArrayList<DistributorsAltered> distributors;
    private int oldEnergyPerDistributor;

    public ProducersAltered() { }

    public ProducersAltered(final int id, final EnergyType energyType, final int maxDistributors,
                            final double priceKW, final int energyPerDistributor) {
        this.id = id;
        this.energyType = energyType;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyPerDistributor = energyPerDistributor;
        this.oldEnergyPerDistributor = 0;
        this.monthlyStats = new ArrayList<>();
        this.distributors = new ArrayList<>();
    }

    public final int getId() {
        return id;
    }

    public final void setId(final int id) {
        this.id = id;
    }

    public final EnergyType getEnergyType() {
        return energyType;
    }

    public final void setEnergyType(final EnergyType energyType) {
        this.energyType = energyType;
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

    public final void setOldEnergyPerDistributor(final int oldEnergyPerDistributor) {
        this.oldEnergyPerDistributor = oldEnergyPerDistributor;
    }

    public final ArrayList<DistributorsAltered> getDistributors() {
        return distributors;
    }

    public final void setDistributors(final ArrayList<DistributorsAltered> distributors) {
        this.distributors = distributors;
    }

    /**
     * Metoda care verifica daca un anumit distribuitor se gaseste
     * deja printre clientii unui producator
     * @param d un distributor
     * @return returneaza daca distributorul se gaseste printre
     * clientii producatorului
     */
    public boolean containsDistributor(DistributorsAltered d) {
        return distributors.contains(d);
    }

    /**
     * Metoda care pune in variabila monthlystats
     * id-urile Distribuitorilor care cumpara
     * energie de la acest producator
     * @param value numarul lunii in care se afla simularea
     */
    public void setMonthsInfo(int value) {
        ArrayList<Integer> d = new ArrayList<>();
        for (DistributorsAltered distributor : distributors) {
            d.add(distributor.getId());
        }
        MonthlyStats m = new MonthlyStats(value, d);
        monthlyStats.add(m);
    }

    /**
     * Metoda ce calculeaza costul pe ca va trebui sa il plateasca distribuitorul
     * @return returneaza costul pentru acest producator
     */
    public double calculateCost() {
        return energyPerDistributor * priceKW;
    }

    /**
     * metoda adauga un distributor ca si client
     * al producatorului
     * @param d distributorul de adaugat
     */
    @Override
    public void add(DistributorsAltered d) {
          distributors.add(d);
    }

    /**
     * metoda scoate un client de la producator
     * @param d distributorul de scos
     */
    @Override
    public void remove(DistributorsAltered d) {
        distributors.remove(d);
    }

    /**
     * metoda anunta toti distributorii ca producatorul
     * si-a schimbat cantitatea de energie
     * pentru ca acestia sa faca schimbarile necesare
     * si apoi ii scoate pe toti din lista
     */
    @Override
    public void notifyUpdate() {
        for (DistributorsAltered distributor : distributors) {
            distributor.update(oldEnergyPerDistributor, priceKW);
        }
        distributors.clear();
    }
}
