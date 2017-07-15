package pl.hdised.calibration.input;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Sebastian Oprzędek on 13.07.2017.
 */
abstract public class InputReader {
    protected File file;

    protected InputReader (String filename){
        file = new File(filename);
    }

    abstract protected Map<String, String> parseLine(String line);

    protected List<String> getLines() throws IOException{
        List<String> lines = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine())
            lines.add(scanner.nextLine());
        return lines;
    }

    public Map<String, List<String>> getData() throws IOException{
        Map<String, List<String>> data = new HashMap<>();
        List<String> lines = getLines();
        for (String line : lines) {
            Map<String, String> lineValues = parseLine(line);
            for (String key : lineValues.keySet()){
                List<String> valueSet = data.containsKey(key) ? data.get(key) : new ArrayList<String>();
                valueSet.add(lineValues.get(key));
                data.put(key, valueSet);
            }
        }
        return data;
    }
}