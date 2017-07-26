package pl.hdised.calibration.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import pl.hdised.calibration.common.neutralnetwork.NeutralNetworkController;

import java.io.IOException;

/**
 * Created by Sebastian Oprzędek on 16.07.2017.
 */
public class CalibrationTabController {

    @FXML
    private TextArea textArea;


    @FXML
    protected void readTrainingData(ActionEvent event) throws IOException {
        String s  = new NeutralNetworkController().launchLearning();

        textArea.appendText(s);
    }
}
