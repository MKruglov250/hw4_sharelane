package org.example.Utilities;

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
                    .parse(new FileReader("src/main/java/org/example/resources/books.json"));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    static JSONArray jsonArray = (JSONArray) jsonObject.get("books");

    public static String getBookName(int i) {
        JSONObject book = (JSONObject) jsonArray.get(i-1);
        return book.get("name").toString();
    }

    public static String getBookAuthor(int i) {
        JSONObject book = (JSONObject) jsonArray.get(i-1);
        return book.get("author").toString();
    }

}
