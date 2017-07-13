package pl.hdised.calibration.gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import pl.hdised.calibration.input.TankMeasuresInputReader;

import java.util.List;
import java.util.Map;

public class Controller {
    @FXML
    private TextArea textArea;

    @FXML
    protected void readData(ActionEvent event) {
        TankMeasuresInputReader tankMeasuresInputReader = new TankMeasuresInputReader();
        Map<String, List<String>> tankMeasures = tankMeasuresInputReader.getData();
        for(String key : tankMeasures.keySet()){
            textArea.appendText(key + ":\n");
            for(String value : tankMeasures.get(key))
                textArea.appendText(value + ", ");
            textArea.appendText("\n\n");
        }
    }
}
