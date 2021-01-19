package altereddata;

public class ConsumersAltered {

    private int id;
    private int initialBudget;
    private int monthlyIncome;
    private DistributorsAltered actualDistributor;
    private DistributorsAltered oldDistribuitor;
    private int actualDistributorPrice;
    private int oldDistribuitorPrice;
    private int monthsLeft;
    private boolean isBankrupt;
    private boolean isRestantier;

    public ConsumersAltered() { }

    public ConsumersAltered(final int id, final int initialBudget, final int monthlyIncome) {
        this.id = id;
        this.initialBudget = initialBudget;
        this.monthlyIncome = monthlyIncome;
        this.actualDistributor = null;
        this.oldDistribuitor = null;
        this.actualDistributorPrice = 0;
        this.oldDistribuitorPrice = 0;
        this.monthsLeft = 0;
        this.isBankrupt = false;
        this.isRestantier = false;
    }

    public final int getId() {
        return id;
    }

    public final void setId(final int id) {
        this.id = id;
    }

    public final int getInitialBudget() {
        return initialBudget;
    }

    public final void setInitialBudget(final int initialBudget) {
        this.initialBudget = initialBudget;
    }

    public final int getMonthlyIncome() {
        return monthlyIncome;
    }

    public final void setMonthlyIncome(final int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public final DistributorsAltered getActualDistributor() {
        return actualDistributor;
    }

    public final void setActualDistributor(final DistributorsAltered actualDistributor) {
        this.actualDistributor = actualDistributor;
    }

    public final DistributorsAltered getOldDistribuitor() {
        return oldDistribuitor;
    }

    public final void setOldDistribuitor(final DistributorsAltered oldDistribuitor) {
        this.oldDistribuitor = oldDistribuitor;
    }

    public final int getActualDistributorPrice() {
        return actualDistributorPrice;
    }

    public final void setActualDistributorPrice(final int actualDistributorPrice) {
        this.actualDistributorPrice = actualDistributorPrice;
    }

    public final int getOldDistribuitorPrice() {
        return oldDistribuitorPrice;
    }

    public final void setOldDistribuitorPrice(final int oldDistribuitorPrice) {
        this.oldDistribuitorPrice = oldDistribuitorPrice;
    }

    public final int getMonthsLeft() {
        return monthsLeft;
    }

    public final void setMonthsLeft(final int monthsLeft) {
        this.monthsLeft = monthsLeft;
    }

    public final boolean isBankrupt() {
        return isBankrupt;
    }

    public final void setBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public final boolean isRestantier() {
        return isRestantier;
    }

    public final void setRestantier(final boolean restantier) {
        isRestantier = restantier;
    }

    @Override
    public String toString() {
        return "ConsumersAltered{"
                +
                "id="
                +
                id
                +
                ", initialBudget="
                +
                initialBudget
                +
                ", monthlyIncome="
                +
                monthlyIncome
                +
                ", actualDistributor="
                +
                actualDistributor.getId()
                +
                ", actualDistributorPrice="
                +
                actualDistributorPrice
                +
                ", oldDistribuitorPrice="
                +
                oldDistribuitorPrice
                +
                ", monthsLeft="
                +
                monthsLeft
                +
                ", isBankrupt="
                +
                isBankrupt
                +
                ", isRestantier="
                +
                isRestantier
                +
                '}';
    }

    /**
     * metoda prin care consumatorul isi alege un distributor daca este nevoie
     * @param distributorsAltered distribuitorul cu cel mai ieftin contract
     */
    public void chooseDist(DistributorsAltered distributorsAltered) {
        if (!isBankrupt()) {
            if (getActualDistributor() != null && getMonthsLeft() == 0) {
                getActualDistributor().getConsumers().remove(this);
                setOldDetails();
                setDetails(distributorsAltered);
                distributorsAltered.getConsumers().add(this);
            }
            if (getActualDistributor() == null && getMonthsLeft() == 0) {
                setDetails(distributorsAltered);
                distributorsAltered.getConsumers().add(this);
            }
        }
    }

    /**
     * metoda stabileste distributorul vechi ai consumatorului
     * daca este cazul
     */
    public void setOldDetails() {
        this.oldDistribuitor = actualDistributor;
        this.oldDistribuitorPrice = actualDistributorPrice;
    }

    /**
     * metoda care stabileste distribuitorul unui consumator
     * si pretul pe care acesta trebuie sa il plateasca
     * @param distributorsAltered arraylist-ul cu distributori
     */
    public void setDetails(DistributorsAltered distributorsAltered) {
        this.actualDistributor = distributorsAltered;
        this.monthsLeft = distributorsAltered.getContractLength();
        this.actualDistributorPrice = distributorsAltered.getContractPrice();
    }

    /**
     * Metoda pentru a calcula bugetul pentru cazul in care
     * consumatorul nu este restantier
     */
    public void notRestantier() {
        if (initialBudget - actualDistributorPrice >= 0) {
            initialBudget -= actualDistributorPrice;
            actualDistributor.addConsumerMoney(actualDistributorPrice);
        } else {
            isRestantier = true;
            oldDistribuitor = actualDistributor;
            oldDistribuitorPrice = actualDistributorPrice;
        }
    }

    /**
     * Metoda pentru cazul in care consumatorul este restantier
     * iar noul dist este tot cel dinainte
     * si va trebui sa plateasca si penalizarile
     * si factura curenta
     * @param penance penalizarile lunii trecute
     */
    public void sameDistributor(int penance) {
        if (initialBudget - actualDistributorPrice - penance >= 0) {
            initialBudget -= actualDistributorPrice;
            initialBudget -= penance;
            actualDistributor.addConsumerMoney(actualDistributorPrice);
            oldDistribuitor.addConsumerMoney(penance);
            isRestantier = false;
        } else {
            isBankrupt = true;
        }
    }

    /**
     * Metoda pentru cazul in care consumatorul este restantier
     * iar noul dist este diferit de cel precedent
     * si trebuie sa plateasca doar penalizarile
     * factura curenta fiind amanata
     * @param penance penalizarile lunii trecute
     */
    public void differentDistributor(int penance) {
        if (initialBudget - penance >= 0) {
            initialBudget -= penance;
            oldDistribuitor.addConsumerMoney(oldDistribuitorPrice);
            oldDistribuitor = actualDistributor;
            oldDistribuitorPrice = actualDistributorPrice;
        } else {
            isBankrupt = true;
        }
    }

    /**
     * Metoda pentru a calcula bugetul in cazul in care
     * consumatorul este restantier
     */
    public void isRestantieR() {
        int penance = (int) Math.round(Math.floor(1.2 * oldDistribuitorPrice));
        if (actualDistributor.equals(oldDistribuitor)) {
            sameDistributor(penance);
        } else {
            differentDistributor(penance);
        }
    }

    /**
     * Metoda care calculeaza datele unui consumator la finalul lunii
     */
    public void calculateBudget() {
        monthsLeft--;
        initialBudget += monthlyIncome;
        if (!isBankrupt) {
            if (!isRestantier) {
                notRestantier();
            } else {
                isRestantieR();
            }
        }
    }
}
