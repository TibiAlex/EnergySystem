package factory;

import altereddata.ConsumersAltered;
import outputdata.ConsumersOutput;

import java.util.ArrayList;

public class OutputConsAltered implements Factory {

    /**
     * metoda ce initializeaza datele de iesire
     * @param obj obiectul trimis ca parametru
     * @return se returneaza consumatorii de afisat
     */
    @Override
    public Object initialise(Object obj) {
        ArrayList<ConsumersAltered> consumersAltereds = (ArrayList<ConsumersAltered>) obj;
        ArrayList<ConsumersOutput> consumersOutputs = new ArrayList<>();
        for (ConsumersAltered consumersAltered : consumersAltereds) {
            ConsumersOutput consumersOutput = new ConsumersOutput(
                    consumersAltered.getId(),
                    consumersAltered.isBankrupt(),
                    consumersAltered.getInitialBudget());
            consumersOutputs.add(consumersOutput);
        }
        return consumersOutputs;
    }
}
