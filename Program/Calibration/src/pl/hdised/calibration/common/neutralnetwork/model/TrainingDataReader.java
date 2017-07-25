package pl.hdised.calibration.common.neutralnetwork.model;

import java.io.*;
/**
 * Created by Sebastian Oprzędek on 18.07.2017.
 */
public class TrainingDataReader {

    private BufferedReader bufferedReader;

    public TrainingDataReader(String filename) throws IOException
    {
        this.bufferedReader = new BufferedReader(new FileReader(filename));
    }

    int[] getTopology() throws IOException
    {
        String[] inputs = bufferedReader.readLine().split(" ");
        int[] parsedInputs = new int[inputs.length];
        for(int i=0; i<inputs.length; ++i)
            parsedInputs[i]=Integer.parseInt(inputs[i]);
        return parsedInputs;
    }

    double[] readValues() throws IOException
    {
        String[] inputs = bufferedReader.readLine().split(" ");
        double[] parsedInputs = new double[inputs.length];
        for(int i=0; i<inputs.length; ++i)
            parsedInputs[i]=Double.parseDouble(inputs[i]);
        return parsedInputs;
    }
    
}
