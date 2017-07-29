package pl.hdised.calibration.common.gui.fx.util;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Sebastian Oprzędek on 16.07.2017.
 */
public abstract class SceneSwitcher {
    protected Scene defaultScene;
    private Scene currentScene;

    protected SceneSwitcher(Scene defaultScene){
        this.defaultScene = defaultScene;
        this.currentScene = defaultScene;
    }

    protected void setScene(Scene scene){
        Stage currentStage = (Stage) currentScene.getWindow();
        currentStage.setScene(scene);
        currentScene = scene;
    }

    protected void setDefaultScene(){
        setScene(defaultScene);
    }
}
