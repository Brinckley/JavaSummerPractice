package com.brinckley.library.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    public Iterable<Book> findAll() {

        return bookRepository.findAll();
    }

    public Boolean existsById(long id) {

        return bookRepository.existsById(id);
    }

    public Optional<Book> findById(long id) {

        return bookRepository.findById(id);
    }


}
