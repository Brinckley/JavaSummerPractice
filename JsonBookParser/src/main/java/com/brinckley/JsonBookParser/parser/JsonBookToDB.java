package com.brinckley.JsonBookParser.parser;

import com.brinckley.JsonBookParser.book.Book;
import com.brinckley.JsonBookParser.book.BookParserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonBookToDB {

    private JsonBookParser jsonBookParser;

    public JsonBookToDB() {
        jsonBookParser = new JsonBookParser();
    }

    public JsonBookToDB(String q) {
        jsonBookParser = new JsonBookParser(q);
    }

    public void saveBookListToDB(List<Book> bookList) {

    }

//    public static void main(String[] args) {
//        JsonBookParser jsonBookParser = new JsonBookParser();
//        BookParserService bookService = new BookParserService();
//        List<Book> bookList = new ArrayList<>();
//        try {
//            bookList = jsonBookParser.readJsonFromFileToList("D:/C/data.json");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(bookList.size());
//        for (Book book : bookList) {
//            System.out.println(".......");
//            bookService.save(book);
//        }
//    }
}
