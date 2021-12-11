package objects;

import interfaces.IObject;
import views.ObjectView;

public class Object implements IObject {

    // Please to not use this constructor.
    // Index of object should not be setted up manually.
    // It was created only to meet project requirements.
    public Object(int id, String name, String imgPath) {
        this.id = id;
        this.name = name;
        this.imgPath = imgPath;
    }

    public Object(String name, String imgPath) {
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

    public static int getNextId(){return nextId;};
    public int getId(){return id;}

    public String getName() {
        return name;
    }

    @Override
    public void getValuesFromView(ObjectView objectView) {
        id = nextId++;
        name = objectView.getName();
        imgPath = objectView.getImgPath();
    }
}
