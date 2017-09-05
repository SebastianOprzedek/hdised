package pl.hdised.calibration.gui.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import pl.hdised.calibration.common.gui.fx.util.SceneSwitcher;
import pl.hdised.calibration.model.CalibrationData;
import pl.hdised.calibration.util.algorithm.ClassifierCreator;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import java.util.ArrayList;

/**
 * Created by Sebastian Oprzędek on 16.07.2017.
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

        comboBox.setItems(FXCollections.observableArrayList(new ClassifierCreator().getNames()));
    }

    @FXML
    protected void launch(ActionEvent event) throws Exception {
        trainingData = mainController.getTrainingData();
        testData = mainController.getTestData();
        Instances instances = new Instances("instances", attributeList, trainingData.getLength());
        Instances testInstances = new Instances("testInstances", attributeList, testData.getLength());
        setInstances(instances, trainingData);
        setInstances(testInstances, testData);
//        for(String algorithmName : new ClassifierCreator().getNames()) {
//            try {
//                test(algorithmName, instances, testInstances);
//            }
//            catch (Exception e){
//                System.out.println("Error for: " + algorithmName+"\n");
////                e.printStackTrace();
//            }
//        }
        test((String) comboBox.getValue(), instances, testInstances);
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

    private void test(String algorithm, Instances trainingInstances, Instances testInstances) throws Exception{
        Classifier tree = new ClassifierCreator().createClassifier(algorithm);
        tree.buildClassifier(trainingInstances);   // build classifier
        Evaluation eval = new Evaluation(trainingInstances);
        eval.evaluateModel(tree, testInstances);
        textArea.appendText(eval.toSummaryString("\n\n"+algorithm+"\nResults\n======\n", true));
    }

}