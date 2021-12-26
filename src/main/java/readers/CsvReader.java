package readers;

import enums.ObjectType;
import factories.ObjectFactory;
import interfaces.IReader;
import objects.*;

import java.io.*;
import java.util.ArrayList;

public class CsvReader extends Reader implements IReader {

    public static String dataPath = "D:\\state001.csv";

    public ArrayList<Obj> getArrayList() {
        ArrayList<Obj> arrayList = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(dataPath));
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
                Obj obj = objectFactory.createObject(objectType);
                obj.setValuesFromArray(arrayList1);

                arrayList.add(obj);

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
