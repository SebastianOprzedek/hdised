package pl.hdised.calibration.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import pl.hdised.calibration.gui.scenes.LoadingScene;
import pl.hdised.calibration.input.TankMeasuresInputReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MainSceneController extends SceneController{
    @FXML
    private TextArea textArea;

    public MainSceneController(Scene defaultScene){
        super(defaultScene);
    }

    @FXML
    protected void readData(ActionEvent event) {
        try {
            setScene(new LoadingScene());
            appendTankMeasureInput(textArea);
            setDefaultScene();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    private void appendTankMeasureInput(TextArea textArea) throws IOException{
        TankMeasuresInputReader tankMeasuresInputReader = new TankMeasuresInputReader();
        Map<String, List<String>> tankMeasures = tankMeasuresInputReader.getData();
        for (String key : tankMeasures.keySet()) {
            textArea.appendText(key + ":\n");
            for (String value : tankMeasures.get(key))
                textArea.appendText(value + ", ");
            textArea.appendText("\n\n");
        }
    }
}
