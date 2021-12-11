package com.main.prjmtg;

import enums.*;
import factories.ObjectViewFactory;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import objects.*;
import objects.Object;
import views.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private GridPane gridPanel;

    @FXML
    private ListView<Object> listView;

    @FXML
    private TextField searchField;

    private ObservableList<Object> objectsList = FXCollections.observableArrayList();

    private static Node infoNode;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        populate(); // for presentation purposes only!

        listView.setItems(objectsList);

        FilteredList<Object> filteredList = new FilteredList<>(objectsList, b -> true);

        searchField.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            filteredList.setPredicate(object -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }

                String lowerNewValue = newValue.toLowerCase();
                if (object.getName().toLowerCase().indexOf(lowerNewValue) != -1){
                    return true;
                }
                else {
                    return false;
                }
            });
        }));

        SortedList<Object> sortedList = new SortedList<>(filteredList);
        listView.setItems(sortedList);

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<? extends Object> observableValue, Object object, Object t1) {
                if (t1 != null) {
                    if (infoNode != null) {
                        gridPanel.getChildren().remove(infoNode);
                    }
                ObjectViewFactory objectViewFactory = new ObjectViewFactory();
                System.out.println(t1.getClass().toString());
                ObjectView objectView = objectViewFactory.createView(ObjectType.valueOfClass(t1.getClass().toString()));
                infoNode = objectView.view(t1);
                gridPanel.add(infoNode, 1, 0);
                }
            }
        });
    }

    public void onDelete(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("delete-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait();
        DeleteController deleteController = fxmlLoader.getController();
        int id = deleteController.getId();
        if (!deleteController.actionCanceled()) {
            if (!deleteItem(id)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setContentText("Could not find object which given id.");
                alert.show();
            }
        }
    }

    private boolean deleteItem(int id) {
        boolean result = false;
        for (Object obj : objectsList) {
            if (obj.getId() == id) {
                objectsList.remove(obj);
                result = true;
                break;
            }
        }
        return result;
    }

    public void onAdd(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("add-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait();
        AddController addController = fxmlLoader.getController();
        Object newObject = addController.getObject();
        if (newObject != null) {
            objectsList.add(newObject);
        }
    }

    public void onSearch(ActionEvent actionEvent) {
        String text = searchField.getText();
        if (text.compareTo("*") == 0){
            listView.setItems(objectsList);
        }
        else
        {
            listView.setItems(null);
        }
    }

    /* ------ */

    private void populate() {
        objectsList.add(new Object("Kiora, Master of the Depths Emblem", "file:\\D:\\img\\tbfz-14-kiora-master-of-the-depths-emblem.jpg"));
        objectsList.add(new Card("Temple of the False God", Type.land, Owner.player, Zone.library, "file:\\D:\\img\\voc-187-temple-of-the-false-god.jpg"));
        objectsList.add(new Creature("Kiora's Follower", 2, 2, false,Owner.player, Zone.library, "file:\\D:\\img\\ddo-52-kiora-s-follower.jpg" ));
        objectsList.add(new Planeswalker("Kiora, Master of the Depths", 4, true, Owner.player, Zone.library, "file:\\D:\\img\\bfz-213-kiora-master-of-the-depths.jpg" ));
        objectsList.add(new Spell("Scapeshift", Type.sorcery, Owner.player, "file:\\D:\\img\\m19-201-scapeshift.jpg" ));
        objectsList.add(new Spell("Growth Spiral", Type.instant, Owner.player, "file:\\D:\\img\\cmr-446-growth-spiral.jpg" ));
    }
}