package pl.hdised.calibration.model;

import pl.hdised.calibration.common.neuralnetwork.model.NeuralNetworkDataPosition;

/**
 * Created by Sebastian Oprzędek on 30.07.2017.
 */
public class CalibrationTrainingDataPosition {

    private int passNumber;
    private CalibrationDataPosition calibrationDataPosition;
    private double resultFuelVolume;
    private double error;

    public CalibrationTrainingDataPosition(NeuralNetworkDataPosition neuralNetworkDataPosition){
        passNumber = neuralNetworkDataPosition.getPassNumber();
        calibrationDataPosition = new CalibrationDataPosition(neuralNetworkDataPosition.getInputValues()[0], neuralNetworkDataPosition.getInputValues()[1], neuralNetworkDataPosition.getTargetValues()[0]);
        resultFuelVolume = neuralNetworkDataPosition.getResultValues()[0];
        error = neuralNetworkDataPosition.getError();
    }


    public int getPassNumber() {
        return passNumber;
    }

    public double getError() {
        return error;
    }

    public CalibrationDataPosition getCalibrationDataPosition() {
        return calibrationDataPosition;
    }

    public double getTankId() {
        return getCalibrationDataPosition().getTankId();
    }

    public double getFuelHeight() {
        return getCalibrationDataPosition().getFuelHeight();
    }

    public double getFuelVolume() {
        return getCalibrationDataPosition().getFuelVolume();
    }

    public double getResultFuelVolume() {
        return resultFuelVolume;
    }
}
