package com.company;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void getJSONIntoFile() throws IOException {
        URL url = new URL("https://www.googleapis.com/books/v1/volumes?q=inauthor:keyes&key=???????");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("HttpResponseCode: " + conn.getResponseCode());
        } else {

            String inline = "";
            Scanner scanner = new Scanner(url.openStream());

            //Write all the JSON data into a string using a scanner
            while (scanner.hasNext()) {
                inline += scanner.nextLine();
            }
            //Close the scanner
            scanner.close();

            FileWriter fileWriter = new FileWriter("D:/C/data.json");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(inline);
            printWriter.close();
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

        getJSONIntoFile();

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
