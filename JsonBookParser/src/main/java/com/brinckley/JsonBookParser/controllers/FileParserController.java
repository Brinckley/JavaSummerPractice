package com.brinckley.JsonBookParser.controllers;

import com.brinckley.JsonBookParser.book.Book;
import com.brinckley.JsonBookParser.book.BookParserService;
import com.brinckley.JsonBookParser.parser.JsonBookParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody // "D:/C/data.json"
    // /load-file?path=D:/C/data.json
    private String fileIntoTheTable(@RequestParam String path, Model model) {
        JsonBookParser jsonBookParser = new JsonBookParser();
        List<Book> bookList = new ArrayList<>();
        try {
            bookList = jsonBookParser.readJsonFromFileToList(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Book book : bookList) {
            bookParserService.save(book);
        }
        return "load-file";
    }

}
