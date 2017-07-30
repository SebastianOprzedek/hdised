package pl.hdised.calibration.common.neutralnetwork;

import pl.hdised.calibration.common.ArrayHelper;
import pl.hdised.calibration.common.Normalizer;
import pl.hdised.calibration.common.NormalizerHelper;
import pl.hdised.calibration.common.neutralnetwork.model.Net;
import pl.hdised.calibration.common.neutralnetwork.model.TrainingDataPosition;
import pl.hdised.calibration.common.neutralnetwork.model.TrainingDataWriter;
import pl.hdised.calibration.common.neutralnetwork.util.TopologyHelper;
import java.io.IOException;

/**
 * Created by Sebastian Oprzędek on 18.07.2017.
 */
public class NeutralNetworkController {
    private Net net;
    private Normalizer[] inputNormalizers;
    private Normalizer[] outputNormalizers;

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

    public TrainingDataPosition[] launchLearning(double[][] inputData, double[][] outputData) throws IOException {
        NormalizerHelper normalizerHelper = new NormalizerHelper();
        ArrayHelper arrayHelper = new ArrayHelper();
        net = new Net(new TopologyHelper().createTopologySchema(inputData.length, outputData.length));
        TrainingDataPosition[] trainingDataPositions = new TrainingDataPosition[arrayHelper.minSecondLayerLength(inputData, outputData)];
        inputNormalizers = new NormalizerHelper().createNormalizersForArray(inputData);
        outputNormalizers = new NormalizerHelper().createNormalizersForArray(outputData);

        for(int i=0; i<trainingDataPositions.length; ++i){
            TrainingDataPosition trainingDataPosition = new TrainingDataPosition(i+1, arrayHelper.getColumn(inputData, i), arrayHelper.getColumn(outputData, i));
            net.feedForward(normalizerHelper.normalizeArray(inputNormalizers, trainingDataPosition.getInputValues()));
            trainingDataPosition.setResultValues(normalizerHelper.realValuesArray(outputNormalizers, net.getResults()));
            net.backPropagtion(normalizerHelper.normalizeArray(outputNormalizers, trainingDataPosition.getTargetValues()));
            trainingDataPosition.setError(net.getRecentAverageError());
            trainingDataPositions[i]=trainingDataPosition;
        }
        return trainingDataPositions;
    }

    public double[] test(double[] inputValues){
        NormalizerHelper normalizerHelper = new NormalizerHelper();
        net.feedForward(normalizerHelper.normalizeArray(inputNormalizers, inputValues));
        return normalizerHelper.realValuesArray(outputNormalizers, net.getResults());
    }
}
