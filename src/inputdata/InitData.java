package inputdata;

import java.util.ArrayList;

public class InitData {

    private ArrayList<Consumers> consumers = new ArrayList<>();
    private ArrayList<Distributors> distributors = new ArrayList<>();
    private ArrayList<Producers> producers = new ArrayList<>();

    public InitData() { }

    public final ArrayList<Consumers> getConsumers() {
        return consumers;
    }

    public final ArrayList<Distributors> getDistributors() {
        return distributors;
    }

    public final ArrayList<Producers> getProducers() {
        return producers;
    }

    public final void setConsumers(final ArrayList<Consumers> consumers) {
        this.consumers = consumers;
    }

    public final void setDistributors(final ArrayList<Distributors> distributors) {
        this.distributors = distributors;
    }

    public final void setProducers(final ArrayList<Producers> producers) {
        this.producers = producers;
    }
}
