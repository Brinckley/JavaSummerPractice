package com.brinckley.library.book;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    public List<Book> getBooks() {    // something like this should be done with API Litres
        return List.of(
                new Book(
                        1L,
                        "Имя розы",
                        "Умберто Эко",
                        1980,
                        710,
                        "978-5-271-35678-0",
                        "Елена Костюкович",
                        16
                )
        );
    }
}
