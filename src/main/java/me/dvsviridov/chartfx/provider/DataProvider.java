package me.dvsviridov.chartfx.provider;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import me.dvsviridov.chartfx.model.RawData;
import me.dvsviridov.chartfx.model.TransformedData;
import me.dvsviridov.chartfx.utils.AdapterUtils;
import org.springframework.stereotype.Component;


@Component
public class DataProvider { // класс, задача которого поставлять данные контроллеру в понятном ему виде

    public Observable<TransformedData> startListening() {
        return DataListener.startListen()
                .observeOn(Schedulers.computation())
                .map(rawData -> {
                    var values = RawData.getParsedSeparatedValues(rawData);
                    return new TransformedData(values[0], values[1], values[2]);
                })
                .buffer(100)
                .map(AdapterUtils::getAverage);
    }
}
