package pl.hdised.calibration.gui.scenes;

import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import pl.hdised.calibration.gui.controllers.LoadingSceneController;
import java.io.IOException;

/**
 * Created by Sebastian Oprzędek on 16.07.2017.
 */
public class LoadingScene extends Scene{

    public LoadingScene(Scene previousScene, Task task) throws IOException{
        super(new Parent() {});
        FXMLLoader loader = new FXMLLoader(LoadingScene.class.getResource("../view/loadingScene.fxml"));
        loader.setController(new LoadingSceneController(this, task));
        this.setRoot(loader.load());
    }

}
