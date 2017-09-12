package pl.hdised.calibration.common.neuralnetwork.model;

/**
 * Represents single learning/testing operation result
 * Created by Sebastian Oprzędek on 30.07.2017.
 */
public class NeuralNetworkDataPosition {
    private int passNumber;
    private double[] inputValues;
    private double[] resultValues;
    private double[] targetValues;
    private double error;

    /**
     * creates single learning/testing operation result
     *
     * @param passNumber   index of pass
     * @param inputValues  input values
     * @param targetValues target values
     */
    public NeuralNetworkDataPosition(int passNumber, double[] inputValues, double[] targetValues) {
        this.passNumber = passNumber;
        this.inputValues = inputValues;
        this.targetValues = targetValues;
    }

    /**
     * returns index of pass
     *
     * @return index of pass
     */
    public int getPassNumber() {
        return passNumber;
    }

    /**
     * returns input values
     *
     * @return input values
     */
    public double[] getInputValues() {
        return inputValues;
    }

    /**
     * returns result (predicted) values
     *
     * @return result values
     */
    public double[] getResultValues() {
        return resultValues;
    }

    /**
     * returns target values
     *
     * @return target values
     */
    public double[] getTargetValues() {
        return targetValues;
    }

    /**
     * sets results values
     *
     * @param resultValues results values
     */
    public void setResultValues(double[] resultValues) {
        this.resultValues = resultValues;
    }

    /**
     * returns error value
     *
     * @return error value
     */
    public double getError() {
        return error;
    }

    /**
     * sets error value
     *
     * @param error error value
     */
    public void setError(double error) {
        this.error = error;
    }
}
