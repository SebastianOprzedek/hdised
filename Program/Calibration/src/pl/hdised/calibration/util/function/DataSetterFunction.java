package pl.hdised.calibration.util.function;

import pl.hdised.calibration.model.CalibrationData;

import java.util.concurrent.Callable;

/**
 * Created by Sebastian Oprzędek on 04.09.2017.
 */
abstract public class DataSetterFunction implements Callable<Void> {
    protected CalibrationData calibrationData;

    public Void callWithParameter(CalibrationData calibrationData) throws Exception{
        this.calibrationData = calibrationData;
        return call();
    }
}
