package ru.vsu.fedosova_p_o.fuction;

public class Function5 extends FunctionDescription{


    public Function5() {
        startCoef.put("A", 1.0);
        startCoef.put("W", 12.0);
        startCoef.put("F", -1000.0);
        startCoef.put("T", 2.0);
        startCoef.put("C", 2.0);
        setDefaultCoeff();
    }

    @Override
    public String getFunctionName() {
        return "y = A*sin(W*x + F)*(e^(-x/T))+C";
    }

    @Override
    public double compute(double x) {
        return get("A") * Math.sin(get("W") * x + get("F")) * (Math.pow(Math.E, -x/get("T"))) + get("C");
    }
}
