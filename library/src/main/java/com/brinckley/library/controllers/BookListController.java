package com.brinckley.library.controllers;

import com.brinckley.library.book.Book;
import com.brinckley.library.book.BookRepository;
import com.brinckley.library.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookListController {

    private final BookService bookService;

    @Autowired
    public BookListController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book-list")
    public String output(Model model) {
        Iterable<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "book-list";
    }
}
