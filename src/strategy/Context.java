package strategy;

import altereddata.DistributorsAltered;
import altereddata.ProducersAltered;

import java.util.ArrayList;

public class Context {

    private final IStrategy strategy;

    public Context(IStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     *metoda ce va executa o anumita strategie in functiede caz
     * @param distributorsAltered distribuitorul ce isi cauta producatori
     * @param producersAltereds lista de producatori din care poate alege
     */
    public void executeStrategy(DistributorsAltered distributorsAltered,
                                ArrayList<ProducersAltered> producersAltereds) {
        strategy.strategy(distributorsAltered, producersAltereds);
    }

}
