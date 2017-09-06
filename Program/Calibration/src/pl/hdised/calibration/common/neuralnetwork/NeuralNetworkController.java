package pl.hdised.calibration.common.neuralnetwork;

import pl.hdised.calibration.common.ArrayHelper;
import pl.hdised.calibration.common.Normalizer;
import pl.hdised.calibration.common.NormalizerHelper;
import pl.hdised.calibration.common.neuralnetwork.model.Net;
import pl.hdised.calibration.common.neuralnetwork.model.NeuralNetworkDataPosition;
import pl.hdised.calibration.common.neuralnetwork.model.TrainingDataWriter;
import pl.hdised.calibration.common.neuralnetwork.util.TopologyHelper;
import weka.classifiers.evaluation.Prediction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebastian Oprzędek on 18.07.2017.
 */
public class NeuralNetworkController {
    private Net net;
    private Normalizer[] inputNormalizers;
    private Normalizer[] outputNormalizers;
    private NeuralNetworkDataPosition[] testPositions;

    public void writeTrainingData(double[][] inputs, double[][] outputs) throws IOException {
        TrainingDataWriter trainingDataWriter = new TrainingDataWriter("trainingData.txt");
        trainingDataWriter.setTopology(new TopologyHelper().createTopologySchema(inputs.length, outputs.length));

        for (int k = 0; k < inputs[0].length; k++) {
            double[] inputLine = new double[inputs.length];
            for (int i = 0; i < inputs.length; ++i)
                inputLine[i] = inputs[i][k];
            trainingDataWriter.writeValues(inputLine);

            double[] outputLine = new double[outputs.length];
            for (int i = 0; i < outputs.length; ++i)
                outputLine[i] = outputs[i][k];
            trainingDataWriter.writeValues(outputLine);
        }
    }

    public NeuralNetworkDataPosition[] launchLearning(double[][] inputData, double[][] outputData) throws IOException {
        NormalizerHelper normalizerHelper = new NormalizerHelper();
        ArrayHelper arrayHelper = new ArrayHelper();
        net = new Net(new TopologyHelper().createTopologySchema(inputData.length, outputData.length));
        NeuralNetworkDataPosition[] neuralNetworkDataPositions = new NeuralNetworkDataPosition[arrayHelper.minSecondLayerLength(inputData, outputData)];
        inputNormalizers = new NormalizerHelper().createNormalizersForArray(inputData);
        outputNormalizers = new NormalizerHelper().createNormalizersForArray(outputData);

        for (int i = 0; i < neuralNetworkDataPositions.length; ++i) {
            NeuralNetworkDataPosition neuralNetworkDataPosition = new NeuralNetworkDataPosition(i + 1, arrayHelper.getColumn(inputData, i), arrayHelper.getColumn(outputData, i));
            net.feedForward(normalizerHelper.normalizeArray(inputNormalizers, neuralNetworkDataPosition.getInputValues()));
            neuralNetworkDataPosition.setResultValues(normalizerHelper.realValuesArray(outputNormalizers, net.getResults()));
            net.backPropagtion(normalizerHelper.normalizeArray(outputNormalizers, neuralNetworkDataPosition.getTargetValues()));
            neuralNetworkDataPosition.setError(net.getRecentAverageError());
            neuralNetworkDataPositions[i] = neuralNetworkDataPosition;
        }
        return neuralNetworkDataPositions;
    }

    public double[] test(double[] inputValues) {
        NormalizerHelper normalizerHelper = new NormalizerHelper();
        net.feedForward(normalizerHelper.normalizeArray(inputNormalizers, inputValues));
        return normalizerHelper.realValuesArray(outputNormalizers, net.getResults());
    }

    public void launchTesting(double[][] inputData, double[][] outputData) {
        NormalizerHelper normalizerHelper = new NormalizerHelper();
        ArrayHelper arrayHelper = new ArrayHelper();
        NeuralNetworkDataPosition[] neuralNetworkDataPositions = new NeuralNetworkDataPosition[arrayHelper.minSecondLayerLength(inputData, outputData)];
        for (int i = 0; i < neuralNetworkDataPositions.length; ++i) {
            NeuralNetworkDataPosition neuralNetworkDataPosition = new NeuralNetworkDataPosition(i + 1, arrayHelper.getColumn(inputData, i), arrayHelper.getColumn(outputData, i));
            net.feedForward(normalizerHelper.normalizeArray(inputNormalizers, neuralNetworkDataPosition.getInputValues()));
            neuralNetworkDataPosition.setResultValues(normalizerHelper.realValuesArray(outputNormalizers, net.getResults()));
            neuralNetworkDataPosition.setError(net.getRecentAverageError());
            neuralNetworkDataPositions[i] = neuralNetworkDataPosition;
        }
        testPositions = neuralNetworkDataPositions;
    }

    public String toSummaryString(String title) {
        return title+"\n"+
                "Mean absolute error: \t\t" + absoluteError()+
                "\nRoot mean squared error: \t\t" + squaredError()+
                "\nRelative absolute error: \t\t" + absoluteError()/averageTargetValue()*100 + "%" +
                "\nRoot relative squared error: \t\t" + squaredError()/averageTargetValue()*100 + "%\n\n";
    }

    public double absoluteError(){
        double balance = 0.0;
        for (NeuralNetworkDataPosition position : testPositions)
            for(int i =0; i< position.getResultValues().length; i++)
                balance += position.getResultValues()[i] - position.getTargetValues()[i];
        return Math.abs(balance)/(testPositions.length*testPositions[0].getTargetValues().length);
    }

    public double averageTargetValue(){
        double balance = 0.0;
        for (NeuralNetworkDataPosition position : testPositions)
            for(int i =0; i< position.getResultValues().length; i++)
                balance += position.getTargetValues()[i];
        return balance/(testPositions.length*testPositions[0].getTargetValues().length);
    }

    public double squaredError(){
        double balance = 0.0;
        for (NeuralNetworkDataPosition position : testPositions)
            for(int i =0; i< position.getResultValues().length; i++)
                balance += Math.pow(position.getResultValues()[i] - position.getTargetValues()[i], 2);
        return Math.sqrt(balance/(testPositions.length*testPositions[0].getTargetValues().length));
    }


    public NeuralNetworkDataPosition[] getTestPositions() {
        return testPositions;
    }
}
