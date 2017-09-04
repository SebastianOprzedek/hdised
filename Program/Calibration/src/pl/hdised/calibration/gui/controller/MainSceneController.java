package pl.hdised.calibration.gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import pl.hdised.calibration.model.CalibrationData;
import pl.hdised.calibration.util.function.DataSetterFunction;

import java.io.IOException;
import java.util.concurrent.Callable;

public class MainSceneController{
    private CalibrationData trainingData = new CalibrationData();
    private CalibrationData testData = new CalibrationData();
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab readTrainingDataTab;
    private ReadDataTabController readTrainingDataTabController;
    @FXML
    private Tab readTestDataTab;
    private ReadDataTabController readTestDataTabController;
    @FXML
    private Tab neuralNetworkTab;
    private NeuralNetworkTabController neuralNetworkTabController;
    @FXML
    private Tab machineLearningTab;
    private MachineLearningTabController machineLearningTabController;
    @FXML
    private Tab showTrainingDataTab;
    private ShowDataTabController showTrainingDataTabController;
    @FXML
    private Tab showTestDataTab;
    private ShowDataTabController showTestDataTabController;

    private Callable<CalibrationData> getTrainingDataFunction = new Callable<CalibrationData>() {
        @Override
        public CalibrationData call() throws Exception {
            return getTrainingData();
        }
    };
    private Callable<CalibrationData> getTestDataFunction = new Callable<CalibrationData>() {
        @Override
        public CalibrationData call() throws Exception {
            return getTestData();
        }
    };
    private DataSetterFunction setTrainingDataFunction = new DataSetterFunction() {
        @Override
        public Void call() throws Exception {
            setShowTrainingDataTab(this.calibrationData);
            return null;
        }
    };
    private DataSetterFunction setTestDataFunction = new DataSetterFunction() {
        @Override
        public Void call() throws Exception {
            setShowTestDataTab(this.calibrationData);
            return null;
        }
    };

    @FXML
    public void initializeTabs(Scene defaultScene) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/readDataTab.fxml"));
        loader.setController(readTrainingDataTabController = new ReadDataTabController(defaultScene, this, setTrainingDataFunction));
        readTrainingDataTab.setContent(loader.load());

        loader = new FXMLLoader(getClass().getResource("../view/readDataTab.fxml"));
        loader.setController(readTestDataTabController = new ReadDataTabController(defaultScene, this, setTestDataFunction));
        readTestDataTab.setContent(loader.load());

        loader = new FXMLLoader(getClass().getResource("../view/neuralNetworkTab.fxml"));
        loader.setController(neuralNetworkTabController = new NeuralNetworkTabController(defaultScene,this));
        neuralNetworkTab.setContent(loader.load());

        loader = new FXMLLoader(getClass().getResource("../view/machineLearningTab.fxml"));
        loader.setController(machineLearningTabController = new MachineLearningTabController(defaultScene,this));
        machineLearningTab.setContent(loader.load());

        loader = new FXMLLoader(getClass().getResource("../view/showDataTab.fxml"));
        loader.setController(showTrainingDataTabController = new ShowDataTabController(this, getTrainingDataFunction));
        showTrainingDataTab.setContent(loader.load());

        loader = new FXMLLoader(getClass().getResource("../view/showDataTab.fxml"));
        loader.setController(showTestDataTabController = new ShowDataTabController(this, getTestDataFunction));
        showTestDataTab.setContent(loader.load());
    }

    public void setTrainingData(CalibrationData trainingData){
        this.trainingData = trainingData;
        showTrainingDataTabController.updateData(trainingData);
    }

    public void setTestData(CalibrationData testData){
        this.testData = testData;
        showTestDataTabController.updateData(testData);
    }

    public CalibrationData getTrainingData() {
        return trainingData;
    }

    public CalibrationData getTestData() {
        return trainingData;
    }

    public void setShowTrainingDataTab(){
        tabPane.getSelectionModel().select(showTrainingDataTab);
    }

    public void setShowTestDataTab(){
        tabPane.getSelectionModel().select(showTestDataTab);
    }

    public void setReadTrainingDataTab(){
        tabPane.getSelectionModel().select(readTrainingDataTab);
    }

    public void setReadTestDataTab(){
        tabPane.getSelectionModel().select(readTrainingDataTab);
    }
    public void setNeuralNetworkTab(){
        tabPane.getSelectionModel().select(neuralNetworkTab);
    }

    public void setMachineLearningTab(){
        tabPane.getSelectionModel().select(neuralNetworkTab);
    }

    public void setShowTrainingDataTab(CalibrationData trainingData){
        setTrainingData(trainingData);
        setShowTrainingDataTab();
    }

    public void setShowTestDataTab(CalibrationData testData){
        setTestData(testData);
        setShowTestDataTab();
    }

}