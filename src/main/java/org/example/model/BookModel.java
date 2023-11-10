package org.example.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.json.JSONPropertyName;

@Data
public class BookModel {

    @SerializedName(value = "id")
    public String Id;

    @SerializedName(value = "name")
    public String Name;

    @SerializedName(value = "author")
    public String Author;
}

