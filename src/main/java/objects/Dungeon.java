package objects;

import enums.Owner;
import enums.Type;
import enums.Zone;
import interfaces.IObject;
import views.DungeonView;
import views.ObjectView;

import java.util.ArrayList;

public class Dungeon extends Card implements IObject {
    private int curPos;
    private int maxPos;

    // Please to not use this constructor.
    // Index of object should not be set up manually.
    // It was created only to meet project requirements.
    public Dungeon(int id, String name, String imgPath, Type type, Owner owner, Zone zone, int curPos, int maxPos) {
        super(id, name, imgPath, type, owner, zone);
        this.curPos = curPos;
        this.maxPos = maxPos;
    }

    public Dungeon(String name, int curPos, int maxPos, Owner owner, Zone zone, String imgPath) {
        super(name, Type.dungeon, owner, zone, imgPath);
        this.curPos = curPos;
        this.maxPos = maxPos;
    }

    @Override
    public void setValuesFromView(ObjectView objectView) {
        super.setValuesFromView(objectView);
        DungeonView dungeonView = (DungeonView) objectView;
        curPos = dungeonView.getCurPos();
        maxPos = dungeonView.getMaxPos();
    }

    @Override
    public void setValuesFromArray(ArrayList<String> array) {
        super.setValuesFromArray(array);

    }

    public Integer getCurPos() {
        return curPos;
    }

    public Integer getMaxPos() {
        return maxPos;
    }
}
