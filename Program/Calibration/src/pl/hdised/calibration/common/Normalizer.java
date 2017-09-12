package pl.hdised.calibration.common;

/**
 * Normalize real value to 0-1 range and vice versa
 * Created by Sebastian Oprzędek on 28.07.2017.
 */
public class Normalizer {

    private double scale;
    private double minValue;

    /**
     * standard constructor for real values starting with 0
     * @param scale maximal real value - minimal real value
     */
    public Normalizer(double scale) {
        this(scale, 0);
    }

    /**
     * constructor for real values between minimal and maximal real value
     * @param minValue minimal real value
     * @param maxValue maximal real value
     */
    public Normalizer(double minValue, double maxValue) {
        this.scale = (maxValue - minValue);
        this.minValue = minValue;
    }

    /**
     * normalize real value to value between 0-1
     * @param realValue value to normalize
     * @return normalized value
     */
    public double normalize(double realValue) {
        double normalizedValue = (realValue - minValue) / scale;
        if (normalizedValue < 0 || normalizedValue > 1)
            return -1;
        return normalizedValue;
    }

    /**
     * returns real value from value between 0-1
     * @param normalizedValue normalized value
     * @return real value
     */
    public double realValue(double normalizedValue) {
        if (normalizedValue < 0 || normalizedValue > 1)
            return -1;
        return (normalizedValue * scale) + minValue;
    }
}
