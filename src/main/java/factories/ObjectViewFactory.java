package factories;

import enums.ObjectType;
import objects.Object;
import views.*;

public class ObjectViewFactory {
    public ObjectView createView(ObjectType objectType) {
        ObjectView objectView;

        switch (objectType)
        {
            case object -> objectView = new ObjectView();
            case creature -> objectView = new CreatureView();
            case planeswalker -> objectView = new PlaneswalkerView();
            case card -> objectView = new CardView();
            case spell -> objectView = new SpellView();
            case dungeon -> objectView = new DungeonView();
            default -> throw new IllegalStateException("Unexpected value: " + objectType);
        }

        return objectView;
    }
}
