package interfaces;

import javafx.scene.layout.GridPane;
import objects.Obj;

public interface IView {
    GridPane view(Obj obj);
    GridPane create();
    void setValue(Obj obj);

    String getName();

    String getImgPath();
}
