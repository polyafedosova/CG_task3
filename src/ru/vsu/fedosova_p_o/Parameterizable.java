package ru.vsu.fedosova_p_o;

import ru.vsu.fedosova_p_o.fuction.FunctionDescription;

public interface Parameterizable
{
    FunctionDescription getFunction();

    void setFunction(FunctionDescription function);
}
