package inputdata;

import java.util.ArrayList;

public class Input {

    private int numberOfTurns;
    private InitData initialData = new InitData();
    private ArrayList<MonthlyUpdate> monthlyUpdates = new ArrayList<>();

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
}
