package ru.vsu.fedosova_p_o.fuction;

public class Function6 extends FunctionDescription{


    public Function6() {
        startCoef.put("A", 1.0);
        startCoef.put("C", 12.0);
        setDefaultCoeff();
    }

    @Override
    public String getFunctionName() {
        return "y = A* 1/(1+e^(-x)) + C";
    }

    @Override
    public double compute(double x) {
        return get("A") * 1/(1 + Math.pow(Math.E, -x)) + get("C");
    }
}
