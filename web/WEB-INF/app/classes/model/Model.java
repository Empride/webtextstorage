package model;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by User on 28.08.2015.
 */
public class Model {
    static Model model = new Model();

    public static Model getInstance() {
        return model;
    }

    private Map<Integer, Pair<Date, String>> modelData;

    private Model() {
        modelData = new TreeMap<>();
        for (int i = 0; i < 10; i++)
            modelData.put(i, new Pair<>(new Date(), "Text" + i));
    }

    public Map<Integer, Pair<Date, String>> getModelData() {
        return modelData;
    }

    public void addData(String s) {
        modelData.put(modelData.size() + 1, new Pair<>(new Date(), s));
    }

    public void deleteLine(Integer id)
    {
        modelData.remove(id);
    }
}
