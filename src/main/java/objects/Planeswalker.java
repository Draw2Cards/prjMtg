package objects;

import enums.Owner;
import enums.Type;
import enums.Zone;
import interfaces.IObject;
import views.ObjectView;
import views.PlaneswalkerView;

import java.util.ArrayList;

public class Planeswalker extends Permanent implements IObject {
    private int loyalty;

    // Please to not use this constructor.
    // Index of object should not be set up manually.
    // It was created only to meet project requirements.
    public Planeswalker(int id, String name, String imgPath, Type type, Owner owner, Zone zone, boolean untapped, int loyalty) {
        super(id, name, imgPath, type, owner, zone, untapped);
        this.loyalty = loyalty;
    }

    public Planeswalker(String name, int loyalty, boolean untapped, Owner owner, Zone zone, String imgPath) {
        super(name, untapped, owner, zone, imgPath, Type.planeswalker);
        this.loyalty = loyalty;
    }

    public int getLoyalty() {
        return loyalty;
    }

    @Override
    public void setValuesFromView(ObjectView objectView) {
        super.setValuesFromView(objectView);
        PlaneswalkerView pwView = (PlaneswalkerView) objectView;
        loyalty = pwView.getLoyalty();
    }

    @Override
    public void setValuesFromArray(ArrayList<String> array) {
        array.add(3, "planeswalker");
        super.setValuesFromArray(array);
        loyalty = Integer.parseInt(array.get(6));
        untapped = Boolean.parseBoolean(array.get(7));
    }

    @Override
    public boolean isCreature() {
        return false;
    }
}
