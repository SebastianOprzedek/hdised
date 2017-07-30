package pl.hdised.calibration.common.gui.fx.dialog;

import javafx.scene.control.Alert;

/**
 * Created by Sebastian Oprzędek on 30.07.2017.
 */
public class Dialog extends Alert{

    public Dialog(AlertType alertType, String title, String header, String content) {
        super(alertType, content);
        this.setTitle(title);
        this.setHeaderText(header);
    }
}