package updatedata;

import altereddata.ConsumersAltered;
import altereddata.DistributorsAltered;
import altereddata.ProducersAltered;
import inputdata.Consumers;
import inputdata.DistributorChanges;
import inputdata.MonthlyUpdate;
import inputdata.ProducerChanges;

import java.util.ArrayList;

public final class SetChanges {

    private static SetChanges instance = new SetChanges();

    private SetChanges() { }

    /**
     *metoda pentru a declara instanta singleton
     * pentru clasa ce se ocupa cu schimbarile lunare
     * @return se returneaza instanta
     */
    public static SetChanges getInstance() {
        if (instance == null) {
            instance = new SetChanges();
        }
        return instance;
    }

    /**
     * metoda pentru schimbarea datelor lunare la consumatori
     * @param consumersAltereds consumatorii de prelucrat
     * @param monthlyUpdates update-urile lunare
     * @param month luna in care ne aflam
     */
    public void setConsumerChanges(ArrayList<ConsumersAltered> consumersAltereds,
                                   ArrayList<MonthlyUpdate> monthlyUpdates, int month) {
        ArrayList<Consumers> newConsumers = monthlyUpdates.get(month).getNewConsumers();
        if (newConsumers.size() != 0) {
            for (Consumers newConsumer : newConsumers) {
                ConsumersAltered c = new ConsumersAltered(newConsumer.getId(),
                        newConsumer.getInitialBudget(),
                        newConsumer.getMonthlyIncome());
                consumersAltereds.add(c);
            }
        }
    }

    /**
     * metoda pentru schimbarea datelor lunare a distributorilor
     * @param distributorsAltereds distributorii de prelucrat
     * @param monthlyUpdates update-urile lunare
     * @param month luna in care ne aflam
     */
    public void setDistributorChanges(ArrayList<DistributorsAltered> distributorsAltereds,
                                      ArrayList<MonthlyUpdate> monthlyUpdates, int month) {
        ArrayList<DistributorChanges> distributorChanges;
        distributorChanges = monthlyUpdates.get(month).getDistributorChanges();
        if (distributorChanges.size() != 0) {
            for (DistributorChanges distributorChange : distributorChanges) {
                int id = distributorChange.getId();
                distributorsAltereds.get(id).setInitialInfrastructureCost(
                        distributorChange.getInfrastructureCost());
            }
        }
    }

    /**
     * metoda pentru schimbarea datelor lunare la producatori
     * @param producersAltereds producatorii de prelucrat
     * @param monthlyUpdates update-urile lunare
     * @param month luna in care ne aflat
     */
    public void setProducerChanges(ArrayList<ProducersAltered> producersAltereds,
                                   ArrayList<MonthlyUpdate> monthlyUpdates, int month) {
        ArrayList<ProducerChanges> producerChanges;
        producerChanges = monthlyUpdates.get(month).getProducerChanges();
        if (producerChanges.size() != 0) {
            for (ProducerChanges producerChange : producerChanges) {
                int id = producerChange.getId();
                producersAltereds.get(id).setOldEnergyPerDistributor(
                        producersAltereds.get(id).getEnergyPerDistributor());
                producersAltereds.get(id).setEnergyPerDistributor(
                        producerChange.getEnergyPerDistributor());
                producersAltereds.get(id).notifyUpdate();
            }
        }
    }
}
