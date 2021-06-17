package me.dvsviridov.chartfx.utils;

import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class FxScheduler extends Scheduler {

    private final static FxScheduler m_instance = new FxScheduler();

    private FxScheduler() {}

    @NonNull
    @Override
    public Worker createWorker() {
        return new Worker() {

            private final CompositeDisposable disposable = new CompositeDisposable();
            @Override
            public void dispose() {
                disposable.dispose();
            }

            @Override
            public boolean isDisposed() {
                return disposable.isDisposed();
            }

            @NonNull
            @Override
            public Disposable schedule(@NonNull Runnable runnable, long l, @NonNull TimeUnit timeUnit) {
                Timer timer = new Timer();

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(runnable);
                    }
                }, timeUnit.toMillis(l));
                return disposable;
            }

            @NonNull
            @Override
            public Disposable schedule(@NonNull Runnable run) {
                return super.schedule(run);
            }
        };
    }

    public static FxScheduler getInstance() {
        return m_instance;
    }

}