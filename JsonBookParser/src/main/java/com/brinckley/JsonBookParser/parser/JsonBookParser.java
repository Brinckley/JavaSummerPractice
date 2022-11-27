package com.brinckley.JsonBookParser.parser;

import com.brinckley.JsonBookParser.book.Book;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JsonBookParser {

    private final String APIKey = "";

    private String website_www;
    private String website_folders;
    private String website_q;
    private String website_key;

    public JsonBookParser() {
        this.website_www = "https://www.googleapis.com";
        this.website_folders = "/books/v1/volumes?q=";
        this.website_q = "intitle";
        this.website_key = ":keyes&key=";
    }

    public JsonBookParser(String website_q) {
        this.website_www = "https://www.googleapis.com";
        this.website_folders = "/books/v1/volumes?q=";
        this.website_q = website_q;
        this.website_key = ":keyes&key=";
    }

    public String getWebsite_q() {
        return website_q;
    }

    public void setWebsite_q(String website_q) {
        this.website_q = website_q;
    }

    public URL URLBuilder() throws MalformedURLException { // building the url we gonna use later
        return new URL(website_www + website_folders + website_q + website_key + APIKey);
    }

    public String readJsonIntoString() throws IOException { // getting json info as a string
        URL url = URLBuilder();

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        String json = "";

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("HttpResponseCode: " + conn.getResponseCode());
        } else {
            Scanner scanner = new Scanner(url.openStream());

            //Write all the JSON data into a string using a scanner
            while (scanner.hasNext()) {
                json += scanner.nextLine();
            }
            //Close the scanner
            scanner.close();
        }
        return json;
    }

    public List<Book> readJsonFromURLToList() {
        String fullJson = "";
        try {
            fullJson = readJsonIntoString(); // from URL we get the json string and work with it
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonElement fileElement = JsonParser.parseString(fullJson);
        JsonObject fileObject = fileElement.getAsJsonObject();

        JsonArray jsonArrayOfBooks = fileObject.get("items").getAsJsonArray();
        List<Book> bookList = extractBookListFromJsonArray(jsonArrayOfBooks);

        return bookList; // list of extracted from JSON elements
    }

    public List<Book> readJsonFromFileToList (String filepath) throws FileNotFoundException, IOException {
        File input = new File(filepath);

        JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
        JsonObject fileObject = fileElement.getAsJsonObject();

        JsonArray jsonArrayOfBooks = fileObject.get("items").getAsJsonArray();
        List<Book> bookList = extractBookListFromJsonArray(jsonArrayOfBooks);
        return bookList;
    }

    private List<Book> extractBookListFromJsonArray(JsonArray jsonArrayOfBooks) {
        List<Book> bookList = new ArrayList<>();
        for (JsonElement element : jsonArrayOfBooks) {
            String id = "NO DATA", title = "NO DATA", publisher = "NO DATA", publishedDate = "NO DATA", description = "NO DATA", language = "NO DATA";
            List<String> categories = new ArrayList<>(), authors = new ArrayList<>();
            Integer pageCount = -1;

            JsonObject jsonBookObject = element.getAsJsonObject();

            id = jsonBookObject.get("id").getAsString();

            JsonElement jsonVolumeElement = jsonBookObject.get("volumeInfo");
            JsonObject jsonVolumeObject = jsonVolumeElement.getAsJsonObject();

            if(jsonVolumeObject.has("title"))
                title = jsonVolumeObject.get("title").getAsString();
            if(title.length() >= 255)
                title = title.substring(0, 254);

            if(jsonVolumeObject.has("pageCount"))
                pageCount = jsonVolumeObject.get("pageCount").getAsInt();

            if(jsonVolumeObject.has("publisher"))
                publisher = jsonVolumeObject.get("publisher").getAsString();
            if(publisher.length() >= 255)
                publisher = publisher.substring(0, 254);

            if(jsonVolumeObject.has("publishedDate"))
                publishedDate = jsonVolumeObject.get("publishedDate").getAsString();

            if(jsonVolumeObject.has("description"))
                description = jsonVolumeObject.get("description").getAsString();
            if(description.length() >= 255)
                description = description.substring(0, 254);

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
                    authors.toString().substring(1, authors.toString().length() - 1),
                    publisher,
                    publishedDate,
                    description,
                    pageCount,
                    categories.toString().substring(1, categories.toString().length() - 1),
                    language,
                    id);
            bookList.add(book);
        }

        return bookList;
    }

    public void writeJsonToFile(String filepath) {
        String json = "";
        try {
            json = readJsonIntoString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(json);
        printWriter.close();
    }
}
