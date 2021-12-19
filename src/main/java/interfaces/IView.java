package interfaces;

import javafx.scene.layout.GridPane;
import objects.MtgObject;

public interface IView {
    public GridPane view(MtgObject obj);
    public GridPane create();
    public void setValue(MtgObject mtgObject);

    String getName();

    String getImgPath();
}
