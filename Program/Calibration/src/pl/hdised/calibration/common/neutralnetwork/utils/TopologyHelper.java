package pl.hdised.calibration.common.neutralnetwork.utils;

import pl.hdised.calibration.common.neutralnetwork.model.Neuron;

/**
 * Created by Sebastian Oprzędek on 18.07.2017.
 */
public class TopologyHelper {
    public Neuron[][] createTopology(int[] topologySchema, double alpha, double eta) {
        int howManyLayers = topologySchema.length;
        Neuron[][] topology = new Neuron[howManyLayers][];
        for (int i = 0; i < howManyLayers; ++i)
        {
            topology[i] = new Neuron[howManyLayers];
            int howManyOutputs = i == topologySchema.length - 1 ? 0 : topologySchema[i + 1];
            for (int j = 0; j <= topologySchema[i]; ++j)
                topology[i][j] = new Neuron(howManyOutputs, j, alpha, eta);
            topology[i][topologySchema[i]].setOutputValue(1.0);
        }
        return topology;
    }
}
