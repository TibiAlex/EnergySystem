package factory;

import altereddata.ConsumersAltered;
import inputdata.Consumers;
import inputdata.Input;

import java.util.ArrayList;

public class InitConsAltered implements Factory {

    /**
     * metoda care copiaza datele de intrare pentru a fi editate
     * @param obj variabila ce contine toate datele de intrare
     * @return se returneaza arraylistul cu toti consumatorii de prelucrat
     */
    @Override
    public Object initialise(Object obj) {
        Input input = (Input) obj;
        ArrayList<ConsumersAltered> consumersAltereds = new ArrayList<>();
        ArrayList<Consumers> consumers = input.getInitialData().getConsumers();
        for (Consumers consumer : consumers) {
            ConsumersAltered consumersAltered = new ConsumersAltered(consumer.getId(),
                    consumer.getInitialBudget(),
                    consumer.getMonthlyIncome());
            consumersAltereds.add(consumersAltered);
        }
        return consumersAltereds;
    }
}
