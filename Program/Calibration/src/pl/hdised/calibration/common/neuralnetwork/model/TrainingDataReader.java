package pl.hdised.calibration.common.neuralnetwork.model;

import java.io.*;
import java.util.Scanner;

/**
 * Reads training data from file
 * Created by Sebastian Oprzędek on 18.07.2017.
 */
public class TrainingDataReader {

    private Scanner scanner;

    /**
     * Opens file to read
     *
     * @param filename file name
     * @throws IOException error during opening file
     */
    public TrainingDataReader(String filename) throws IOException
    {
        this.scanner = new Scanner(new FileReader(filename));
    }

    /**
     * Read topology schema from file
     *
     * @throws IOException error during opening file
     * @return topology schema
     */
    public int[] getTopology() throws IOException
    {
        String[] inputs = scanner.nextLine().split(" ");
        int[] parsedInputs = new int[inputs.length];
        for(int i=0; i<inputs.length; ++i)
            parsedInputs[i]=Integer.parseInt(inputs[i]);
        return parsedInputs;
    }

    /**
     * Read single row of values from file
     *
     * @throws IOException error during opening file
     * @return array of values (from one row)
     */
    public double[] readValues() throws IOException
    {
        String[] inputs = scanner.nextLine().split(" ");
        double[] parsedInputs = new double[inputs.length];
        for(int i=0; i<inputs.length; ++i)
            parsedInputs[i]=Double.parseDouble(inputs[i]);
        return parsedInputs;
    }

    /**
     * Check if end of file
     *
     * @return if end of file
     */
    public boolean isEof(){
        return scanner.hasNextLine();
    }
}