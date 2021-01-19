import altereddata.DistributorsAltered;

import java.util.ArrayList;

public final class Method {

    private final int minim = 1000;

    private static Method instance;

    private Method() { }

    /**
     * metoda ce instantiaza singleton
     * pentru clasa in care se afla metoda
     * ce calculeaza cel mai ieftin contract
     * @return se returneaza instanta
     */
    public static Method getInstance() {
        if (instance == null) {
            instance = new Method();
        }
        return instance;
    }

    /**
     * metoda ce calculeaza costul de productie pt fiecare distribuitor
     * si pretul contractului
     * si gaseste cel mai ieftin contract
     * @param distributorsAltereds arraylistul de distributori
     * @return returneaza indicele celui mai ieftin contract
     */
    public int minimID(ArrayList<DistributorsAltered> distributorsAltereds) {
        int min = minim;
        int indice = 0;
        for (DistributorsAltered distributorsAltered : distributorsAltereds) {
            if (!distributorsAltered.isBankrupt()) {
                distributorsAltered.calculareProdCost();
                distributorsAltered.calculareContractCost();
                if (distributorsAltered.getContractPrice() < min) {
                    min = distributorsAltered.getContractPrice();
                    indice = distributorsAltered.getId();
                }
            }
        }
        return indice;
    }


}
