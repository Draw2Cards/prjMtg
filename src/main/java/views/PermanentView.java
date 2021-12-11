package views;

import interfaces.IView;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import objects.Object;
import objects.Permanent;

public abstract class PermanentView extends CardView implements IView {

    private CheckBox checkBox;

    @Override
    public GridPane view(Object obj) {
        GridPane gridPane = getGrid();
        GridPane rightPane = rightPane(gridPane);
        setValue(obj);
        this.setEditable(false);
        return rightPane;
    }

    @Override
    protected GridPane getGrid(){
        GridPane gridPane = super.getGrid();
        Label label = new Label();
        label.setText("Untapped:");
        checkBox = new CheckBox();
        gridPane.add(label, 0, yPos);
        gridPane.add(checkBox, 1, yPos++);

        return gridPane;
    }

    @Override
    protected void setValue(Object obj){
        super.setValue(obj);
        Permanent permanent = (Permanent) obj;
        checkBox.setSelected(permanent.isUntapped());
    }

    @Override
    protected void setEditable(boolean b) {
        super.setEditable(b);
    }

    public boolean isUntapped() {
        return checkBox.isSelected();
    }
}
