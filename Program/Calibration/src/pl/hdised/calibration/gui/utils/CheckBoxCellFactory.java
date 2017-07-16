package pl.hdised.calibration.gui.utils;

import javafx.scene.control.TableCell;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.util.Callback;
import pl.hdised.calibration.gui.models.FileTablePosition;

/**
 * Created by Sebastian Oprzędek on 16.07.2017.
 */
public class CheckBoxCellFactory implements Callback {
    @Override
    public TableCell call(Object param) {
        return (CheckBoxTableCell<FileTablePosition,Boolean>) new CheckBoxTableCell();
    }
}
