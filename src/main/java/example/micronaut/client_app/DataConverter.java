package example.micronaut.client_app;

import example.micronaut.domain.Persona;

import java.util.Collections;
import java.util.List;

public class DataConverter {

    private static String[] columnNames = {"ID", "Name", "Age", "Gender"};

    public static String[] getColumnNames() {
        return columnNames;
    }

    public static String[][] convertFromListToArray (List<Persona> list) {
        Collections.sort(list, (x, y) -> {return x.getId() - y.getId();});
        String[][] dataArray = new String[list.size()][4];
        for (int i = 0; i < list.size(); i++) {
            dataArray[i][0] = list.get(i).getId().toString();
            dataArray[i][1] = list.get(i).getName();
            dataArray[i][2] = list.get(i).getAge().toString();
            dataArray[i][3] = list.get(i).getGender();
        }
        return dataArray;
    }

}
