package pl.hdised.calibration.common.neutralnetwork;

import pl.hdised.calibration.common.ArrayHelper;
import pl.hdised.calibration.common.neutralnetwork.model.Net;
import pl.hdised.calibration.common.neutralnetwork.model.TrainingDataReader;
import pl.hdised.calibration.common.neutralnetwork.model.TrainingDataWriter;
import pl.hdised.calibration.common.neutralnetwork.util.TopologyHelper;
import java.io.IOException;

/**
 * Created by Sebastian Oprzędek on 18.07.2017.
 */
public class NeutralNetworkController {
    public NeutralNetworkController() {
    }

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

    public String launchLearningFromFile() throws IOException {
        TrainingDataReader trainingDataReader = new TrainingDataReader("trainingData.txt");
        StringBuilder stringBuilder = new StringBuilder();
        int[] topologySchema = trainingDataReader.getTopology();
        Net net = new Net(topologySchema);

        int trainingPass = 0;
        while (trainingDataReader.isEof()) {
            stringBuilder.append("Pass: ").append(++trainingPass).append("\t-\tInputs:");
            double[] inputValues = trainingDataReader.readValues();
            for (double value : inputValues)
                stringBuilder.append(' ').append(value);
            net.feedForward(inputValues); //TODO: normalize values
            stringBuilder.append("\tResults:");
            double[] resultValues = net.getResults();
            for (double value : resultValues)
                stringBuilder.append(' ').append(value);
            stringBuilder.append("\tTargets:");
            double[] targetValues = trainingDataReader.readValues();
            for (double value : targetValues)
                stringBuilder.append(' ').append(value);
            net.backPropagtion(targetValues);

            double error = net.getRecentAverageError();
            stringBuilder.append("\tNet recent average error: ").append(error).append("\n");
        }
//        net.save("net.txt");
    return stringBuilder.toString();
    }

    public String launchLearning(double[][] inputData, double[][] outputData) throws IOException {
        int dataSize = new ArrayHelper().minSecondLayerLength(inputData, outputData);
        StringBuilder stringBuilder = new StringBuilder();
        int[] topologySchema = new TopologyHelper().createTopologySchema(inputData.length, outputData.length);
        Net net = new Net(topologySchema);

        int trainingPass = 0;
        while (trainingPass < dataSize) {
            stringBuilder.append("Pass: ").append(trainingPass+1).append("\t-\tInputs:");
            double[] inputValues = new double[inputData.length];
            for(int i=0; i<inputData.length; ++i)
                inputValues[i] = inputData[i][trainingPass];
            for (double value : inputValues)
                stringBuilder.append(' ').append(value);
            net.feedForward(inputValues); //TODO: normalize values
            stringBuilder.append("\tResults:");
            double[] resultValues = net.getResults();
            for (double value : resultValues)
                stringBuilder.append(' ').append(value);
            stringBuilder.append("\tTargets:");
            double[] targetValues = new double[outputData.length];
            for(int i=0; i<outputData.length; ++i)
                targetValues[i] = outputData[i][trainingPass];
            for (double value : targetValues)
                stringBuilder.append(' ').append(value);
            net.backPropagtion(targetValues);
            double error = net.getRecentAverageError();
            stringBuilder.append("\tNet recent average error: ").append(error).append("\n");
            trainingPass++;
        }
//        net.save("net.txt");
        return stringBuilder.toString();
    }
}
