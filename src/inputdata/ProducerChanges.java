package inputdata;

public class ProducerChanges {

    private int id;
    private int energyPerDistributor;

    public final int getId() {
        return id;
    }

    public final int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public final void setId(final int id) {
        this.id = id;
    }

    public final void setEnergyPerDistributor(final int energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }
}

