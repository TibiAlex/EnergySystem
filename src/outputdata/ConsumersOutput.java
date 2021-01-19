package outputdata;

public class ConsumersOutput {

    private int id;
    private boolean isBankrupt;
    private int budget;

    public ConsumersOutput() { }

    public ConsumersOutput(final int id, final boolean isBankrupt, final int budget) {
        this.id = id;
        this.isBankrupt = isBankrupt;
        this.budget = budget;
    }

    public final int getId() {
        return id;
    }

    public final void setId(final int id) {
        this.id = id;
    }

    public final boolean isIsBankrupt() {
        return isBankrupt;
    }

    public final int getBudget() {
        return budget;
    }

    public final void setBudget(final int budget) {
        this.budget = budget;
    }

    /**
     * metoda pentru a afisa consumatorii prelucrati
     * @return se returneaza campurile id, isBankrupt si budget pentru fiecare consumator
     */
    @Override
    public String toString() {
        return "ConsumersOutput{"
                +
                "id="
                +
                id
                +
                ", isBankrupt="
                +
                isBankrupt
                +
                ", budget="
                +
                budget
                +
                '}';
    }
}
