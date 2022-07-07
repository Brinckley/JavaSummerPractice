package com.brinckley.library.controllers;

import com.brinckley.library.book.Book;
import com.brinckley.library.book.BookRepository;
import com.brinckley.library.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddFormController {

    private final BookService bookService;

    @Autowired
    public AddFormController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/add-form")
    public String add(Model model) {
        return "add-form";
    }

    @PostMapping("/add-form")
    public String addBook(@RequestParam String title,
                          @RequestParam String author,
                          @RequestParam String isbn,
                          Model model) {

        //Book book = new Book(title, author, isbn);
        //bookService.save(book);
        return "redirect:/book-list";
    }

}
