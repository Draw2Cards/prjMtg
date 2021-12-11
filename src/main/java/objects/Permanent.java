package objects;

import enums.Owner;
import enums.Type;
import enums.Zone;
import interfaces.IObject;

public abstract class Permanent extends Card implements IObject {
    private boolean untapped;

    public Permanent(int id, String name, String imgPath, Type type, Owner owner, Zone zone, boolean untapped) {
        super(id, name, imgPath, type, owner, zone);
        this.untapped = untapped;
    }

    public Permanent(String name, boolean untapped, Owner owner, Zone zone, String imgPath) {
        super(name, Type.creature, owner, zone, imgPath);
        this.untapped = untapped;
    }

    public boolean isUntapped() {
        return untapped;
    }

    // Method defined only for purpose of meeting project requirements. It has no real reason to be created.
    public abstract boolean isCreature();
}
