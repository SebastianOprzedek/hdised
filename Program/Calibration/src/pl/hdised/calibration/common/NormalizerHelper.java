package pl.hdised.calibration.common;

/**
 * Created by Sebastian Oprzędek on 30.07.2017.
 */
public class NormalizerHelper {

    public Normalizer[] createNormalizersForArray(double[][] array){
        Normalizer[] normalizers = new Normalizer[array.length];
        for(int i=0; i<array.length; ++i){
            ArrayHelper arrayHelper = new ArrayHelper();
            normalizers[i] = new Normalizer(arrayHelper.min(array[i]), arrayHelper.max(array[i]));
        }
        return normalizers;
    }

    public double[] normalizeArray(Normalizer[] normalizerArray, double[] realArray){
        double[] normalizedArray = new double[realArray.length];
        for(int i=0; i<realArray.length; ++i)
            normalizedArray[i] = normalizerArray[i].normalize(realArray[i]);
        return normalizedArray;
    }

    public double[] realValuesArray(Normalizer[] normalizerArray, double[] normalizeArray){
        double[] realValuesArray = new double[normalizeArray.length];
        for(int i=0; i<normalizeArray.length; ++i)
            realValuesArray[i] = normalizerArray[i].realValue(normalizeArray[i]);
        return realValuesArray;
    }
}