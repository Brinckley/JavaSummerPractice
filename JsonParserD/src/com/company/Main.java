package com.company;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("D:/C/data.json");
        List<Book> bookList = new ArrayList<>();

        JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
        JsonObject fileObject = fileElement.getAsJsonObject();

        String kind = fileObject.get("kind").getAsString();

        JsonArray jsonArrayOfBooks = fileObject.get("items").getAsJsonArray();
        for (JsonElement element : jsonArrayOfBooks) {
            String id = "", title = "", publisher = "", publishedDate = "", description = "", language = "";
            List<String> categories = new ArrayList<>(), authors = new ArrayList<>();
            Integer pageCount = -1;

            JsonObject jsonBookObject = element.getAsJsonObject();

            id = jsonBookObject.get("id").getAsString();

            JsonElement jsonVolumeElement = jsonBookObject.get("volumeInfo");
            JsonObject jsonVolumeObject = jsonVolumeElement.getAsJsonObject();

            if(jsonVolumeObject.has("title"))
                title = jsonVolumeObject.get("title").getAsString();

            if(jsonVolumeObject.has("pageCount"))
                pageCount = jsonVolumeObject.get("pageCount").getAsInt();

            if(jsonVolumeObject.has("publisher"))
                publisher = jsonVolumeObject.get("publisher").getAsString();

            if(jsonVolumeObject.has("publishedDate"))
                publishedDate = jsonVolumeObject.get("publishedDate").getAsString();

            if(jsonVolumeObject.has("description"))
                description = jsonVolumeObject.get("description").getAsString();

            if(jsonVolumeObject.has("language"))
                language = jsonVolumeObject.get("language").getAsString();

            if(jsonVolumeObject.has("authors")) {
                JsonArray authorsArray = jsonVolumeObject.get("authors").getAsJsonArray();
                authors = new ArrayList<>();
                for (JsonElement jsonAuthorElement : authorsArray) {
                    authors.add(jsonAuthorElement.getAsString());
                }
            }

            if(jsonVolumeObject.has("categories")) {
                JsonArray categoriesArray = jsonVolumeObject.get("categories").getAsJsonArray();
                categories = new ArrayList<>();
                for (JsonElement jsonCategoriesElement : categoriesArray) {
                    categories.add(jsonCategoriesElement.getAsString());
                }
            }

            Book book = new Book(title,
                    authors.toString(),
                    publisher,
                    publishedDate,
                    description,
                    pageCount,
                    categories.toString(),
                    language,
                    id);
            bookList.add(book);
        }
        System.out.println(bookList.size());
    }
}
