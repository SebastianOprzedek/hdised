package pl.hdised.calibration.model;

import pl.hdised.calibration.common.neutralnetwork.model.TrainingDataPosition;

/**
 * Created by Sebastian Oprzędek on 30.07.2017.
 */
public class CalibrationTrainingDataPosition {

    private int passNumber;
    private CalibrationDataPosition calibrationDataPosition;
    private double resultFuelVolume;
    private double error;

    public CalibrationTrainingDataPosition(TrainingDataPosition trainingDataPosition){
        passNumber = trainingDataPosition.getPassNumber();
        calibrationDataPosition = new CalibrationDataPosition(trainingDataPosition.getInputValues()[0], trainingDataPosition.getInputValues()[1], trainingDataPosition.getTargetValues()[0]);
        resultFuelVolume = trainingDataPosition.getResultValues()[0];
        error = trainingDataPosition.getError();
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
