package pl.hdised.calibration.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.hdised.calibration.gui.scenes.MainScene;

public class Main extends Application {

    @Override
    public void start(Stage mainStage) throws Exception{
        mainStage.setTitle("Calibration");
        mainStage.setScene(new MainScene(330, 330));
        mainStage.getScene();
        mainStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
