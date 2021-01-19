package factory;

import altereddata.ProducersAltered;
import inputdata.Input;
import inputdata.Producers;

import java.util.ArrayList;

public class InitProdAltered implements Factory {

    /**
     * metoda care copiaza datele de intrare pentru a fi editate
     * @param obj variabila ce contine toate datele de intrare
     * @return se returneaza arraylistul cu toti producatorii de prelucrat
     */
    @Override
    public Object initialise(Object obj) {
        Input input = (Input) obj;
        ArrayList<ProducersAltered> producersAltereds = new ArrayList<>();
        ArrayList<Producers> producers = input.getInitialData().getProducers();
        for (Producers producer : producers) {
            ProducersAltered producersAltered = new ProducersAltered(producer.getId(),
                    producer.getEnergyType(), producer.getMaxDistributors(),
                    producer.getPriceKW(), producer.getEnergyPerDistributor());
            producersAltereds.add(producersAltered);
        }
        return producersAltereds;
    }
}
