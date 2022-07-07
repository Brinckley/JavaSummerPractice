package com.brinckley.JsonBookParser.book;

import com.brinckley.JsonBookParser.book.Book;
import com.brinckley.JsonBookParser.book.BookParserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookParserService {

    private final BookParserRepository bookRepository;

    @Autowired
    public BookParserService(BookParserRepository bookRepository) {
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
