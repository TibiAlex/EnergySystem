package observer;

import altereddata.DistributorsAltered;

public interface IObservable {

    /**
     * metoda ce adauga un dist ca si client al unui prod
     * @param distributorsAltered dist de adaugat
     */
    void add(DistributorsAltered distributorsAltered);

    /**
     * metoda ce scoate un dist dintre clientii unui prod
     * @param distributorsAltered dist de scos
     */
    void remove(DistributorsAltered distributorsAltered);

    /**
     * metoda ce anunta distributorii clienti
     * daca a avut loc vreo schimbare
     */
    void notifyUpdate();

}
