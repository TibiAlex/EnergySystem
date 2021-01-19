package inputdata;

public class ProducerChanges {

    private int id;
    private int energyPerDistributor;

    public ProducerChanges() { }

    public ProducerChanges(final int id, final int energyPerDistributor) {
        this.id = id;
        this.energyPerDistributor = energyPerDistributor;
    }

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

    @Override
    public String toString() {
        return "ProducerChanges{"
                +
                "id="
                +
                id
                +
                ", energyPerDistributor="
                +
                energyPerDistributor
                +
                '}';
    }
}

