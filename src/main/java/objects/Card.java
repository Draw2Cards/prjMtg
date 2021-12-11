package objects;

import enums.Owner;
import enums.Type;
import enums.Zone;
import interfaces.IObject;
import views.CardView;
import views.ObjectView;

public class Card extends Object implements IObject {
    private Type type;
    private Owner owner;
    private Zone zone;

    // Please to not use this constructor.
    // Index of object should not be setted up manually.
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
    public void getValuesFromView(ObjectView objectView) {
        super.getValuesFromView(objectView);
        CardView cardView = (CardView) objectView;
        type = cardView.getType();
        owner = cardView.getOwner();
        zone = cardView.getZone();
    }
}
