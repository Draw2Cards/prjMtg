package views;

import enums.Owner;
import enums.Type;
import enums.Zone;
import interfaces.IView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import objects.Card;
import objects.MtgObject;

public class CardView extends ObjectView implements IView {

    private ComboBox typeCombo;
    private ComboBox ownerCombo;
    private ComboBox zoneCombo;

    protected ComboBox getTypeCombo(){return typeCombo;}

    @Override
    public GridPane view(MtgObject obj) {
        GridPane gridPane = getGrid();
        GridPane rightPane = rightPane(gridPane);
        setValue(obj);
        setEditable(false);
        return rightPane;
    }

    @Override
    protected GridPane getGrid(){
        GridPane gridPane = super.getGrid();
        Label typeLabel = new Label();
        typeLabel.setText("Type:");
        Label ownerLabel = new Label();
        ownerLabel.setText("Owner:");
        Label zoneLabel = new Label();
        zoneLabel.setText("Zone:");
        typeCombo = new ComboBox();
        typeCombo.getItems().setAll(Type.values());
        ownerCombo = new ComboBox();
        ownerCombo.getItems().setAll(Owner.values());
        zoneCombo = new ComboBox();
        zoneCombo.getItems().setAll(Zone.values());
        gridPane.add(typeLabel, 0, yPos);
        gridPane.add(typeCombo, 1, yPos++);

        gridPane.add(ownerLabel, 0, yPos);
        gridPane.add(ownerCombo, 1, yPos++);

        gridPane.add(zoneLabel, 0, yPos);
        gridPane.add(zoneCombo, 1, yPos++);

        return gridPane;
    }

    @Override
    public void setValue(MtgObject obj){
        super.setValue(obj);
        Card card = (Card) obj;
        typeCombo.getSelectionModel().select(card.getType());
        ownerCombo.getSelectionModel().select(card.getOwner());
        zoneCombo.getSelectionModel().select(card.getZone());
    }

    @Override
    protected void setEditable(boolean b) {
        super.setEditable(b);
    }

    public Type getType() {
        return (Type) typeCombo.getSelectionModel().getSelectedItem();
    }

    public Owner getOwner() {
        return (Owner) ownerCombo.getSelectionModel().getSelectedItem();
    }

    public Zone getZone() {
        return  (Zone) zoneCombo.getSelectionModel().getSelectedItem();
    }
}
