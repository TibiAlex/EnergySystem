package factory;

import altereddata.ProducersAltered;
import outputdata.ProducersOutput;

import java.util.ArrayList;

public class OutputProdAltered implements Factory {

    /**
     * metoda ce initializeaza datele de iesire
     * @param obj obiectul trimis ca parametru
     * @return se returneaza producatorii de afisat
     */
    @Override
    public Object initialise(Object obj) {
        ArrayList<ProducersAltered> producersAltereds = (ArrayList<ProducersAltered>) obj;
        ArrayList<ProducersOutput> producersOutputs = new ArrayList<>();
        for (ProducersAltered producersAltered : producersAltereds) {
            ProducersOutput producersOutput = new ProducersOutput(
                    producersAltered.getId(),
                    producersAltered.getMaxDistributors(),
                    producersAltered.getPriceKW(),
                    producersAltered.getEnergyType(),
                    producersAltered.getEnergyPerDistributor(),
                    producersAltered.getMonthlyStats());
            producersOutputs.add(producersOutput);
        }
        return producersOutputs;
    }
}
