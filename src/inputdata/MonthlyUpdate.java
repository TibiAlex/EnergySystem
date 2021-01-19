package inputdata;

import java.util.ArrayList;

public final class MonthlyUpdate {

    private ArrayList<Consumers> newConsumers = new ArrayList<>();
    private ArrayList<DistributorChanges> distributorChanges = new ArrayList<>();
    private ArrayList<ProducerChanges> producerChanges = new ArrayList<>();

    private MonthlyUpdate() { }

    public ArrayList<Consumers> getNewConsumers() {
        return newConsumers;
    }

    public ArrayList<DistributorChanges> getDistributorChanges() {
        return distributorChanges;
    }

    public ArrayList<ProducerChanges> getProducerChanges() {
        return producerChanges;
    }

    public void setNewConsumers(final ArrayList<Consumers> newConsumers) {
        this.newConsumers = newConsumers;
    }

    public void setDistributorChanges(final ArrayList<DistributorChanges>
                                                    distributorChanges) {
        this.distributorChanges = distributorChanges;
    }

    public void setProducerChanges(final ArrayList<ProducerChanges> producerChanges) {
        this.producerChanges = producerChanges;
    }
}
