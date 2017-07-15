package pl.hdised.calibration.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import pl.hdised.calibration.input.TankMeasuresInputReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MainWindowController {
    @FXML
    private TextArea textArea;

    @FXML
    protected void readData(ActionEvent event) {
        try {
            Scene currentScene = textArea.getScene();
            Stage stage = (Stage) currentScene.getWindow();
            stage.setScene(loadingScene());
            appendTankMeasureInput(textArea);
            stage.setScene(currentScene);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    private Scene loadingScene() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/loadingScene.fxml"));
        return new Scene(loader.load());
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
