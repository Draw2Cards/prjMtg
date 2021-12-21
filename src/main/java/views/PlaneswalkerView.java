package views;

import interfaces.IView;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.GridPane;
import objects.Obj;
import objects.Planeswalker;

public class PlaneswalkerView extends PermanentView implements IView {
    private Spinner<Integer> loyalSpinner;

    @Override
    public GridPane view(Obj obj) {
        GridPane gridPane = getGrid();
        GridPane rightPane = rightPane(gridPane);
        setValue(obj);
        this.setEditable(false);
        return rightPane;
    }

    @Override
    protected GridPane getGrid(){
        GridPane gridPane = super.getGrid();
        Label loyalLabel = new Label();
        loyalLabel.setText("Loyalty:");
        loyalSpinner = new Spinner<>();
        SpinnerValueFactory<Integer> powValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,999);
        loyalSpinner.setValueFactory(powValueFactory);
        gridPane.add(loyalLabel, 0, yPos);
        gridPane.add(loyalSpinner, 1, yPos++);

        return gridPane;
    }

    @Override
    public void setValue(Obj obj){
        super.setValue(obj);
        Planeswalker pw = (Planeswalker) obj;
        loyalSpinner.getValueFactory().setValue(pw.getLoyalty());
    }

    @Override
    protected void setEditable(boolean b) {
        super.setEditable(b);
        loyalSpinner.setEditable(b);
    }

    public int getLoyalty() {
        return loyalSpinner.getValue();
    }
}
