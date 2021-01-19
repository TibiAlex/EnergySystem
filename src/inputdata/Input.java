package inputdata;

import java.util.ArrayList;

public class Input {

    private int numberOfTurns;
    private InitData initialData = new InitData();
    private ArrayList<MonthlyUpdate> monthlyUpdates = new ArrayList<>();

    public Input() { }

    public Input(final int numberOfTurns, final InitData initialData,
                 final ArrayList<MonthlyUpdate> monthlyUpdates) {
        this.numberOfTurns = numberOfTurns;
        this.initialData = initialData;
        this.monthlyUpdates = monthlyUpdates;
    }

    public final int getNumberOfTurns() {
        return numberOfTurns;
    }

    public final InitData getInitialData() {
        return initialData;
    }

    public final ArrayList<MonthlyUpdate> getMonthlyUpdates() {
        return monthlyUpdates;
    }

    public final void setNumberOfTurns(final int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    public final void setInitialData(final InitData initialData) {
        this.initialData = initialData;
    }

    public final void setMonthlyUpdates(final ArrayList<MonthlyUpdate> monthlyUpdates) {
        this.monthlyUpdates = monthlyUpdates;
    }

    @Override
    public String toString() {
        return "Input{"
                +
                "numberOfTurns="
                +
                numberOfTurns
                +
                ", initialData="
                +
                initialData
                +
                ", monthlyUpdates="
                +
                monthlyUpdates
                +
                '}';
    }
}
