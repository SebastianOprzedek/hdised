package pl.hdised.calibration.common.neuralnetwork.model;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Sebastian Oprzędek on 18.07.2017.
 */
public class TrainingDataReader {

    private Scanner scanner;

    public TrainingDataReader(String filename) throws IOException
    {
        this.scanner = new Scanner(new FileReader(filename));
    }

    public int[] getTopology() throws IOException
    {
        String[] inputs = scanner.nextLine().split(" ");
        int[] parsedInputs = new int[inputs.length];
        for(int i=0; i<inputs.length; ++i)
            parsedInputs[i]=Integer.parseInt(inputs[i]);
        return parsedInputs;
    }

    public double[] readValues() throws IOException
    {
        String[] inputs = scanner.nextLine().split(" ");
        double[] parsedInputs = new double[inputs.length];
        for(int i=0; i<inputs.length; ++i)
            parsedInputs[i]=Double.parseDouble(inputs[i]);
        return parsedInputs;
    }

    public boolean isEof(){
        return scanner.hasNextLine();
    }
}