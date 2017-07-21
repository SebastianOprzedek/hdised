package pl.hdised.calibration.common.neutralnetwork.model;

import pl.hdised.calibration.common.neutralnetwork.utils.TopologyHelper;
import java.lang.Math;

/**
 * Created by Sebastian Oprzędek on 18.07.2017.
 */
public class Net {

    private Neuron[][] topology;
    private double error;
    private double recentAverageError;
    private double recentAverageSmoothingFactor;

    Net(int[] topologySchema, double alpha, double eta){
        topology = new TopologyHelper().createTopology(topologySchema, alpha, eta);
    }

    void feedForward(double[] inputValues)
    {
        for (int whichInput = 0; whichInput < inputValues.length; ++whichInput)
            topology[0][whichInput].setOutputValue(inputValues[whichInput]);
        for (int whichLayer = 1; whichLayer < topology.length; ++whichLayer)
        {
            Neuron[] previousLayer = topology[whichLayer - 1];
            for (int whichNeuron = 0; whichNeuron < topology[whichLayer].length - 1; ++whichNeuron)
                topology[whichLayer][whichNeuron].feedForward(previousLayer);
        }
    }

    void backPropagtion(double[] targetValues)
    {
        Neuron[] outputLayer = topology[topology.length-1];
        error = 0.0;
        for (int whichNeuron = 0; whichNeuron < outputLayer.length - 1; ++whichNeuron)
        {
            double delta = targetValues[whichNeuron] - outputLayer[whichNeuron].getOutputValue();
            error += delta*delta;
        }
        error /= outputLayer.length - 1;
        error = Math.sqrt(error);
        recentAverageError = (recentAverageError * recentAverageSmoothingFactor + error) / (recentAverageSmoothingFactor + 1.0);
        for (int whichNeuron = 0; whichNeuron < outputLayer.length - 1; ++whichNeuron)
            outputLayer[whichNeuron].calcOutputGradients(targetValues[whichNeuron]);
        for (int whichLayer = topology.length - 2; whichLayer > 0; --whichLayer)
        {
            Neuron[] hiddenLayer = topology[whichLayer];
            Neuron[] nextLayer = topology[whichLayer + 1];
            for (Neuron neuron : hiddenLayer) neuron.calcHiddenGradients(nextLayer);
        }
        for (int whichLayer = topology.length - 1; whichLayer > 0; --whichLayer)
        {
            Neuron[] layer = topology[whichLayer];
            Neuron[] previousLayer = topology[whichLayer - 1];
            for (int whichNeuron = 0; whichNeuron < layer.length - 1; ++whichNeuron)
                layer[whichNeuron].updateInputWeights(previousLayer);
        }
    }

    double[] getResults()
    {
        double[] resultValues = new double[topology[topology.length-1].length];
        for (int whichNeuron = 0; whichNeuron < topology[topology.length-1].length - 1; ++whichNeuron)
            resultValues[whichNeuron] = topology[topology.length-1][whichNeuron].getOutputValue();
        return resultValues;
    }

}
