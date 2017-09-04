package pl.hdised.calibration.gui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import pl.hdised.calibration.model.CalibrationData;
import pl.hdised.calibration.model.CalibrationDataPosition;

import java.util.concurrent.Callable;

/**
 * Created by Sebastian Oprzędek on 28.07.2017.
 */
public class ShowDataTabController {
    private MainSceneController mainController;
    @FXML
    private TableView dataTable;
    ObservableList<CalibrationDataPosition> data;
    Callable<CalibrationData> getDataFunction;

    public ShowDataTabController(MainSceneController tabController, Callable<CalibrationData> getDataFunction){
        this.mainController = tabController;
        this.getDataFunction = getDataFunction;
    }

    @FXML
    public void initialize() throws Exception{
        dataTable.setEditable(true);
        data = FXCollections.observableArrayList(getDataFunction.call().getPositions());
        dataTable.setItems(data);
    }

    public void updateData(CalibrationData calibrationData){
        data = FXCollections.observableArrayList(calibrationData.getPositions());
        dataTable.setItems(data);
    }
}
