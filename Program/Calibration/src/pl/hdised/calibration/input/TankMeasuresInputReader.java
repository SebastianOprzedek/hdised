package pl.hdised.calibration.input;

import pl.hdised.calibration.common.ResourceHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sebastian Oprzędek on 13.07.2017.
 */
public class TankMeasuresInputReader extends InputReader{

    public TankMeasuresInputReader(){
        super(ResourceHelper.getResource("TankMeasuresPath"));
    }

    protected Map<String, String> parseLine(String line){
        String[] parts = line.split(";");
        Map<String, String> data = new HashMap<>();
        data.put("timestamp", parts[0]);
        data.put("locationId", parts[1]);
        data.put("meterId", parts[2]);
        data.put("tankId", parts[3]);
        data.put("fuelHeight", parts[4]);
        data.put("fuelVolume", parts[5]);
        data.put("temperature", parts[6]);
//        data.put("waterHeight", parts[7]);  //despite documentation log files doesn't contain these columns
//        data.put("waterVolume", parts[8]);
        return data;
    }
}