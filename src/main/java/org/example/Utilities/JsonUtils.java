package org.example.Utilities;

//import org.json.simple.JSONArray;
//import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class JsonUtils {

    public static String getBookName(int i) throws IOException, ParseException {
        JSONArray jsonArray = new JSONArray();

        JSONObject book = (JSONObject) jsonArray.get(i-1);
        return book.get("name").toString();
    }

}
