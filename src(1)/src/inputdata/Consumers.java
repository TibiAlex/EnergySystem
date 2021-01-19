package inputdata;

public class Consumers {

    private int id;
    private int initialBudget;
    private int monthlyIncome;

    public Consumers() { }

    public Consumers(final int id, final int initialBudget, final int monthlyIncome) {
        this.id = id;
        this.initialBudget = initialBudget;
        this.monthlyIncome = monthlyIncome;
    }

    public final int getId() {
        return id;
    }

    public final int getInitialBudget() {
        return initialBudget;
    }

    public final int getMonthlyIncome() {
        return monthlyIncome;
    }

    public final void setId(final int id) {
        this.id = id;
    }

    public final void setInitialBudget(final int initialBudget) {
        this.initialBudget = initialBudget;
    }

    public final void setMonthlyIncome(final int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    @Override
    public String toString() {
        return "Consumers{"
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
                '}';
    }
}
