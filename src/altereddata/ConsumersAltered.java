package altereddata;

public class ConsumersAltered {

    private int id;
    private int initialBudget;
    private int monthlyIncome;
    private DistributorsAltered actualDistributor;
    private int actualDistributorPrice;
    private DistributorsAltered oldDistribuitor;
    private int oldDistribuitorPrice;
    private int monthsLeft;
    private boolean isRestantier;
    private final double constanta = 1.2;
    private boolean isBankrupt;

    public ConsumersAltered() { }

    public ConsumersAltered(final int id, final int initialBudget, final int monthlyIncome) {
        this.id = id;
        this.initialBudget = initialBudget;
        this.monthlyIncome = monthlyIncome;
        this.actualDistributor = null;
        this.actualDistributorPrice = 0;
        this.oldDistribuitor = null;
        this.oldDistribuitorPrice = 0;
        this.monthsLeft = 0;
        this.isRestantier = false;
        this.isBankrupt = false;
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

    public final int getActualDistributorPrice() {
        return actualDistributorPrice;
    }

    public final int getMonthsLeft() {
        return monthsLeft;
    }

    public final boolean isBankrupt() {
        return isBankrupt;
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
        int penance = (int) Math.round(Math.floor(constanta * oldDistribuitorPrice));
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
