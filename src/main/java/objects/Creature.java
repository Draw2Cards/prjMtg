package objects;

import enums.Owner;
import enums.Type;
import enums.Zone;
import interfaces.IObject;
import views.CreatureView;
import views.ObjectView;

public class Creature extends Permanent implements IObject {
    private int power;
    private int tough;

    // Please to not use this constructor.
    // Index of object should not be setted up manually.
    // It was created only to meet project requirements.
    public Creature(int id, String name, String imgPath, Type type, Owner owner, Zone zone, boolean untapped, int power, int tough) {
        super(id, name, imgPath, type, owner, zone, untapped);
        this.power = power;
        this.tough = tough;
    }

    public Creature(String name, int power, int tough, boolean untapped, Owner owner, Zone zone, String imgPath) {
        super(name, untapped, owner, zone, imgPath);
        this.power = power;
        this.tough = tough;
    }

    public int getPower() {
        return power;
    }

    public int getTough() {
        return tough;
    }

    @Override
    public void getValuesFromView(ObjectView objectView) {
        super.getValuesFromView(objectView);
        CreatureView creatureView = (CreatureView) objectView;
        power = creatureView.getPower();
        tough = creatureView.getToug();
    }

    @Override
    public boolean isCreature() {
        return true;
    }
}
