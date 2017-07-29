package pl.hdised.calibration.model;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Sebastian Oprzędek on 28.07.2017.
 */
public class CalibrationData {
    private List<CalibrationDataPosition> positions = new ArrayList<>();

    public void append(Map<String, List<String>> inputData){
        for(int i=0; i<inputData.get("tankId").size() && i<inputData.get("fuelHeight").size() && i<inputData.get("fuelVolume").size(); ++i)
            positions.add(new CalibrationDataPosition(inputData.get("tankId").get(i), inputData.get("fuelHeight").get(i), inputData.get("fuelVolume").get(i)));
    }

    public void writeDataToFile(String filename) throws IOException{
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filename));
        for (int i = 1; i < positions.size(); ++i){
            bufferedOutputStream.write(positions.get(i).toString().getBytes());
            bufferedOutputStream.write(("\r\n").getBytes());
        }
        bufferedOutputStream.close();
    }

    CalibrationDataPosition getForIndex(int index){
        return positions.get(index);
    }

    public List<CalibrationDataPosition> getPositions() {
        return positions;
    }

    public double[] getTankIds(){
        double[] tankIds = new double[positions.size()];
        for(int i=0; i<positions.size(); ++i)
            tankIds[i] = positions.get(i).getTankId();
        return tankIds;
    }

    public double[] getFuelHeights(){
        double[] fuelHeights = new double[positions.size()];
        for(int i=0; i<positions.size(); ++i)
            fuelHeights[i] = positions.get(i).getFuelHeight();
        return fuelHeights;
    }

    public double[] getFuelVolumes(){
        double[] fuelVolumes = new double[positions.size()];
        for(int i=0; i<positions.size(); ++i)
            fuelVolumes[i] = positions.get(i).getFuelVolume();
        return fuelVolumes;
    }
}