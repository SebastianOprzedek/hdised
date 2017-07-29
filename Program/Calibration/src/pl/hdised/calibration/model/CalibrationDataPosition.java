package pl.hdised.calibration.model;

import javafx.beans.property.SimpleDoubleProperty;

/**
 * Created by Sebastian Oprzędek on 28.07.2017.
 */
public class CalibrationDataPosition {
    private final SimpleDoubleProperty tankId;
    private final SimpleDoubleProperty fuelHeight;
    private final SimpleDoubleProperty fuelVolume;

    public CalibrationDataPosition(double tankId, double fuelHeight, double fuelVolume){
        this.tankId = new SimpleDoubleProperty(tankId);
        this.fuelHeight = new SimpleDoubleProperty(fuelHeight);
        this.fuelVolume = new SimpleDoubleProperty(fuelVolume);
    }

    public CalibrationDataPosition(String tankId, String fuelHeight, String fuelVolume){
        this(Double.parseDouble(tankId.replace(',','.')), Double.parseDouble(fuelHeight.replace(',','.')), Double.parseDouble(fuelVolume.replace(',','.')));
    }

    public SimpleDoubleProperty tankIdProperty() {
        return this.tankId;
    }
    public double getTankId() {
        return tankIdProperty().get();
    }
    public void setTankId(double taskId){
        this.tankIdProperty().set(taskId);
    }

    public SimpleDoubleProperty fuelHeightProperty() {
        return this.fuelHeight;
    }
    public double getFuelHeight() {
        return fuelHeightProperty().get();
    }
    public void setFuelHeight(double fuelHeight){
        this.fuelHeightProperty().set(fuelHeight);
    }

    public SimpleDoubleProperty fuelVolumeProperty() {
        return this.fuelVolume;
    }
    public double getFuelVolume() {
        return fuelVolumeProperty().get();
    }
    public void setVolumeHeight(double fuelVolume){
        this.fuelVolumeProperty().set(fuelVolume);
    }

    @Override
    public String toString(){
        return tankId + " " + fuelHeight + " " + fuelVolume;
    }
}
