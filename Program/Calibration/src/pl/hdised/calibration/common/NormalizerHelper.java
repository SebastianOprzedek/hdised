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

}
