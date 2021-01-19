package inputdata;

import java.util.ArrayList;

public class MonthlyUpdate {

    private ArrayList<Consumers> newConsumers = new ArrayList<>();
    private ArrayList<DistributorChanges> distributorChanges = new ArrayList<>();
    private ArrayList<ProducerChanges> producerChanges = new ArrayList<>();

    private MonthlyUpdate() { }

    public MonthlyUpdate(final ArrayList<Consumers> newConsumers,
                         final ArrayList<DistributorChanges> distributorChanges,
                         final ArrayList<ProducerChanges> producerChanges) {
        this.newConsumers = newConsumers;
        this.distributorChanges = distributorChanges;
        this.producerChanges = producerChanges;
    }

    public final ArrayList<Consumers> getNewConsumers() {
        return newConsumers;
    }

    public final ArrayList<DistributorChanges> getDistributorChanges() {
        return distributorChanges;
    }

    public final ArrayList<ProducerChanges> getProducerChanges() {
        return producerChanges;
    }

    public final void setNewConsumers(final ArrayList<Consumers> newConsumers) {
        this.newConsumers = newConsumers;
    }

    public final void setDistributorChanges(final ArrayList<DistributorChanges>
                                                    distributorChanges) {
        this.distributorChanges = distributorChanges;
    }

    public final void setProducerChanges(final ArrayList<ProducerChanges> producerChanges) {
        this.producerChanges = producerChanges;
    }

    @Override
    public String toString() {
        return "MonthlyUpdate{"
                +
                "newConsumers="
                +
                newConsumers
                +
                ", distributorChanges="
                +
                distributorChanges
                +
                ", producerChanges="
                +
                producerChanges
                +
                '}';
    }
}
