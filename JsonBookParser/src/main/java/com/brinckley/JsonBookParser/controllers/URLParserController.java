package com.brinckley.JsonBookParser.controllers;

import com.brinckley.JsonBookParser.book.Book;
import com.brinckley.JsonBookParser.book.BookParserService;
import com.brinckley.JsonBookParser.parser.JsonBookParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Controller
public class URLParserController {

    private final BookParserService bookParserService;

    @Autowired
    public URLParserController(BookParserService bookParserService) {
        this.bookParserService = bookParserService;
    }

    @GetMapping("/load-URL")
    private String URLIntoTheTable(Model model) {
        JsonBookParser jsonBookParser = new JsonBookParser("eco");
        URL u = null;
        try {
            u = jsonBookParser.URLBuilder();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        System.out.println(u);
        List<Book> bookList = new ArrayList<>();
        bookList = jsonBookParser.readJsonFromURLToList();
        System.out.println(bookList.size());
        for (Book book : bookList) {
            bookParserService.save(book);
        }
        return "load-URL";
    }

}
