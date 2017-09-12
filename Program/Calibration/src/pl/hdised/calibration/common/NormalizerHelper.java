package pl.hdised.calibration.common;

/**
 * Useful for array of normalizers
 * Created by Sebastian Oprzędek on 30.07.2017.
 */
public class NormalizerHelper {

    /**
     * creates normalizers for array
     * Normalizer for each layer is made from layer max value and layer min value
     *
     * @param array two dimensional array
     * @return array of normalizers
     */
    public Normalizer[] createNormalizersForArray(double[][] array) {
        Normalizer[] normalizers = new Normalizer[array.length];
        for (int i = 0; i < array.length; ++i) {
            ArrayHelper arrayHelper = new ArrayHelper();
            normalizers[i] = new Normalizer(arrayHelper.min(array[i]), arrayHelper.max(array[i]));
        }
        return normalizers;
    }

    /**
     * normalize array of values using array of normalizers
     * value at index 0 is normalized by normalizer on index 0 etc.
     *
     * @param normalizerArray array of normalizers
     * @param realArray array of values to normalize
     * @return array of normalized values
     */
    public double[] normalizeArray(Normalizer[] normalizerArray, double[] realArray) {
        double[] normalizedArray = new double[realArray.length];
        for (int i = 0; i < realArray.length; ++i)
            normalizedArray[i] = normalizerArray[i].normalize(realArray[i]);
        return normalizedArray;
    }

    /**
     * Returns array of real values from normalized values using array of normalizers
     * value at index 0 return real value using normalizer on index 0 etc.
     *
     * @param normalizerArray array of normalizers
     * @param normalizedArray array of normalized values
     * @return array of real values
     */
    public double[] realValuesArray(Normalizer[] normalizerArray, double[] normalizedArray) {
        double[] realValuesArray = new double[normalizedArray.length];
        for (int i = 0; i < normalizedArray.length; ++i)
            realValuesArray[i] = normalizerArray[i].realValue(normalizedArray[i]);
        return realValuesArray;
    }
}