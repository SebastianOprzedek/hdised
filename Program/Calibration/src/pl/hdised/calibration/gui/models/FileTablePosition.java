package pl.hdised.calibration.gui.models;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Sebastian Oprzędek on 16.07.2017.
 */
public class FileTablePosition {
    private final SimpleStringProperty filename;
    private final SimpleBooleanProperty checked;

    public FileTablePosition(String filename, Boolean checked){
        this.filename = new SimpleStringProperty(filename);
        this.checked = new SimpleBooleanProperty(checked);
    }

    public SimpleStringProperty filenameProperty() {
        return this.filename;
    }
    public String getFilename() {
        return filenameProperty().get();
    }
    public void setFilename(String filename){
        this.filenameProperty().set(filename);
    }

    public SimpleBooleanProperty checkedProperty() {
        return this.checked;
    }
    public Boolean getChecked() {
        return this.checkedProperty().get();
    }
    public void setChecked(final Boolean checked) {
        this.checkedProperty().set(checked);
    }
}
