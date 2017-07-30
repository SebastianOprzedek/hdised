package pl.hdised.calibration.gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import pl.hdised.calibration.model.CalibrationData;
import java.io.IOException;

public class MainSceneController{
    private CalibrationData calibrationData = new CalibrationData();
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab readingDataTab;
    private ReadingDataTabController readingDataTabController;
    @FXML
    private Tab calibrationTab;
    private CalibrationTabController calibrationTabController;
    @FXML
    private Tab showDataTab;
    private ShowDataTabController showDataTabController;

    @FXML
    public void initializeTabs(Scene defaultScene) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/readingDataTab.fxml"));
        loader.setController(readingDataTabController = new ReadingDataTabController(defaultScene, this));
        readingDataTab.setContent(loader.load());

        loader = new FXMLLoader(getClass().getResource("../view/calibrationTab.fxml"));
        loader.setController(calibrationTabController = new CalibrationTabController(defaultScene,this));
        calibrationTab.setContent(loader.load());

        loader = new FXMLLoader(getClass().getResource("../view/showDataTab.fxml"));
        loader.setController(showDataTabController = new ShowDataTabController(this));
        showDataTab.setContent(loader.load());
    }

    public void setCalibrationData(CalibrationData calibrationData){
        this.calibrationData = calibrationData;
        showDataTabController.updateData(calibrationData);
    }

    public CalibrationData getCalibrationData() {
        return calibrationData;
    }

    public void setShowDataTab(){
        tabPane.getSelectionModel().select(showDataTab);
    }

    public void setReadingDataTab(){
        tabPane.getSelectionModel().select(readingDataTab);
    }

    public void setCalibrationTab(){
        tabPane.getSelectionModel().select(calibrationTab);
    }

    public void setShowDataTab(CalibrationData calibrationData){
        setCalibrationData(calibrationData);
        setShowDataTab();
    }

}