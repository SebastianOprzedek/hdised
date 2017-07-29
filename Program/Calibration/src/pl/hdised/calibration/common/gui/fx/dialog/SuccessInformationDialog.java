package pl.hdised.calibration.common.gui.fx.dialog;

import javafx.scene.control.Alert;

/**
 * Created by Sebastian Oprzędek on 28.07.2017.
 */
public class SuccessInformationDialog extends Alert{

    public SuccessInformationDialog(){
        super(Alert.AlertType.INFORMATION);
        this.setTitle("Information");
        this.setHeaderText(null);
        this.setContentText("Action succeeded");
    }
}
