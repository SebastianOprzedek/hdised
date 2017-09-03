package pl.hdised.calibration.common.neuralnetwork.model;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Sebastian Oprzędek on 24.07.2017.
 */
public class TrainingDataWriter {

    private BufferedOutputStream bufferedOutputStream;

    public TrainingDataWriter(String filename) throws IOException
    {
        this.bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filename));
    }

    public void setTopology(int[] topologySchema) throws IOException
    {
        bufferedOutputStream.write(String.valueOf(topologySchema[0]).getBytes());
        for (int i = 1; i < topologySchema.length; ++i){
            bufferedOutputStream.write((" ").getBytes());
            bufferedOutputStream.write(String.valueOf(topologySchema[i]).getBytes());
        }
        bufferedOutputStream.write(("\r\n").getBytes());
    }

    public void writeValues(double[] values) throws IOException
    {
        bufferedOutputStream.write(String.valueOf(values[0]).getBytes());
        for (int i = 1; i < values.length; ++i) {
            bufferedOutputStream.write((" ").getBytes());
            bufferedOutputStream.write(String.valueOf(values[i]).getBytes());
        }
        bufferedOutputStream.write(("\r\n").getBytes());
    }
}
