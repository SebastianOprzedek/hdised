package pl.hdised.calibration.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import pl.hdised.calibration.common.gui.fx.util.SceneSwitcher;
import pl.hdised.calibration.model.CalibrationData;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.NaiveBayesUpdateable;
import weka.classifiers.functions.SMO;
import weka.classifiers.trees.DecisionStump;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.gui.beans.DataSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Sebastian Oprzędek on 16.07.2017.
 */
public class MachineLearningTabController extends SceneSwitcher {
    private MainSceneController mainController;

    @FXML
    private TextArea textArea;

    public MachineLearningTabController(Scene defaultScene, MainSceneController tabController) {
        super(defaultScene);
        this.mainController = tabController;
    }

    @FXML
    protected void launch(ActionEvent event) throws Exception {
        CalibrationData calibrationData = mainController.getCalibrationData();
        double[][] inputData = {calibrationData.getTankIds(), calibrationData.getFuelHeights()};
        double[][] outputData = {calibrationData.getFuelVolumes()};
        ArrayList<Attribute> attributeList = new ArrayList<Attribute>();
        attributeList.add(new Attribute("tankId"));
        attributeList.add(new Attribute("fuelHeight"));
        attributeList.add(new Attribute("fuelVolume"));
        Instances instances = new Instances("instances", attributeList, 50);
        Instances testInstances = new Instances("testInstances", attributeList, 10);
        instances.setClassIndex(instances.numAttributes() - 1);
        testInstances.setClassIndex(testInstances.numAttributes() - 1);
        for(int i=0; i<50; i++) {
            Instance inst = new DenseInstance(3);
            inst.setValue(attributeList.get(0), i);
            inst.setValue(attributeList.get(1), 2*i);
            inst.setValue(attributeList.get(2), 3*i);
            instances.add(inst);
        }
        for(int i=0; i<50; i++) {
            Instance inst = new DenseInstance(3);
            inst.setValue(attributeList.get(0), i + new Random().nextDouble() - 0.5);
            inst.setValue(attributeList.get(1), 2*i + new Random().nextDouble() - 0.5);
            inst.setValue(attributeList.get(2), 3*i + new Random().nextDouble() - 0.5);
            testInstances.add(inst);
        }
        DecisionStump tree = new DecisionStump();         // new instance of tree
        tree.buildClassifier(instances);   // build classifier
        Evaluation eval = new Evaluation(instances);
        eval.evaluateModel(tree, testInstances);
        textArea.appendText(eval.toSummaryString("\nResults\n======\n", true));
    }
}