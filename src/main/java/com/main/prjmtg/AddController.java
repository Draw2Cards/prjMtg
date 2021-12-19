package com.main.prjmtg;

import enums.ObjectType;
import factories.ObjectFactory;
import factories.ObjectViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import objects.MtgObject;
import views.ObjectView;

import java.net.URL;
import java.util.ResourceBundle;

public class AddController implements Initializable {

    @FXML
    protected ComboBox<ObjectType> comboBox;

    @FXML
    protected GridPane gridPane;

    protected MtgObject newMtgObject;
    protected ObjectView objectView;
    protected Node node;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBox.getItems().setAll(ObjectType.values());
        comboBox.getSelectionModel().select(ObjectType.object);
        setGrid(ObjectType.object);
    }

    public void onAction(ActionEvent actionEvent) {
        if (node != null){
            gridPane.getChildren().remove(node);
        }
        setGrid(comboBox.getSelectionModel().getSelectedItem());
        }

   protected void setGrid(ObjectType objectType){
       ObjectViewFactory objectViewFactory = new ObjectViewFactory();
       ObjectFactory objectFactory = new ObjectFactory();
       objectView = objectViewFactory.createView(objectType);
       newMtgObject = objectFactory.createObject(objectType);
       node = objectView.create();
       gridPane.add(node, 0,0);
   }

    public void onAdd(ActionEvent actionEvent) {
        if (newMtgObject != null){
            newMtgObject.setValuesFromView(objectView);
            Node source = (Node) actionEvent.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("Empty object");
            alert.show();
        }
    }

    public void onCancel(ActionEvent actionEvent) {
        newMtgObject = null;
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public MtgObject getObject(){ return newMtgObject; }
}
