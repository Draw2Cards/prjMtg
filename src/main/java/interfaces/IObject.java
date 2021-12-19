package interfaces;

import views.ObjectView;

import java.util.ArrayList;

public interface IObject {
    public void setValuesFromView(ObjectView objectView);
    public void setValuesFromArray(ArrayList<String> array);
}
