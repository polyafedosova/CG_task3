package ru.vsu.fedosova_p_o.fuction;

public class Function3 extends FunctionDescription{

    public Function3() {
        startCoef.put("A", 1.0);
        startCoef.put("B", 12.0);
        startCoef.put("C", -2.0);
        startCoef.put("D", 2.0);
        setDefaultCoeff();
    }

    @Override
    public String getFunctionName() {
        return "y = A*x^3 + B*x^2 + C*x + D";
    }

    @Override
    public double compute(double x) {
        return get("A")*Math.pow(x, 3) + get("B")*Math.pow(x, 2) + get("C")*x + get("D");
    }
}
