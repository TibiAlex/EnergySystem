package observer;

public interface IObserver {

    /**
     * metoda ce updateaza cantitatea de energie
     * primita de la producatori
     * @param old vechiaul pret al producatorului
     * @param price pretul producatorului
     */
    void update(int old, double price);

}
