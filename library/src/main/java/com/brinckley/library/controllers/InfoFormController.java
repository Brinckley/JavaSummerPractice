package com.brinckley.library.controllers;

import com.brinckley.library.book.Book;
import com.brinckley.library.book.BookRepository;
import com.brinckley.library.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class InfoFormController {
// extracting info from the database

    private final BookService bookService;

    @Autowired
    public InfoFormController (BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book-list/{id}")
    public String info(@PathVariable(value = "id") long id, Model model) {
        if(bookService.existsById(id)) {
            Optional<Book> optional = bookService.findById(id);
            List<Book> res = new ArrayList<>();
            optional.ifPresent(res::add);
            model.addAttribute("book", optional);
            return "info-form";
        }
        return "redirect:/book-list";
    }

}
