package pl.hdised.calibration.common.neutralnetwork;

import pl.hdised.calibration.common.neutralnetwork.model.TrainingDataWriter;
import pl.hdised.calibration.common.neutralnetwork.utils.TopologyHelper;

import java.io.IOException;

/**
 * Created by Sebastian Oprzędek on 18.07.2017.
 */
public class NeutralNetworkController {
    public NeutralNetworkController() { }

    public void writeTrainingData(double[][] inputs, double[][] outputs) throws IOException {
        TrainingDataWriter trainingDataWriter = new TrainingDataWriter("trainingData.txt");
        trainingDataWriter.setTopology(new TopologyHelper().createTopologySchema(inputs.length, outputs.length));

        for(int k=0; k < inputs[0].length; k++) {
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
}
