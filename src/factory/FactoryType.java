package factory;

public class FactoryType {

    /**
     * metoda ce verifica tipul variabilei
     * trimise ca parametru
     * @param s tipul variabilei trimis ca si string
     * @return se returneaza cand gaseste un tip cu care coincide
     */
    public Factory typeOfVariables(String s) {
        if (s.equals("Dist")) {
            return new InitDistAltered();
        }
        if (s.equals("Prod")) {
            return new InitProdAltered();
        }
        if (s.equals("Cons")) {
            return new InitConsAltered();
        }
        if (s.equals("DistOut")) {
            return new OutputDistAltered();
        }
        if (s.equals("ConsOut")) {
            return new OutputConsAltered();
        }
        if (s.equals("ProdOut")) {
            return new OutputProdAltered();
        }
        return null;
    }

}
