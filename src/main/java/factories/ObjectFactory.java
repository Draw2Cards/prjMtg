package factories;

import enums.ObjectType;
import enums.Owner;
import enums.Type;
import enums.Zone;
import objects.*;
import objects.Object;

public class ObjectFactory {
    public Object createObject(ObjectType objectType) {
        Object object;

        switch (objectType)
        {
            // Unfortunately requirement for the project is that there shouldn't be parameterless constructors.
            case object -> object = new Object("", "");
            case creature -> object = new Creature("", -1, -1, true, Owner.player, Zone.exile, "");
            case planeswalker -> object = new Planeswalker("", -1, true, Owner.player, Zone.exile, "");
            case card -> object = new Card("", Type.token, Owner.player, Zone.exile, "");
            case spell -> object = new Spell("", Type.instant, Owner.player, "");
            case dungeon -> object = new Dungeon("", -1, -1, Owner.player, Zone.exile,"");
            default -> throw new IllegalStateException("Unexpected value: " + objectType);
        }

        return object;
    }
}
