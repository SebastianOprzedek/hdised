package pl.hdised.calibration.common.neuralnetwork.model;

import pl.hdised.calibration.common.neuralnetwork.util.TopologyHelper;

import java.lang.Math;

/**
 * Represents model of net
 * Created by Sebastian Oprzędek on 18.07.2017.
 */
public class Net {

    static final double DEFAULT_ALPHA = 0.3;
    static final double DEFAULT_ETA = 0.2;
    private Neuron[][] topology;
    private double error;
    private double recentAverageError;
    private double recentAverageSmoothingFactor;

    /**
     * Creates net from topology schema
     *
     * @param topologySchema topology schema
     */
    public Net(int[] topologySchema) {
        topology = new TopologyHelper().createTopology(topologySchema, DEFAULT_ALPHA, DEFAULT_ETA);
    }

    /**
     * Standard feedForward algorithm for net
     *
     * @param inputValues input values to feed
     */
    public void feedForward(double[] inputValues) {
        for (int whichInput = 0; whichInput < inputValues.length; ++whichInput)
            topology[0][whichInput].setOutputValue(inputValues[whichInput]);
        for (int whichLayer = 1; whichLayer < topology.length; ++whichLayer) {
            Neuron[] previousLayer = topology[whichLayer - 1];
            for (int whichNeuron = 0; whichNeuron < topology[whichLayer].length - 1; ++whichNeuron)
                topology[whichLayer][whichNeuron].feedForward(previousLayer);
        }
    }

    /**
     * Standard back propagation algorithm for net
     *
     * @param targetValues target vaules
     */
    public void backPropagation(double[] targetValues) {
        Neuron[] outputLayer = topology[topology.length - 1];
        error = 0.0;
        for (int whichNeuron = 0; whichNeuron < outputLayer.length - 1; ++whichNeuron) {
            double delta = targetValues[whichNeuron] - outputLayer[whichNeuron].getOutputValue();
            error += delta * delta;
        }
        error /= outputLayer.length - 1;
        error = Math.sqrt(error);
        recentAverageError = (recentAverageError * recentAverageSmoothingFactor + error) / (recentAverageSmoothingFactor + 1.0);
        for (int whichNeuron = 0; whichNeuron < outputLayer.length - 1; ++whichNeuron)
            outputLayer[whichNeuron].calcOutputGradients(targetValues[whichNeuron]);
        for (int whichLayer = topology.length - 2; whichLayer > 0; --whichLayer) {
            Neuron[] hiddenLayer = topology[whichLayer];
            Neuron[] nextLayer = topology[whichLayer + 1];
            for (Neuron neuron : hiddenLayer) neuron.calcHiddenGradients(nextLayer);
        }
        for (int whichLayer = topology.length - 1; whichLayer > 0; --whichLayer) {
            Neuron[] layer = topology[whichLayer];
            Neuron[] previousLayer = topology[whichLayer - 1];
            for (int whichNeuron = 0; whichNeuron < layer.length - 1; ++whichNeuron)
                layer[whichNeuron].updateInputWeights(previousLayer);
        }
    }

    /**
     * Returns predictions
     *
     * @return predictions
     */
    public double[] getResults() {
        double[] resultValues = new double[topology[topology.length - 1].length - 1];
        for (int whichNeuron = 0; whichNeuron < topology[topology.length - 1].length - 1; ++whichNeuron)
            resultValues[whichNeuron] = topology[topology.length - 1][whichNeuron].getOutputValue();
        return resultValues;
    }

    /**
     * Returns recent average error
     *
     * @return recent average error
     */
    public double getRecentAverageError() {
        return recentAverageError;
    }
}
