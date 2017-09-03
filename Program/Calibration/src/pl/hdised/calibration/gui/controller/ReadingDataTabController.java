package pl.hdised.calibration.gui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import pl.hdised.calibration.common.gui.fx.dialog.Dialog;
import pl.hdised.calibration.common.neuralnetwork.NeuralNetworkController;
import pl.hdised.calibration.common.resourcehelper.ResourceHelper;
import pl.hdised.calibration.model.FilePosition;
import pl.hdised.calibration.common.gui.fx.scene.LoadingScene;
import pl.hdised.calibration.common.gui.fx.util.SceneSwitcher;
import pl.hdised.calibration.model.CalibrationData;
import pl.hdised.calibration.input.TankMeasuresMeasuresInputReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebastian Oprzędek on 16.07.2017.
 */
public class ReadingDataTabController extends SceneSwitcher {
    private MainSceneController mainController;
    @FXML
    private TableView filesTable;

    private List<String> filenameList = new ArrayList<String>(){{
        ResourceHelper resourceHelper = new ResourceHelper();
        add(resourceHelper.get("OrginalTankMeasures1"));
        add(resourceHelper.get("OrginalTankMeasures2"));
        add(resourceHelper.get("OrginalTankMeasures3"));
        add(resourceHelper.get("DeformedTankMeasures1"));
        add(resourceHelper.get("DeformedTankMeasures2"));
        add(resourceHelper.get("DeformedTankMeasures3"));
    }};

    ReadingDataTabController(Scene defaultScene, MainSceneController tabController) {
        super(defaultScene);
        this.mainController = tabController;
    }

    @FXML
    public void initialize(){
        filesTable.setEditable(true);
        ObservableList<FilePosition> data = FXCollections.observableArrayList();
        filenameList.forEach(filename -> data.add(new FilePosition(filename, true)));
        filesTable.setItems(data);
    }

    @FXML
    protected void readData(ActionEvent event) throws IOException {
        final CalibrationData calibrationData = new CalibrationData();
        Task task = new Task<String>() {
            protected String call() throws IOException {
                NeuralNetworkController neuralNetworkController = new NeuralNetworkController();
                StringBuilder input = new StringBuilder();
                for(Object item : filesTable.getItems())
                    if (((FilePosition) item).getChecked())
                        calibrationData.append(new TankMeasuresMeasuresInputReader(((FilePosition) item).getFilename()).getData());
                calibrationData.writeDataToFile("calibrationData.txt");
                return input.toString();
            }
            @Override protected void cancelled() {
                super.cancelled();
                new Dialog(Alert.AlertType.INFORMATION, "Task cancelled", null, "Task cancelled").showAndWait();
                setDefaultScene();
            }
            @Override protected void succeeded() {
                super.succeeded();
                mainController.setShowDataTab(calibrationData);
                setDefaultScene();
            }
            @Override protected void failed() {
                super.failed();
                new Dialog(Alert.AlertType.ERROR, "Task failed", "Task failed", "Input files are incorrect").showAndWait();
                setDefaultScene();
            }
        };
        setScene(new LoadingScene(defaultScene, task));
        new Thread(task).start();
    }
}
