package objects;

import enums.Owner;
import enums.Type;
import enums.Zone;
import interfaces.IObject;
import views.CardView;
import views.ObjectView;

import java.util.ArrayList;

public class Card extends MtgObject implements IObject {
    private Type type;
    private Owner owner;
    private Zone zone;

    // Please to not use this constructor.
    // Index of object should not be set up manually.
    // It was created only to meet project requirements.
    public Card(int id, String name, String imgPath, Type type, Owner owner, Zone zone) {
        super(id, name, imgPath);
        this.type = type;
        this.owner = owner;
        this.zone = zone;
    }

    public Card(String name, Type type, Owner owner, Zone zone, String imgPath) {
        super(name, imgPath);
        this.type = type;
        this.owner = owner;
        this.zone = zone;
    }

    public Owner getOwner() {return owner;}
    public Zone getZone() {return zone;};
    public Type getType() {
        return type;
    }

    @Override
    public void setValuesFromView(ObjectView objectView) {
        super.setValuesFromView(objectView);
        CardView cardView = (CardView) objectView;
        type = cardView.getType();
        owner = cardView.getOwner();
        zone = cardView.getZone();
    }

    @Override
    public void setValuesFromArray(ArrayList<String> array) {
        super.setValuesFromArray(array);
        type = Type.valueOf(array.get(3));
        owner = Owner.valueOf(array.get(4));
        zone = Zone.valueOf(array.get(5));
    }
}
