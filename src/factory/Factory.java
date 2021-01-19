package factory;

public interface Factory {

    /**
     * metoda care copiaza datele de intrare pentru a fi editate
     * @param obj variabila ce contine toate datele de intrare
     * @return se returneaza datele de prelucrat in lunctie de caz
     */
    Object initialise(Object obj);

}
