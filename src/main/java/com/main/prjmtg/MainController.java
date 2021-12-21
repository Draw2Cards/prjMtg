package com.main.prjmtg;

import enums.*;
import factories.ObjectViewFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import objects.Obj;
import readers.CsvReader;
import readers.Reader;
import views.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private GridPane gridPanel;

    @FXML
    private ListView<Obj> listView;

    @FXML
    private TextField searchField;

    @FXML
    private ComboBox typesComboBox;

    private ObservableList<Obj> fullObjectsList = FXCollections.observableArrayList();

    private static Node infoNode;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            loadState(new CsvReader());
        } catch (IOException e) {
            e.printStackTrace();
        }


        typesComboBox.getItems().setAll(ObjectType.values());
        typesComboBox.getItems().add("all");
        typesComboBox.getSelectionModel().select("all");

        listView.setItems(fullObjectsList);

        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent click) {

                if (click.getClickCount() == 2) {
                    Obj currentItemSelected = listView.getSelectionModel().getSelectedItem();
                    System.out.println(listView.getSelectionModel().getSelectedIndex() +": " + currentItemSelected.getName());

                    FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("add-view.fxml"));
                    EditController editController = new EditController();
                    fxmlLoader.setController(editController);

                    Scene scene = null;
                    try {
                        scene = new Scene(fxmlLoader.load());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.setScene(scene);

                    editController.setObject(currentItemSelected);
                    stage.showAndWait();
                    Obj editedObj = editController.getObject();
                    if (editedObj != null) {
                        fullObjectsList.set(listView.getSelectionModel().getSelectedIndex(), editedObj);
                    }

                }
            }
        });

        FilteredList<Obj> filteredByNameList = new FilteredList<>(fullObjectsList, b -> true);
        FilteredList<Obj> filteredByType = new FilteredList<>(filteredByNameList, b-> true);

        searchField.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            filteredByNameList.setPredicate(object -> {
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

        typesComboBox.getSelectionModel().selectedItemProperty().addListener((((observableValue, oldValue, newValue) -> {
            filteredByType.setPredicate(object -> {
                if (newValue == null || newValue == "all"){
                    return true;
                }
                else {
                    ObjectType objType = (ObjectType) newValue;
                    System.out.println(object.getClass().toString() + " " + objType.getClassName());
                    if ( object.getClass().toString().compareTo(objType.getClassName()) == 0 ) {
                        return true;
                    }
                    else
                    {
                        return false;
                    }

                }
            });
        })));

        SortedList<Obj> sortedList = new SortedList<>(filteredByType);
        listView.setItems(sortedList);

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Obj>() {
            @Override
            public void changed(ObservableValue<? extends Obj> observableValue, Obj obj, Obj t1) {
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

    private void loadState(Reader reader) throws IOException {
        ArrayList<Obj> arrayList = new ArrayList<>();
        arrayList = reader.getArrayList();

        fullObjectsList.setAll(arrayList);

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
        for (Obj obj : fullObjectsList) {
            if (obj.getId() == id) {
                fullObjectsList.remove(obj);
                result = true;
                break;
            }
        }
        return result;
    }

    public void onAdd(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("add-view.fxml"));
        AddController addController = new AddController();
        fxmlLoader.setController(addController);
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait();
        Obj newObj = addController.getObject();
        if (newObj != null) {
            fullObjectsList.add(newObj);
        }
    }

    public void onSearch(ActionEvent actionEvent) {
        String text = searchField.getText();
        if (text.compareTo("*") == 0){
            listView.setItems(fullObjectsList);
        }
        else
        {
            listView.setItems(null);
        }
    }
}