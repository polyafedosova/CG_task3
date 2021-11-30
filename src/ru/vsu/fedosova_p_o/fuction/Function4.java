package ru.vsu.fedosova_p_o.fuction;

public class Function4 extends FunctionDescription{


    public Function4() {
        startCoef.put("A", 1.0);
        startCoef.put("B", 12.0);
        startCoef.put("C", -1000.0);
        startCoef.put("D", 2.0);
        setDefaultCoeff();
    }

    @Override
    public String getFunctionName() {
        return "y = A/(B*x+D) + C";
    }

    @Override
    public double compute(double x) {
        return get("A") / (get("B") * x + get("D")) + get("C");
    }
}
