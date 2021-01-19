package altereddata;

import java.util.ArrayList;

public class MonthlyStats {

    private int month;
    private ArrayList<Integer> distributorsIds;

    public MonthlyStats() { }

    public MonthlyStats(final int month, final ArrayList distrbutordID) {
        this.month = month;
        this.distributorsIds = distrbutordID;
    }

    public final int getMonth() {
        return month;
    }

    public final void setMonth(final int month) {
        this.month = month;
    }

    public final ArrayList<Integer> getDistributorsIds() {
        return distributorsIds;
    }

    public final void setDistributorsIds(final ArrayList<Integer> distributorsIds) {
        this.distributorsIds = distributorsIds;
    }

    @Override
    public String toString() {
        return "MonthlyStats{"
                +
                "month="
                +
                month
                +
                ", distributorsID="
                +
                distributorsIds
                +
                '}';
    }
}
