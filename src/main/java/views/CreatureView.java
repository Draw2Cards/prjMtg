package views;

import interfaces.IView;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.GridPane;
import objects.Creature;
import objects.MtgObject;

public class CreatureView extends PermanentView implements IView {

    private Spinner<Integer> powerSpinner;
    private Spinner<Integer>toughSpinner;

    @Override
    public GridPane view(MtgObject obj) {
        GridPane gridPane = getGrid();
        GridPane rightPane = rightPane(gridPane);
        setValue(obj);
        this.setEditable(false);
        return rightPane;
    }

    @Override
    protected GridPane getGrid(){
        GridPane gridPane = super.getGrid();
        Label powerLabel = new Label();
        powerLabel.setText("Power:");
        Label toughnessLabel = new Label();
        toughnessLabel.setText("Toughness:");

        powerSpinner = new Spinner<>();
        SpinnerValueFactory<Integer> powValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,999);
        powerSpinner.setValueFactory(powValueFactory);

        toughSpinner = new Spinner<>();
        SpinnerValueFactory<Integer> toughValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,999);
        toughSpinner.setValueFactory(toughValueFactory);

        gridPane.add(powerLabel, 0, yPos);
        gridPane.add(powerSpinner, 1, yPos++);
        gridPane.add(toughnessLabel, 0, yPos);
        gridPane.add(toughSpinner, 1, yPos++);

        return gridPane;
    }

    @Override
    public void setValue(MtgObject obj){
        super.setValue(obj);
        Creature cre = (Creature) obj;
        powerSpinner.getValueFactory().setValue(cre.getPower());
        toughSpinner.getValueFactory().setValue(cre.getTough());
    }

    @Override
    protected void setEditable(boolean b) {
        super.setEditable(b);
        powerSpinner.setEditable(b);
        toughSpinner.setEditable(b);
    }

    public int getPower() {
        return powerSpinner.getValue();
    }

    public int getToug() {
        return toughSpinner.getValue();
    }
}
