package pl.hdised.calibration.common.neuralnetwork.util;

import pl.hdised.calibration.common.neuralnetwork.model.Neuron;

/**
 * Helpful for topology operations
 * Created by Sebastian Oprzędek on 18.07.2017.
 */
public class TopologyHelper {
    static final int[] DEFAULT_INNER_LAYERS = {4};

    /**
     * Create topology (array of neurons) from topology schema (array of integers).
     *
     * @param topologySchema topology schema (array of integers)
     * @param alpha net momentum
     * @param eta learning grade
     * @return array of neurons
     */
    public Neuron[][] createTopology(int[] topologySchema, double alpha, double eta) {
        int howManyLayers = topologySchema.length;
        Neuron[][] topology = new Neuron[howManyLayers][];
        for (int i = 0; i < howManyLayers; ++i)
        {
            int howManyOutputs = i == topologySchema.length - 1 ? 0 : topologySchema[i + 1];
            topology[i] = new Neuron[topologySchema[i]+1];
            for (int j = 0; j <= topologySchema[i]; ++j)
                topology[i][j] = new Neuron(howManyOutputs, j, alpha, eta);
            topology[i][topologySchema[i]].setOutputValue(1.0);
        }
        return topology;
    }

    /**
     * Create topology schema (array of integers) for number of inputs and outputs
     * It use default inner layer size, which if 4.
     * For example for 3 inputs and 2 outputs method will create topology schema: 3 4 2
     *
     * @param numberOfInputs number of inputs
     * @param numberOfOutputs number of outputs
     * @return array of integers containing each layer length
     */
    public int[] createTopologySchema(int numberOfInputs, int numberOfOutputs){
        int[] topologySchema = new int[DEFAULT_INNER_LAYERS.length + 2];
        topologySchema[0] = numberOfInputs;
        System.arraycopy(DEFAULT_INNER_LAYERS, 0, topologySchema, 1, DEFAULT_INNER_LAYERS.length);
        topologySchema[DEFAULT_INNER_LAYERS.length + 1] = numberOfOutputs;
        return topologySchema;
    }
}
