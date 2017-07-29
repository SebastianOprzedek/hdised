package pl.hdised.calibration.common.gui.fx.dialog;

import javafx.scene.control.Alert;

/**
 * Created by Sebastian Oprzędek on 28.07.2017.
 */
public class WrongFileErrorDialog extends Alert {

    public WrongFileErrorDialog(){
        super(AlertType.ERROR);
        this.setTitle("Error");
        this.setHeaderText(null);
        this.setContentText("File is incorrect");
    }
}