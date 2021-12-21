package factories;

import enums.ObjectType;
import enums.Owner;
import enums.Type;
import enums.Zone;
import objects.*;
import objects.Obj;

public class ObjectFactory {
    public Obj createObject(ObjectType objectType) {
        Obj obj;

        switch (objectType)
        {
            // Unfortunately requirement for the project is that there shouldn't be parameterless constructors.
            case object -> obj = new Obj("", "");
            case creature -> obj = new Creature("", -1, -1, true, Owner.player, Zone.exile, "");
            case planeswalker -> obj = new Planeswalker("", -1, true, Owner.player, Zone.exile, "");
            case card -> obj = new Card("", Type.token, Owner.player, Zone.exile, "");
            case spell -> obj = new Spell("", Type.instant, Owner.player, "");
            case dungeon -> obj = new Dungeon("", -1, -1, Owner.player, Zone.exile,"");
            default -> throw new IllegalStateException("Unexpected value: " + objectType);
        }

        return obj;
    }
}
