package pl.hdised.calibration.gui.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import pl.hdised.calibration.common.gui.fx.util.SceneSwitcher;
import pl.hdised.calibration.common.neuralnetwork.NeuralNetworkController;
import pl.hdised.calibration.common.neuralnetwork.model.NeuralNetworkDataPosition;
import pl.hdised.calibration.model.CalibrationData;
import pl.hdised.calibration.util.algorithm.ClassifierCreator;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.Prediction;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebastian Oprzędek on 03.09.2017.
 */
public class MachineLearningTabController extends SceneSwitcher {
    private MainSceneController mainController;
    private CalibrationData trainingData;
    private CalibrationData testData;
    private ArrayList<Attribute> attributeList;
    @FXML
    private ComboBox comboBox;
    @FXML
    private TextArea textArea;

    public MachineLearningTabController(Scene defaultScene, MainSceneController tabController) {
        super(defaultScene);
        this.mainController = tabController;
    }

    @FXML
    public void initialize(){
        attributeList = new ArrayList<>();
        attributeList.add(new Attribute("tankId"));
        attributeList.add(new Attribute("fuelHeight"));
        attributeList.add(new Attribute("fuelVolume"));

        List<String> list = new ClassifierCreator().getNames();
        list.add("NeuralNetwork");
        comboBox.setItems(FXCollections.observableArrayList(list));
    }

    @FXML
    protected void onlyResult(ActionEvent event) throws Exception {
        test(false);
    }

    @FXML
    protected void allData(ActionEvent event) throws Exception {
        test(true);
    }

    private void test(Boolean allData) throws Exception{
        trainingData = mainController.getTrainingData();
        testData = mainController.getTestData();
        if((String) comboBox.getValue() == "NeuralNetwork")
            printNeuralNetworkResult(trainingData, testData, allData);
        else {
            Instances instances = new Instances("instances", attributeList, trainingData.getLength());
            Instances testInstances = new Instances("testInstances", attributeList, testData.getLength());
            setInstances(instances, trainingData);
            setInstances(testInstances, testData);
            printMachineLearningResult((String) comboBox.getValue(), instances, testInstances, allData);
        }
    }

    private void setInstances(Instances instances, CalibrationData calibrationData){
        instances.setClassIndex(instances.numAttributes() - 1);
        for(int i=0; i<calibrationData.getLength(); i++) {
            Instance inst = new DenseInstance(3);
            inst.setValue(attributeList.get(0), calibrationData.getForIndex(i).getTankId());
            inst.setValue(attributeList.get(1), calibrationData.getForIndex(i).getFuelHeight());
            inst.setValue(attributeList.get(2), calibrationData.getForIndex(i).getFuelVolume());
            instances.add(inst);
        }
    }

    private void printNeuralNetworkResult(CalibrationData trainingData, CalibrationData testData, Boolean allData) throws Exception{
        NeuralNetworkController neuralNetworkController = new NeuralNetworkController();
        CalibrationData calibrationData = mainController.getTrainingData();
        neuralNetworkController.launchLearning(new double[][] {calibrationData.getTankIds(), calibrationData.getFuelHeights()}, new double[][]{calibrationData.getFuelVolumes()});
        neuralNetworkController.launchTesting(new double[][] {calibrationData.getTankIds(), calibrationData.getFuelHeights()}, new double[][]{calibrationData.getFuelVolumes()});
        textArea.clear();
        textArea.appendText(neuralNetworkController.toSummaryString("NeutralNetwork\nResults\n======\n"));
        if(allData)
            textArea.appendText(positionListToString(neuralNetworkController.getTestPositions()));
    }

    private void printMachineLearningResult(String algorithm, Instances trainingInstances, Instances testInstances, Boolean allData) throws Exception{
        Classifier tree = new ClassifierCreator().createClassifier(algorithm);
        tree.buildClassifier(trainingInstances);   // build classifier
        Evaluation eval = new Evaluation(trainingInstances);
        eval.evaluateModel(tree, testInstances);
        textArea.clear();
        textArea.appendText(eval.toSummaryString(algorithm+"\nResults\n======\n", true));
        if(allData)
            textArea.appendText(predictionListToString(eval.predictions()));
    }

    private String predictionListToString(List<Prediction> predictionList){
        StringBuilder tmp = new StringBuilder();
        List<Double> occurred = new ArrayList<>();
        for(Prediction prediction : predictionList) {
            if(!occurred.contains(prediction.actual()))
            {
                occurred.add(prediction.actual());
                tmp.append("\nActual: ").append(String.format("%.6f", prediction.actual())).append("\t\tPredicted: ").append(String.format("%.6f", prediction.predicted()));
            }
        }
        return tmp.toString();
    }

    private String positionListToString(NeuralNetworkDataPosition[] positions){
        StringBuilder tmp = new StringBuilder();
        List<Double> occurred = new ArrayList<>();
        for(int i=0; i<positions.length; i++){
            if(!occurred.contains(positions[i].getTargetValues()[0]))
            {
                occurred.add(positions[i].getTargetValues()[0]);
                tmp.append("\nActual: ").append(String.format("%.6f", positions[i].getTargetValues()[0])).append("\t\tPredicted: ").append(String.format("%.6f", positions[i].getResultValues()[0]));
            }
        }
        return tmp.toString();
    }

}