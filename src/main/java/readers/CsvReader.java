package readers;

import enums.ObjectType;
import factories.ObjectFactory;
import interfaces.IReader;
import objects.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CsvReader extends Reader implements IReader {
    public ArrayList<MtgObject> getArrayList() throws IOException {
        ArrayList<MtgObject> arrayList = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("D:\\state001.csv"));
            String line = reader.readLine();
            while (line != null) {

                String[] array = line.split(";");
                ArrayList<String> arrayList1 = new ArrayList<>();
                for (String var : array)
                {
                    arrayList1.add(var);
                }

                ObjectFactory objectFactory = new ObjectFactory();
                ObjectType objectType = ObjectType.valueOf(arrayList1.get(0));
                MtgObject mtgObject = objectFactory.createObject(objectType);
                mtgObject.setValuesFromArray(arrayList1);

                arrayList.add(mtgObject);

                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return arrayList;
    }
}
