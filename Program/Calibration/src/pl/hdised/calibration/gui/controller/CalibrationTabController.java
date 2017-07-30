package pl.hdised.calibration.gui.controller;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import pl.hdised.calibration.common.gui.fx.dialog.Dialog;
import pl.hdised.calibration.common.gui.fx.scene.LoadingScene;
import pl.hdised.calibration.common.gui.fx.util.SceneSwitcher;
import pl.hdised.calibration.common.neutralnetwork.NeutralNetworkController;
import pl.hdised.calibration.model.CalibrationData;

import java.io.IOException;

/**
 * Created by Sebastian Oprzędek on 16.07.2017.
 */
public class CalibrationTabController extends SceneSwitcher {
    private MainSceneController mainController;
    private NeutralNetworkController neutralNetworkController;
    @FXML
    private TextArea textArea;
    @FXML
    private TextField tankId;
    @FXML
    private TextField fuelHeight;
    @FXML
    private TextField fuelVolume;

    public CalibrationTabController(Scene defaultScene, MainSceneController tabController){
        super(defaultScene);
        this.mainController = tabController;
        neutralNetworkController = new NeutralNetworkController();
    }

    @FXML
    protected void train(ActionEvent event) throws IOException {
        Task task = new Task<String>() {
            protected String call() throws IOException {
                CalibrationData calibrationData = mainController.getCalibrationData();
                double[][] inputData = {calibrationData.getTankIds(), calibrationData.getFuelHeights()};
                double[][] outputData = {calibrationData.getFuelVolumes()};
                return neutralNetworkController.launchLearning(inputData, outputData);
            }
            @Override protected void cancelled() {
                super.cancelled();
                new Dialog(Alert.AlertType.INFORMATION, "Task cancelled", null, "Task cancelled").showAndWait();
                setDefaultScene();
            }
            @Override protected void succeeded() {
                super.succeeded();
                textArea.appendText(this.getValue());
                setDefaultScene();
            }
            @Override protected void failed() {
                super.failed();
                new Dialog(Alert.AlertType.ERROR, "Task failed", null, "Task failed").showAndWait();
                setDefaultScene();
            }
        };
        setScene(new LoadingScene(defaultScene, task));
        new Thread(task).start();
    }

    @FXML
    protected void test(ActionEvent event) throws IOException {
        double[] inputValues = { Double.parseDouble(tankId.getText()), Double.parseDouble(fuelHeight.getText())};
        fuelVolume.setText(Double.toString(neutralNetworkController.test(inputValues)[0]));
    }
}