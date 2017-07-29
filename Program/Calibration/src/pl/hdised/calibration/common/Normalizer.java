package pl.hdised.calibration.common;

/**
 * Created by Sebastian Oprzędek on 28.07.2017.
 */
public class Normalizer {

    private double scale;
    private double minValue;

    public Normalizer(double scale){
        this(scale, 0);
    }

    public Normalizer(double minValue, double maxValue){
        this.scale=(maxValue - minValue);
        this.minValue=minValue;
    }

    public double normalize(double realValue) {
        double normalizedValue = (realValue - minValue) / scale;
        if (normalizedValue < 0 || normalizedValue > 1)
            return -1;
        return normalizedValue;
    }

    public double realValue(double normalizedValue) {
        if (normalizedValue < 0 || normalizedValue > 1)
            return -1;
        return (normalizedValue * scale) + minValue;
    }
}
