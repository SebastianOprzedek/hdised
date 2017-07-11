package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;

public class Controller {
    @FXML
    private TextArea textArea;

    @FXML
    protected void readData(ActionEvent event) {
        textArea.setText("hdised");
    }
}
