package pl.hdised.calibration.common.gui.fx.controller;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import pl.hdised.calibration.common.gui.fx.util.SceneSwitcher;

/**
 * Created by Sebastian Oprzędek on 15.07.2017.
 */

public class LoadingSceneController extends SceneSwitcher {
    private Task task;

    public LoadingSceneController(Scene defaultScene, Task task){
        super(defaultScene);
        this.task = task;
    }

    @FXML
    protected void abort(ActionEvent event) {
        task.cancel();
    }
}
