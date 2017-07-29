package pl.hdised.calibration.gui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import pl.hdised.calibration.common.gui.fx.dialog.CancelInformationDialog;
import pl.hdised.calibration.common.gui.fx.dialog.WrongFilesDialogError;
import pl.hdised.calibration.common.neutralnetwork.NeutralNetworkController;
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
        Task task = new Task<String>() {
            protected String call() throws IOException {
                NeutralNetworkController neutralNetworkController = new NeutralNetworkController();
                StringBuilder input = new StringBuilder();
                CalibrationData calibrationData = new CalibrationData();
                for(Object item : filesTable.getItems())
                    if (((FilePosition) item).getChecked())
                        calibrationData.append(new TankMeasuresMeasuresInputReader(((FilePosition) item).getFilename()).getData());
                calibrationData.writeDataToFile("calibrationData.txt");
                mainController.setShowDataTab(calibrationData);
                return input.toString();
            }
            @Override protected void cancelled() {
                super.cancelled();
                new CancelInformationDialog().showAndWait();
                setDefaultScene();
            }
            @Override protected void succeeded() {
                super.succeeded();
                setDefaultScene();
            }
            @Override protected void failed() {
                super.failed();
                new WrongFilesDialogError().showAndWait();
                setDefaultScene();
            }
        };
        setScene(new LoadingScene(defaultScene, task));
        new Thread(task).start();
    }
}
