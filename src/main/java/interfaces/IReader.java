package interfaces;

import objects.MtgObject;

import java.io.IOException;
import java.util.ArrayList;

public interface IReader {
    public ArrayList<MtgObject> getArrayList() throws IOException;
}
