package pl.hdised.calibration.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import pl.hdised.calibration.common.neutralnetwork.NeutralNetworkController;
import pl.hdised.calibration.model.CalibrationData;

import java.io.IOException;

/**
 * Created by Sebastian Oprzędek on 16.07.2017.
 */
public class CalibrationTabController {
    private MainSceneController mainController;
    @FXML
    private TextArea textArea;

    public CalibrationTabController(MainSceneController tabController){
        this.mainController = tabController;
    }

    @FXML
    protected void train(ActionEvent event) throws IOException {
        double[][] inputData = new double[1][];
        double[][] outputData = new double[2][];
        CalibrationData calibrationData = mainController.getCalibrationData();
        inputData[0] = calibrationData.getTankIds();
        outputData[0] = calibrationData.getFuelHeights();
        outputData[1] = calibrationData.getFuelVolumes();
        NeutralNetworkController neutralNetworkController = new NeutralNetworkController();
        textArea.appendText(neutralNetworkController.launchLearning(inputData, outputData));
    }

    @FXML
    protected void test(ActionEvent event) throws IOException {
    }

}
