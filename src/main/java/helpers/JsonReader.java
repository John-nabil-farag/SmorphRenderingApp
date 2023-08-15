package helpers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonReader {

    Gson gson;
    public JsonReader()
    {
        gson= new Gson();
    }


    public JsonObject readJsonFileToJsonObject(String filePath)
    {
        JsonObject object = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            object= gson.fromJson(br, JsonObject.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }

    public JsonArray readJsonFileToJsonArray(String filePath)
    {
        JsonArray object = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            object= gson.fromJson(br, JsonArray.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }
}
