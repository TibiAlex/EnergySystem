package outputdata;

import java.util.ArrayList;

public class Output {

    private ArrayList<ConsumersOutput> consumers = new ArrayList<>();
    private ArrayList<DistributorsOutput> distributors = new ArrayList<>();
    private ArrayList<ProducersOutput> energyProducers = new ArrayList<>();

    private Output() { }

    public Output(final ArrayList<ConsumersOutput> consumersOutputs,
                  final ArrayList<DistributorsOutput> distributorsOutputs,
                  final ArrayList<ProducersOutput> producersOutputs) {
        this.consumers = consumersOutputs;
        this.distributors = distributorsOutputs;
        this.energyProducers = producersOutputs;
    }

    public final ArrayList<ConsumersOutput> getConsumers() {
        return consumers;
    }

    public final void setConsumers(final ArrayList<ConsumersOutput> consumers) {
        this.consumers = consumers;
    }

    public final ArrayList<DistributorsOutput> getDistributors() {
        return distributors;
    }

    public final void setDistributors(final ArrayList<DistributorsOutput>
                                              distributors) {
        this.distributors = distributors;
    }

    public final ArrayList<ProducersOutput> getEnergyProducers() {
        return energyProducers;
    }

    public final void setEnergyProducers(final ArrayList<ProducersOutput> energyProducers) {
        this.energyProducers = energyProducers;
    }

    @Override
    public String toString() {
        return "Output{"
                +
                "consumers="
                +
                consumers
                +
                ", distributors="
                +
                distributors
                +
                ", producers="
                +
                energyProducers
                +
                '}';
    }
}
