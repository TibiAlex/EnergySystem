package strategy;

import altereddata.DistributorsAltered;
import altereddata.ProducersAltered;

import java.util.ArrayList;

public interface IStrategy {

    /**
     * metoda de cautare a producatorilor pentru
     * ca distribuitorii sa faca rost de energie
     * in funtie de strategia lor
     * @param distributorsAltered distribuitorul ce cauta energie
     * @param producersAltereds lista de producatori in care se va cauta
     */
    void strategy(DistributorsAltered distributorsAltered,
                  ArrayList<ProducersAltered> producersAltereds);

}
