package me.dvsviridov.chartfx.model;

import java.util.List;

// класс, представляющий сырые данные от контроллера - строка со значениями, разделенными пробелами
public class RawData {

    // в рамках теста данные вида "1123 1231 23" где первое число - x, второе - y и третье - z
    private String data;

    public RawData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public static Double[] getParsedSeparatedValues(RawData data) {
        String[] separated = data.getData().split(" ");
        return new Double[] {
                Double.valueOf(separated[0]),
                Double.valueOf(separated[1]),
                Double.valueOf(separated[2])
        };
    }
}
