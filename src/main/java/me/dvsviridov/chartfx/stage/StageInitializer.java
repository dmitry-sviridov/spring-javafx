package me.dvsviridov.chartfx.stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.dvsviridov.chartfx.FXChartApplication.StageReadyEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {

    @Value("classpath:/mainUI.fxml")
    private Resource mainUIResource;
    private final String appTitle;
    private ApplicationContext applicationContext;

    public StageInitializer(
            @Value("${spring.application.ui.title}") String appTitle,
            ApplicationContext applicationContext) {
        this.appTitle = appTitle;
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent stageReadyEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(mainUIResource.getURL());
            fxmlLoader.setControllerFactory(aClass -> applicationContext.getBean(aClass));
            Parent parent = fxmlLoader.load();


            Stage stage = stageReadyEvent.getStage();
            Scene scene = new Scene(parent, 800, 600);

            stage.setScene(scene);
            stage.setTitle(appTitle);
            stage.show();

            stage.setMinWidth(stage.getWidth());
            stage.setMinHeight(stage.getHeight());

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

}
