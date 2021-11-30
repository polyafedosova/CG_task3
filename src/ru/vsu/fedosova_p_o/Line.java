package ru.vsu.fedosova_p_o;

public class Line
{
    private final RealPoint point1, point2;

    public Line(RealPoint point1, RealPoint point2)
    {
        this.point1 = point1;
        this.point2 = point2;
    }

    public RealPoint getPoint1()
    {
        return point1;
    }

    public RealPoint getPoint2()
    {
        return point2;
    }
}
