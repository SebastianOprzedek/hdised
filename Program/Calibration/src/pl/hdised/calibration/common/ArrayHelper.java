package pl.hdised.calibration.common;

/**
 * Created by Sebastian Oprzędek on 30.07.2017.
 */
public class ArrayHelper {

    public int minSecondLayerLength(double[][]... arrays){
        if(arrays.length == 1){
            int min = arrays[0][0].length;
            for(int i = 1; i < arrays[0].length; i++)
                if(min > arrays[0][i].length)
                    min = arrays[0][i].length;
            return min;
        }
        else{
            int min = minSecondLayerLength(arrays[0]);
            for (int i = 1; i < arrays.length; i++) {
                int temporaryMin = minSecondLayerLength(arrays[i]);
                if (min > temporaryMin)
                    min = temporaryMin;
            }
            return min;
        }
    }

    public int maxSecondLayerLength(double[][]... arrays){
        if(arrays.length == 1){
            int max = arrays[0][0].length;
            for(int i = 1; i < arrays[0].length; i++)
                if(max < arrays[0][i].length)
                    max = arrays[0][i].length;
            return max;
        }
        else{
            int max = minSecondLayerLength(arrays[0]);
            for (int i = 1; i < arrays.length; i++) {
                int temporaryMax = minSecondLayerLength(arrays[i]);
                if (max < temporaryMax)
                    max = temporaryMax;
            }
            return max;
        }
    }
}
