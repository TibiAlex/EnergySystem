package altereddata;

import entities.EnergyType;

import java.util.ArrayList;

public class ProducersAltered {

    private int id;
    private EnergyType energyType;
    private int maxDistributors;
    private double priceKW;
    private int energyPerDistributor;
    private ArrayList<MonthlyStats> monthlyStats;
    private ArrayList<Integer> distrbutorID;
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
        this.distrbutorID = new ArrayList<>();
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

    public final ArrayList<Integer> getDistrbutorID() {
        return distrbutorID;
    }

    public final void setDistrbutorID(final ArrayList<Integer> distrbutorID) {
        this.distrbutorID = distrbutorID;
    }

    public final int getOldEnergyPerDistributor() {
        return oldEnergyPerDistributor;
    }

    public final void setOldEnergyPerDistributor(final int oldEnergyPerDistributor) {
        this.oldEnergyPerDistributor = oldEnergyPerDistributor;
    }

    @Override
    public String toString() {
        return "ProducersAltered{"
                +
                "id="
                +
                id
                +
                ", energyType="
                +
                energyType
                +
                ", maxDistributors="
                +
                maxDistributors
                +
                ", priceKW="
                +
                priceKW
                +
                ", energyPerDistributor="
                +
                energyPerDistributor
                +
                ", monthlyStats="
                +
                monthlyStats
                +
                ", distrbutorID="
                +
                distrbutorID
                +
                ", oldEnergyPerDistributor="
                +
                oldEnergyPerDistributor
                +
                '}';
    }

    /**
     * metoda ce scoate distribuitorii de la un prod
     * daca au loc schimbari la acesta din urma
     * @param distributorsAltereds arraylist cu toti dist
     */
    public void schimbare(ArrayList<DistributorsAltered> distributorsAltereds) {
        for (DistributorsAltered distributorsAltered : distributorsAltereds) {
            if (distrbutorID.contains(distributorsAltered.getId())) {
                distributorsAltered.scadere(oldEnergyPerDistributor, priceKW);
            }
        }
        distrbutorID.clear();
    }

    /**
     * metoda
     * @return returneaza daca au avut loc schimbari
     */
    public boolean schimbat() {
        return oldEnergyPerDistributor != energyPerDistributor;
    }

    /**
     * Metoda care verifica daca un anumit distribuitor se gaseste
     * deja printre clientii unui producator
     * @param d un distributor
     * @return returneaza daca distributorul se gaseste printre
     * clientii producatorului
     */
    public boolean containsDistributor(int d) {
        return distrbutorID.contains(d);
    }

    /**
     * Metoda care pune in variabila monthlystats
     * id-urile Distribuitorilor care cumpara
     * energie de la acest producator
     * @param value numarul lunii in care se afla simularea
     */
    public void setMonthsInfo(int value) {
        ArrayList<Integer> d = new ArrayList<>(distrbutorID);
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
}
