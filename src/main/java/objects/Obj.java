package objects;

import interfaces.IObject;
import views.ObjectView;

import java.util.ArrayList;

public class Obj implements IObject {

    // Please to not use this constructor.
    // Index of object should not be set up manually.
    // It was created only to meet project requirements.
    public Obj(int id, String name, String imgPath) {
        this.id = id;
        this.name = name;
        this.imgPath = imgPath;
    }

    public Obj(String name, String imgPath) {
        this.name = name;
        this.imgPath = imgPath;
        this.id = nextId++;
    }

    public String getImgPath() {return imgPath;}

    @Override
    public String toString() {
        return name;
    }

    private static int nextId = 0;
    private int id = 0;
    private String name;
    private String imgPath;


    public static int getNextId() { return nextId; }
    public int getId(){return id;}

    public String getName() {
        return name;
    }

    @Override
    public void setValuesFromView(ObjectView objectView) {
        id = objectView.getId();
        name = objectView.getName();
        imgPath = objectView.getImgPath();
    }

    @Override
    public void setValuesFromArray(ArrayList<String> array) {
        name = array.get(1);
        imgPath = array.get(2);
    }
}
