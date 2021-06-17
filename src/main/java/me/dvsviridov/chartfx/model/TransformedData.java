package me.dvsviridov.chartfx.model;

// класс-обертка для разобранных данных
public class TransformedData {

    private double x, y, z;

    public TransformedData(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public TransformedData() {}

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
