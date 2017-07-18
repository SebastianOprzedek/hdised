package pl.hdised.calibration.gui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import pl.hdised.calibration.common.resourcehelper.ResourceHelper;
import pl.hdised.calibration.gui.models.FileTablePosition;
import pl.hdised.calibration.gui.scenes.LoadingScene;
import pl.hdised.calibration.gui.utils.SceneSwitcher;
import pl.hdised.calibration.input.TankMeasuresInputReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebastian Oprzędek on 16.07.2017.
 */
public class ReadingDataTabController extends SceneSwitcher {
    @FXML
    private TextArea textArea;

    @FXML
    private TableView filesTable;

    @FXML
    private List<String> filenameList = new ArrayList<String>(){{
        ResourceHelper resourceHelper = new ResourceHelper();
        add(resourceHelper.get("OrginalTankMeasures1"));
        add(resourceHelper.get("OrginalTankMeasures2"));
        add(resourceHelper.get("OrginalTankMeasures3"));
        add(resourceHelper.get("DeformedTankMeasures1"));
        add(resourceHelper.get("DeformedTankMeasures2"));
        add(resourceHelper.get("DeformedTankMeasures3"));
    }};

    ReadingDataTabController(Scene defaultScene) {
        super(defaultScene);
    }

    @FXML
    public void initialize(){
        filesTable.setEditable(true);
        ObservableList<FileTablePosition> data = FXCollections.observableArrayList();
        filenameList.forEach(filename -> data.add(new FileTablePosition(filename, true)));
        filesTable.setItems(data);
    }

    @FXML
    protected void readData(ActionEvent event) throws IOException {
        Task task = new Task<String>() {
            protected String call() throws IOException {
                StringBuilder input = new StringBuilder();
                for(Object item : filesTable.getItems()) {
                    if (((FileTablePosition) item).getChecked())
                        input.append(new TankMeasuresInputReader(((FileTablePosition) item).getFilename()).get());
                }
                return input.toString();
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
                textArea.setText("Task failed\nCheck files paths.");
                setDefaultScene();
            }
        };
        setScene(new LoadingScene(defaultScene, task));
        new Thread(task).start();
    }
}
