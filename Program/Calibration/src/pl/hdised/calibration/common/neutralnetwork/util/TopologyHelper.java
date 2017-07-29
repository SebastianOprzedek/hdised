package pl.hdised.calibration.common.neutralnetwork.util;

import pl.hdised.calibration.common.neutralnetwork.model.Neuron;

/**
 * Created by Sebastian Oprzędek on 18.07.2017.
 */
public class TopologyHelper {
    static final int[] DEFAULT_INNER_LAYERS = {4};

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

    public int[] createTopologySchema(int numberOfInputs, int numberOfOutputs){
        int[] topologySchema = new int[DEFAULT_INNER_LAYERS.length + 2];
        topologySchema[0] = numberOfInputs;
        System.arraycopy(DEFAULT_INNER_LAYERS, 0, topologySchema, 1, DEFAULT_INNER_LAYERS.length);
        topologySchema[DEFAULT_INNER_LAYERS.length + 1] = numberOfOutputs;
        return topologySchema;
    }
}
