package pl.hdised.calibration.gui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import pl.hdised.calibration.common.gui.fx.dialog.Dialog;
import pl.hdised.calibration.common.gui.fx.scene.LoadingScene;
import pl.hdised.calibration.common.gui.fx.util.SceneSwitcher;
import pl.hdised.calibration.common.neuralnetwork.NeuralNetworkController;
import pl.hdised.calibration.common.neuralnetwork.model.NeuralNetworkDataPosition;
import pl.hdised.calibration.model.CalibrationData;
import pl.hdised.calibration.model.CalibrationTrainingDataPosition;

import java.io.IOException;

/**
 * Created by Sebastian Oprzędek on 16.07.2017.
 */
public class NeuralNetworkTabController extends SceneSwitcher {
    private MainSceneController mainController;
    private NeuralNetworkController neuralNetworkController;
    private ObservableList<CalibrationTrainingDataPosition> data;
    @FXML
    private TextField tankId;
    @FXML
    private TextField fuelHeight;
    @FXML
    private TextField fuelVolume;
    @FXML
    private TableView trainingDataPositionTable;

    public NeuralNetworkTabController(Scene defaultScene, MainSceneController tabController){
        super(defaultScene);
        this.mainController = tabController;
        neuralNetworkController = new NeuralNetworkController();
    }

    @FXML
    public void initialize(){
        trainingDataPositionTable.setEditable(true);
        data = FXCollections.observableArrayList();
        trainingDataPositionTable.setItems(data);
    }

    @FXML
    protected void train(ActionEvent event) throws IOException {
        Task task = new Task<NeuralNetworkDataPosition[]>() {
            protected NeuralNetworkDataPosition[] call() throws IOException {
                CalibrationData calibrationData = mainController.getTrainingData();
                double[][] inputData = {calibrationData.getTankIds(), calibrationData.getFuelHeights()};
                double[][] outputData = {calibrationData.getFuelVolumes()};
                return neuralNetworkController.launchLearning(inputData, outputData);
            }
            @Override protected void cancelled() {
                super.cancelled();
                new Dialog(Alert.AlertType.INFORMATION, "Task cancelled", null, "Task cancelled").showAndWait();
                setDefaultScene();
            }
            @Override protected void succeeded() {
                super.succeeded();
                updateTable(this.getValue());
                setDefaultScene();
            }
            @Override protected void failed() {
                super.failed();
                new Dialog(Alert.AlertType.ERROR, "Task failed", "Task failed", "Calibration data is incorrect").showAndWait();
                setDefaultScene();
            }
        };
        setScene(new LoadingScene(defaultScene, task));
        new Thread(task).start();
    }

    private void updateTable(NeuralNetworkDataPosition[] neuralNetworkDataPositions) {
        data = FXCollections.observableArrayList();
        for (NeuralNetworkDataPosition neuralNetworkDataPosition : neuralNetworkDataPositions)
            data.add(new CalibrationTrainingDataPosition(neuralNetworkDataPosition));
        trainingDataPositionTable.setItems(data);
    }

    @FXML
    protected void test(ActionEvent event) throws IOException {
        double[] inputValues = { Double.parseDouble(tankId.getText()), Double.parseDouble(fuelHeight.getText())};
        fuelVolume.setText(Double.toString(neuralNetworkController.test(inputValues)[0]));
    }
}