package me.dvsviridov.chartfx.utils;

import me.dvsviridov.chartfx.model.TransformedData;

import java.util.List;

public class AdapterUtils {

    public static TransformedData getAverage(List<TransformedData> dataList) {

        TransformedData transformedData = dataList.stream()
                .reduce((first, second) -> new TransformedData(
                        first.getX() + second.getX(),
                        first.getY() + second.getY(),
                        first.getZ() + second.getZ()
                ))
                .get();

        transformedData.setX(transformedData.getX() / dataList.size());
        transformedData.setY(transformedData.getY() / dataList.size());
        transformedData.setZ(transformedData.getZ() / dataList.size());

        return transformedData;
    }
}
