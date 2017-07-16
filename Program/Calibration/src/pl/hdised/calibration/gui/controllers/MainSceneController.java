package pl.hdised.calibration.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import java.io.IOException;

public class MainSceneController{
    @FXML
    private Tab readingDataTab;

    @FXML
    private Tab calibrationTab;

    @FXML
    public void initializeTabs(Scene defaultScene) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/readingDataTab.fxml"));
        loader.setController(new ReadingDataTabController(defaultScene));
        readingDataTab.setContent(loader.load());

        loader = new FXMLLoader(getClass().getResource("../view/calibrationTab.fxml"));
        loader.setController(new CalibrationTabController());
        calibrationTab.setContent(loader.load());
    }

}