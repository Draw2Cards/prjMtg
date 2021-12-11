package objects;

import enums.Owner;
import enums.Type;
import enums.Zone;
import interfaces.IObject;
import views.ObjectView;
import views.PlaneswalkerView;

public class Planeswalker extends Permanent implements IObject {
    private int loyalty;

    // Please to not use this constructor.
    // Index of object should not be setted up manually.
    // It was created only to meet project requirements.
    public Planeswalker(int id, String name, String imgPath, Type type, Owner owner, Zone zone, boolean untapped, int loyalty) {
        super(id, name, imgPath, type, owner, zone, untapped);
        this.loyalty = loyalty;
    }

    public Planeswalker(String name, int loyalty, boolean untapped, Owner owner, Zone zone, String imgPath) {
        super(name, untapped, owner, zone, imgPath);
        this.loyalty = loyalty;
    }

    public int getLoyalty() {
        return loyalty;
    }

    @Override
    public void getValuesFromView(ObjectView objectView) {
        super.getValuesFromView(objectView);
        PlaneswalkerView pwView = (PlaneswalkerView) objectView;
        loyalty = pwView.getLoyalty();
    }

    @Override
    public boolean isCreature() {
        return false;
    }
}
