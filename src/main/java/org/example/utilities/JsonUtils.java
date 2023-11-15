package org.example.utilities;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileReader;
import java.io.IOException;


public class JsonUtils {

    static JSONParser parser = new JSONParser();
    static JSONObject jsonObject;

    static {
        try {
            jsonObject = (JSONObject) parser
                    .parse(new FileReader("src/resources/books.json"));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    static JSONArray jsonArray = (JSONArray) jsonObject.get("books");

    public static JSONObject getBookJson (int i) {
        return (JSONObject) jsonArray.get(i-1);
    }

}
