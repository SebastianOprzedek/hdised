package pl.hdised.calibration.common.neuralnetwork.model;

/**
 * Created by Sebastian Oprzędek on 30.07.2017.
 */
public class NeuralNetworkDataPosition {
    private int passNumber;
    private double[] inputValues;
    private double[] resultValues;
    private double[] targetValues;
    private double error;

    public NeuralNetworkDataPosition(int passNumber, double[] inputValues, double[] targetValues){
        this.passNumber = passNumber;
        this.inputValues = inputValues;
        this.targetValues = targetValues;
    }

    public int getPassNumber() {
        return passNumber;
    }

    public double[] getInputValues() {
        return inputValues;
    }

    public double[] getResultValues() {
        return resultValues;
    }

    public double[] getTargetValues() {
        return targetValues;
    }

    public void setResultValues(double[] resultValues) {
        this.resultValues = resultValues;
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }
}
