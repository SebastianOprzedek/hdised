package pl.hdised.calibration.gui.scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import pl.hdised.calibration.gui.controllers.MainSceneController;

import java.io.IOException;

/**
 * Created by Sebastian Oprzędek on 16.07.2017.
 */
public class MainScene extends Scene {

    public MainScene(int width, int height) throws IOException {
        super(new Parent() {}, width, height);
        FXMLLoader loader = new FXMLLoader(LoadingScene.class.getResource("../view/mainScene.fxml"));
        loader.setController(new MainSceneController(this));
        this.setRoot(loader.load());
    }

}
