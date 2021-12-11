package com.main.prjmtg;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DeleteController {
    private int id = -1;
    private boolean canceled = false;

    @FXML
    private TextField textField;

    @FXML
    void onCancel(ActionEvent event) {
        canceled = true;
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onOK(ActionEvent event) {
        try {
            id = Integer.parseInt(textField.getText());
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
        catch (NumberFormatException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("Invalid input format.");
            alert.show();
        }
    }

    public int getId(){return id;}
    public boolean actionCanceled(){return canceled;}
}
