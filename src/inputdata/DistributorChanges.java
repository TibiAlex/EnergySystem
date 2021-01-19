package inputdata;

public class DistributorChanges {

    private int id;
    private int infrastructureCost;

    public DistributorChanges() { }

    public DistributorChanges(final int id, final int infrastructureCost) {
        this.id = id;
        this.infrastructureCost = infrastructureCost;
    }

    public final int getId() {
        return id;
    }

    public final int getInfrastructureCost() {
        return infrastructureCost;
    }

    public final void setId(final int id) {
        this.id = id;
    }

    public final void setInfrastructureCost(final int infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }

    @Override
    public String toString() {
        return "DistributorChanges{"
                +
                "id="
                +
                id
                +
                ", infrastructureCost="
                +
                infrastructureCost
                +
                '}';
    }
}
