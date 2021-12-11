package objects;

import enums.Owner;
import enums.Type;
import enums.Zone;
import interfaces.IObject;

public class Spell extends Card implements IObject {
    private static int cardOnStack = 0;
    private int spellPos;

    // Please to not use this constructor.
    // Both Index of object and Spell position on stack should not be setted up manually.
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
}
