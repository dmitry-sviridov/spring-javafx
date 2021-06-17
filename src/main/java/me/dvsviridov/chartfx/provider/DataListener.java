package me.dvsviridov.chartfx.provider;

import io.reactivex.Observable;
import me.dvsviridov.chartfx.model.RawData;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class DataListener { // класс, задача которого поставлять данные от датчика

    // заглушка для асинхронной генерации данных
    public static Observable<RawData> startListen() {
        return Observable
                .interval(10L, TimeUnit.MILLISECONDS)
                .map(aLong -> {
                    int first = new Random().nextInt(100);
                    int second = new Random().nextInt(100);
                    int third = new Random().nextInt(100);

                    String result = String.format("%d %d %d", first, second, third);
                    return new RawData(result);
                });
    }
}
