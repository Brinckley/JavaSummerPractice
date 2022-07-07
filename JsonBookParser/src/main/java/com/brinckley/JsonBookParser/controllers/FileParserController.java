package com.brinckley.JsonBookParser.controllers;

import com.brinckley.JsonBookParser.book.Book;
import com.brinckley.JsonBookParser.book.BookParserService;
import com.brinckley.JsonBookParser.parser.JsonBookParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FileParserController {

    private final BookParserService bookParserService;

    @Autowired
    public FileParserController(BookParserService bookParserService) {
        this.bookParserService = bookParserService;
    }

    @GetMapping("/load-file")
    private String fileIntoTheTable(Model model) {
        JsonBookParser jsonBookParser = new JsonBookParser();
        List<Book> bookList = new ArrayList<>();
        try {
            bookList = jsonBookParser.readJsonFromFileToList("D:/C/data.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Book book : bookList) {
            bookParserService.save(book);
        }
        return "load-file";
    }

}
