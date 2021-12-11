package views;

import interfaces.IView;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import objects.Dungeon;
import objects.Object;

public class DungeonView extends CardView implements IView {
    private Spinner<Integer> curSpinner;
    private Spinner<Integer> maxSpinner;

    @Override
    public GridPane view(Object obj) {
        GridPane gridPane = getGrid();
        GridPane rightPane = rightPane(gridPane);
        setValue(obj);
        setEditable(false);
        return rightPane;
    }

    @Override
    protected GridPane getGrid(){
        GridPane gridPane = super.getGrid();
        Label curPosLabel = new Label();
        curPosLabel.setText("Current position:");
        Label maxPosLabel = new Label();
        maxPosLabel.setText("Max position:");

        curSpinner = new Spinner<>();
        SpinnerValueFactory<Integer> powValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,999);
        curSpinner.setValueFactory(powValueFactory);

        maxSpinner = new Spinner<>();
        SpinnerValueFactory<Integer> toughValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,999);
        maxSpinner.setValueFactory(toughValueFactory);

        gridPane.add(curPosLabel, 0, yPos);
        gridPane.add(curSpinner, 1, yPos++);
        gridPane.add(maxPosLabel, 0, yPos);
        gridPane.add(maxSpinner, 1, yPos++);

        return gridPane;
    }

    @Override
    protected void setValue(Object obj){
        super.setValue(obj);
        Dungeon dungeon = (Dungeon) obj;
        curSpinner.getValueFactory().setValue(dungeon.getCurPos());
        maxSpinner.getValueFactory().setValue(dungeon.getMaxPos());
    }

    @Override
    protected void setEditable(boolean b) {
        super.setEditable(b);
        curSpinner.setEditable(b);
        maxSpinner.setEditable(b);
    }

    public int getCurPos() {
        return curSpinner.getValue();
    }

    public int getMaxPos() {
        return maxSpinner.getValue();
    }
}
