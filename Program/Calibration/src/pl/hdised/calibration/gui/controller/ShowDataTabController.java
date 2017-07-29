package pl.hdised.calibration.gui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import pl.hdised.calibration.model.CalibrationData;
import pl.hdised.calibration.model.CalibrationDataPosition;

/**
 * Created by Sebastian Oprzędek on 28.07.2017.
 */
public class ShowDataTabController {
    private MainSceneController mainController;
    @FXML
    private TableView dataTable;
    private CalibrationData calibrationData;
    ObservableList<CalibrationDataPosition> data;

    public ShowDataTabController(MainSceneController tabController){
        this.mainController = tabController;
    }

    @FXML
    public void initialize(){
        dataTable.setEditable(true);
        data = FXCollections.observableArrayList(mainController.getCalibrationData().getPositions());
        dataTable.setItems(data);
    }

    public void updateData(CalibrationData calibrationData){
        data = FXCollections.observableArrayList(calibrationData.getPositions());
        dataTable.setItems(data);
    }
}
