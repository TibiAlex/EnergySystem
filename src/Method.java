import altereddata.DistributorsAltered;

import java.util.ArrayList;

public class Method {

    /**
     * metoda ce calculeaza costul de productie pt fiecare distribuitor
     * si pretul contractului
     * si gaseste cel mai ieftin contract
     * @param distributorsAltereds arraylistul de distributori
     * @return returneaza indicele celui mai ieftin contract
     */
    public int minimID(ArrayList<DistributorsAltered> distributorsAltereds) {
        int min = 1000;
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
