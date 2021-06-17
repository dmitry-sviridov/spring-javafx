package me.dvsviridov.chartfx.controller;

import io.reactivex.schedulers.Schedulers;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import me.dvsviridov.chartfx.provider.DataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChartController {

    private final long initialTime;

    private final DataProvider dataProvider;

    ObservableList<XYChart.Series<Long, Double>> xValuesSeries;
    ObservableList<XYChart.Series<Long, Double>> yValuesSeries;
    ObservableList<XYChart.Series<Long, Double>> zValuesSeries;

    ObservableList<XYChart.Data<Long, Double>> xValuesData = FXCollections.observableArrayList();
    ObservableList<XYChart.Data<Long, Double>> yValuesData = FXCollections.observableArrayList();
    ObservableList<XYChart.Data<Long, Double>> zValuesData = FXCollections.observableArrayList();

    @FXML
    XYChart<Long, Double> chartXValue;
    @FXML
    public NumberAxis chartXValueXAxis;
    @FXML
    public NumberAxis chartXValueYAxis;


    @FXML
    XYChart<Long, Double> chartYValue;
    @FXML
    public NumberAxis chartYValueXAxis;
    @FXML
    public NumberAxis chartYValueYAxis;


    @FXML
    XYChart<Long, Double> chartZValue;
    @FXML
    public NumberAxis chartZValueXAxis;
    @FXML
    public NumberAxis chartZValueYAxis;


    public ChartController(@Autowired DataProvider dataProvider) {
        this.dataProvider = dataProvider;
        this.initialTime = System.currentTimeMillis();
    }

    @FXML
    public void initialize() {
        xValuesSeries = FXCollections.observableArrayList();
        xValuesSeries.add(new XYChart.Series<>(xValuesData));

        yValuesSeries = FXCollections.observableArrayList();
        yValuesSeries.add(new XYChart.Series<>(yValuesData));

        zValuesSeries = FXCollections.observableArrayList();
        zValuesSeries.add(new XYChart.Series<>(zValuesData));

        chartXValue.setData(xValuesSeries);
        chartYValue.setData(yValuesSeries);
        chartZValue.setData(zValuesSeries);

        chartXValue.setLegendVisible(false);
        chartYValue.setLegendVisible(false);
        chartZValue.setLegendVisible(false);

        dataProvider
                .startListening()
                .subscribeOn(Schedulers.computation())
                .subscribe(
                        receivedData -> {
                            long sinceStart = System.currentTimeMillis() - initialTime;

                            Platform.runLater(() -> {
                                
                                xValuesData.add(new XYChart.Data<>(sinceStart, receivedData.getX()));

                                yValuesData.add(new XYChart.Data<>(sinceStart, receivedData.getY()));

                                zValuesData.add(new XYChart.Data<>(sinceStart, receivedData.getZ()));
                            });
                        });
    }
}