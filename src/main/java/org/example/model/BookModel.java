package org.example.model;

import lombok.Data;
import org.json.simple.JSONObject;

@Data
public class BookModel {


    int id;
    public String name;
    public String author;


    public BookModel(JSONObject jsonBook){
        this.id = Integer.parseInt(jsonBook.get("id").toString());
        this.name = jsonBook.get("name").toString();
        this.author = jsonBook.get("author").toString();
    }

    public BookModel(String name, String author, int id){
        this.id = id;
        this.name = name;
        this.author = author;
    }
}
