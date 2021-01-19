package updatedata;

import altereddata.ConsumersAltered;
import altereddata.DistributorsAltered;
import altereddata.ProducersAltered;
import inputdata.Consumers;
import inputdata.Distributors;
import inputdata.Producers;

import java.util.ArrayList;

public class CopyInput {

    /**
     * Metoda ce copiaza distribuitorii intr-un vector de editat
     * @param distributors distribuitorii initiali
     * @param distributorsAltereds distribuitorii de editat
     */
    public void copyDistributors(final ArrayList<Distributors> distributors,
                                 final ArrayList<DistributorsAltered> distributorsAltereds) {
        for (Distributors distributor : distributors) {
            DistributorsAltered distributorsAltered =
                    new DistributorsAltered(distributor.getId(),
                            distributor.getContractLength(),
                            distributor.getInitialBudget(),
                            distributor.getInitialInfrastructureCost(),
                            distributor.getEnergyNeededKW(),
                            distributor.getProducerStrategy());
            distributorsAltereds.add(distributorsAltered);
        }
    }

    /**
     * metoda ce copiaza consumatorii intr-un vector ce va fi editat
     * @param consumersAltereds consumatorii initiali
     * @param consumers consumatorii de editat
     */
    public void copyConsumers(final ArrayList<ConsumersAltered> consumersAltereds,
                              final ArrayList<Consumers> consumers) {
        for (Consumers consumer : consumers) {
            ConsumersAltered consumersAltered = new ConsumersAltered(consumer.getId(),
                    consumer.getInitialBudget(),
                    consumer.getMonthlyIncome());
            consumersAltereds.add(consumersAltered);
        }
    }

    /**
     * Metoda ce copiaza producatorii intr-un vector ce va fi editat
     * @param producers producatorii initiali
     * @param producersAltereds producatorii de editat
     */
    public void copyProducers(final ArrayList<Producers> producers,
                              final ArrayList<ProducersAltered> producersAltereds) {
        for (Producers producer : producers) {
            ProducersAltered producersAltered = new ProducersAltered(producer.getId(),
                    producer.getEnergyType(), producer.getMaxDistributors(),
                    producer.getPriceKW(), producer.getEnergyPerDistributor());
            producersAltereds.add(producersAltered);
        }
    }

}
