package factories;

import enums.ObjectType;
import enums.Owner;
import enums.Type;
import enums.Zone;
import objects.*;
import objects.MtgObject;

public class ObjectFactory {
    public MtgObject createObject(ObjectType objectType) {
        MtgObject mtgObject;

        switch (objectType)
        {
            // Unfortunately requirement for the project is that there shouldn't be parameterless constructors.
            case object -> mtgObject = new MtgObject("", "");
            case creature -> mtgObject = new Creature("", -1, -1, true, Owner.player, Zone.exile, "");
            case planeswalker -> mtgObject = new Planeswalker("", -1, true, Owner.player, Zone.exile, "");
            case card -> mtgObject = new Card("", Type.token, Owner.player, Zone.exile, "");
            case spell -> mtgObject = new Spell("", Type.instant, Owner.player, "");
            case dungeon -> mtgObject = new Dungeon("", -1, -1, Owner.player, Zone.exile,"");
            default -> throw new IllegalStateException("Unexpected value: " + objectType);
        }

        return mtgObject;
    }
}
