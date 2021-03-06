package views;

import interfaces.IView;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.GridPane;
import objects.Obj;
import objects.Spell;

public class SpellView extends CardView implements IView {
    private Spinner<Integer> posSpinner;

    @Override
    public GridPane view(Obj obj) {
        GridPane gridPane = getGrid();
        GridPane rightPane = rightPane(gridPane);
        setValue(obj);
        setEditable(false);
        return rightPane;
    }

    @Override
    protected GridPane getGrid(){
        GridPane gridPane = super.getGrid();
        Label loyalLabel = new Label();
        loyalLabel.setText("Position:");
        posSpinner = new Spinner<Integer>();
        gridPane.add(loyalLabel, 0, yPos);
        gridPane.add(posSpinner, 1, yPos++);

        return gridPane;
    }

    @Override
    public void setValue(Obj obj){
        super.setValue(obj);
        Spell spell = (Spell) obj;

        SpinnerValueFactory<Integer> posValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,999);
        posValueFactory.setValue(spell.getPos());
        posSpinner.setValueFactory(posValueFactory);
    }

    @Override
    protected void setEditable(boolean b) {
        super.setEditable(b);
        posSpinner.setEditable(b);
    }

    public int getSpellPos() { return posSpinner.getValue(); }
}
