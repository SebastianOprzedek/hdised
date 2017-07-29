package pl.hdised.calibration.common.gui.fx.util;

import javafx.scene.control.TableCell;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.util.Callback;
import pl.hdised.calibration.model.FilePosition;

/**
 * Created by Sebastian Oprzędek on 16.07.2017.
 */
public class CheckBoxCellFactory implements Callback {
    @Override
    public TableCell call(Object param) {
        return (CheckBoxTableCell<FilePosition,Boolean>) new CheckBoxTableCell();
    }
}
