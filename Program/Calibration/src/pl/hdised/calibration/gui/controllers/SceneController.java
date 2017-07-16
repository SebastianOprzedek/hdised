package pl.hdised.calibration.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Sebastian Oprzędek on 16.07.2017.
 */
abstract class SceneController {
    @FXML
    private Scene defaultScene;
    @FXML
    private Scene currentScene;

    SceneController(Scene defaultScene){
        this.defaultScene = defaultScene;
        this.currentScene = defaultScene;
    }

    void setScene(Scene scene){
        Stage currentStage = (Stage) currentScene.getWindow();
        currentStage.setScene(scene);
        currentScene = scene;
    }

    void setDefaultScene(){
        setScene(defaultScene);
    }
}
