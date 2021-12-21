package objects;

import enums.Owner;
import enums.Type;
import enums.Zone;
import interfaces.IObject;
import views.DungeonView;
import views.ObjectView;
import views.SpellView;

import java.util.ArrayList;

public class Spell extends Card implements IObject {
    private static int cardOnStack = 0;
    private int spellPos;

    // Please to not use this constructor.
    // Both Index of object and Spell position on stack should not be set up manually.
    // It was created only to meet project requirements.
    public Spell(int id, String name, String imgPath, Type type, Owner owner, Zone zone, int spellPos) {
        super(id, name, imgPath, type, owner, zone);
        this.spellPos = spellPos;
    }

    public Spell(String name, Type type, Owner owner, String imgPath) {
        super(name, type, owner, Zone.stack, imgPath);
        spellPos = cardOnStack++;
    }

    public int getPos() {
        return spellPos;
    }

    @Override
    public void setValuesFromArray(ArrayList<String> array) {
        array.add("stack");
        super.setValuesFromArray(array);
    }

    @Override
    public void setValuesFromView(ObjectView objectView) {
        super.setValuesFromView(objectView);
        SpellView spellView = (SpellView) objectView;
        spellPos = spellView.getSpellPos();
    }
}
