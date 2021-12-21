package interfaces;

import objects.Obj;

import java.io.IOException;
import java.util.ArrayList;

public interface IReader {
    ArrayList<Obj> getArrayList() throws IOException;
}
