package views;

import interfaces.IView;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import objects.MtgObject;

import java.io.File;

public class ObjectView implements IView {

    private TextField nameTextF;
    private ImageView imageView;
    private Spinner<Integer> index;
    protected int yPos = 0;

    protected GridPane getGrid(){
        GridPane gridPane = new GridPane();

        Label idLabel = new Label();
        idLabel.setText("Index:");
        gridPane.add(idLabel, 0,yPos);

        index = new Spinner<>();
        SpinnerValueFactory<Integer> powValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,999);
        index.setValueFactory(powValueFactory);
        gridPane.add(index, 1, yPos++);

        Label nameLabel = new Label();
        nameLabel.setText("Name:");
        gridPane.add(nameLabel, 0,yPos);

        nameTextF = new TextField();
        gridPane.add(nameTextF, 1, yPos++);

        return gridPane;
    }

    protected GridPane rightPane(GridPane smallPane){
        GridPane rightPane = new GridPane();
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(50);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(50);
        rightPane.getRowConstraints().setAll(row1, row2);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(100);
        rightPane.getColumnConstraints().setAll(col1);

        rightPane.add( smallPane, 0,0 );

        imageView = new ImageView();
        imageView.setFitHeight(200.0);
        imageView.setPreserveRatio(true);

        rightPane.add(imageView, 0, 1);

        return  rightPane;
    }

    @Override
    public GridPane view(MtgObject obj){
        GridPane gridPane = getGrid();
        GridPane rightPane = rightPane(gridPane);
        setValue(obj);
        setEditable(false);
        return rightPane;
    }

    @Override
    public GridPane create() {
        GridPane gridPane = getGrid();

        Label label = new Label();
        label.setText("Image path:");
        TextField textField = new TextField();
        Button button = new Button();
        button.setText("Browse...");
        button.setOnAction(e ->{
            FileChooser fileChooser = new FileChooser();
            Stage stage = new Stage();
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                textField.setText(file.getPath());
                String imgPath = file.toURI().toString();
                Image imgImage = new Image(imgPath);
                imageView.setImage(imgImage);
            }
        });
        gridPane.add(label, 0, yPos);
        gridPane.add(textField, 1, yPos);
        gridPane.add(button, 2, yPos++);

        GridPane rightPane = rightPane(gridPane);
        setEditable(true);
        return rightPane;
    }

    @Override
    public String getName() {
        String text = new String();
        if (nameTextF != null) {
            text = nameTextF.getText();
        }
        return text;
    }

    @Override
    public String getImgPath() {
        String text = new String();
        if (imageView != null) {
            File file = new File(imageView.getImage().getUrl());
            text = file.toString();
        }
        return text;
    }

    public void setValue(MtgObject mtgObject) {
        nameTextF.setText(mtgObject.getName());
        File imgFile = new File(mtgObject.getImgPath());
        String imgPath = imgFile.toString();
        Image imgImage = new Image(imgPath);
        imageView.setImage(imgImage);
        index.getValueFactory().setValue(mtgObject.getId());
    }
    protected void setEditable(boolean b) {
        index.setEditable(false);
        nameTextF.setEditable(b);
    }

    public int getId() {
        return index.getValue();
    }
}
