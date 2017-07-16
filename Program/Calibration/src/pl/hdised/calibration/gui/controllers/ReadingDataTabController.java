package pl.hdised.calibration.gui.controllers;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import pl.hdised.calibration.common.ResourceHelper;
import pl.hdised.calibration.gui.scenes.LoadingScene;
import pl.hdised.calibration.input.TankMeasuresInputReader;

import java.io.IOException;

/**
 * Created by Sebastian Oprzędek on 16.07.2017.
 */
public class ReadingDataTabController extends SceneController {
    @FXML
    private TextArea textArea;

    ReadingDataTabController(Scene defaultScene){
        super(defaultScene);
        //TODO: file list in table
    }

    @FXML
    protected void readData(ActionEvent event) throws IOException {
        Task task = new Task<String>() {
            protected String call() throws IOException {
                return new TankMeasuresInputReader().get();
            }
            @Override protected void cancelled() {
                super.cancelled();
                textArea.setText("Task cancelled");
                setDefaultScene();
            }
            @Override protected void succeeded() {
                super.succeeded();
                textArea.setText("Task succeeded. Input:\n\n"+this.getValue());
                setDefaultScene();
            }
            @Override protected void failed() {
                super.failed();
                textArea.setText("Task failed\nCheck if file \"" + ResourceHelper.getResource("TankMeasuresPath") + "\" exists");
                setDefaultScene();
            }
        };
        setScene(new LoadingScene(defaultScene, task));
        new Thread(task).start();
    }
}
