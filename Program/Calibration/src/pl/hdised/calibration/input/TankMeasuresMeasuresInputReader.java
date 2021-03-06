package pl.hdised.calibration.input;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sebastian Oprzędek on 13.07.2017.
 */
public class TankMeasuresMeasuresInputReader extends MeasuresInputReader {

    public TankMeasuresMeasuresInputReader(String filename){ super(filename); }

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

    public String getAsString() throws IOException {
        StringBuilder input = new StringBuilder();
        Map<String, List<String>> tankMeasures = getData();
        tankMeasures.keySet().forEach(key -> {
            input.append(key).append(":\n");
            tankMeasures.get(key).forEach(value -> input.append(value).append(", "));
            input.append("\n\n");
        });
        return input.toString();
    }
}