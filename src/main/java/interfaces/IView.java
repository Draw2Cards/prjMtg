package interfaces;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import objects.Object;

public interface IView {
    public GridPane view(Object obj);
    public GridPane create();

    String getName();

    String getImgPath();
}
