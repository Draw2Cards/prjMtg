package com.main.prjmtg;

import enums.ObjectType;
import javafx.fxml.Initializable;
import objects.Obj;

import java.net.URL;
import java.util.ResourceBundle;

public class EditController extends AddController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBox.getItems().setAll(ObjectType.values());
        comboBox.getSelectionModel().select(ObjectType.object);
    }

    public void setObject(Obj currentItemSelected) {
        ObjectType objectType = ObjectType.valueOfClass(currentItemSelected.getClass().toString());
        setGrid(objectType);
        objectView.setValue(currentItemSelected);
    }
}
