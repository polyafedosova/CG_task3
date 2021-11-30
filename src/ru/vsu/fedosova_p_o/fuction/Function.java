package ru.vsu.fedosova_p_o.fuction;

import ru.vsu.fedosova_p_o.Parameters;

public abstract class Function
{
    protected int numberOfParameters;
    protected Parameters parameters;
    protected String stringFunction;

    public abstract double calculateFunctionPoint(double x);

    public Parameters getParameters()
    {
        return parameters;
    }

    public void setParameters(Parameters parameters)
    {
        this.parameters = parameters;
    }

    public int getNumberOfParameters()
    {
        return numberOfParameters;
    }

    @Override
    public String toString()
    {
        return stringFunction;
    }
}
