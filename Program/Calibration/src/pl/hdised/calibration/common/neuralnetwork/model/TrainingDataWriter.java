package pl.hdised.calibration.common.neuralnetwork.model;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Writes training data to file
 * Created by Sebastian Oprzędek on 24.07.2017.
 */
public class TrainingDataWriter {

    private BufferedOutputStream bufferedOutputStream;

    /**
     * Creates file stream
     *
     * @param filename file name
     * @throws IOException error during creating file
     */
    public TrainingDataWriter(String filename) throws IOException {
        this.bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filename));
    }

    /**
     * Writes topology schema to file
     *
     * @param topologySchema array of integers containing each layer length
     * @throws IOException error during operations on file
     */
    public void setTopology(int[] topologySchema) throws IOException {
        bufferedOutputStream.write(String.valueOf(topologySchema[0]).getBytes());
        for (int i = 1; i < topologySchema.length; ++i) {
            bufferedOutputStream.write((" ").getBytes());
            bufferedOutputStream.write(String.valueOf(topologySchema[i]).getBytes());
        }
        bufferedOutputStream.write(("\r\n").getBytes());
    }

    /**
     * Writes values to file
     *
     * @param values training data values from one layer
     * @throws IOException error during operations on file
     */
    public void writeValues(double[] values) throws IOException {
        bufferedOutputStream.write(String.valueOf(values[0]).getBytes());
        for (int i = 1; i < values.length; ++i) {
            bufferedOutputStream.write((" ").getBytes());
            bufferedOutputStream.write(String.valueOf(values[i]).getBytes());
        }
        bufferedOutputStream.write(("\r\n").getBytes());
    }
}
