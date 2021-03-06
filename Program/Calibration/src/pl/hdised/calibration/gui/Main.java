package pl.hdised.calibration.gui;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import pl.hdised.calibration.common.resourcehelper.ResourceHelper;
import pl.hdised.calibration.gui.scene.MainScene;

public class Main extends Application {

    @Override
    public void start(Stage mainStage) throws Exception{
        mainStage.setTitle("Calibration");
        mainStage.setScene(new MainScene());
        mainStage.getScene();
        mainStage.getIcons().add(new Image(new ResourceHelper().get("MainIcon")));
        mainStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
