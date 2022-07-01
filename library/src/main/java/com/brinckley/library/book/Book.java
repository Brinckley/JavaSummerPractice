package com.brinckley.library.book;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

public class Book {
    private Long id; // id maybe useless (local number)
    private String title;
    private String author;
    private int year; // date of publish
    private int pages;
    private String ISBN; // The International Standard Book Number (ISBN)
    private String translator;
    private int ageLimit;

    public Book(Long id,
                String title,
                String author,
                int year,
                int pages,
                String ISBN,
                String translator,
                int ageLimit) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.pages = pages;
        this.ISBN = ISBN;
        this.translator = translator;
        this.ageLimit = ageLimit;
    }

    public Book(String title,
                String author,
                int year,
                int pages,
                String ISBN,
                String translator,
                int ageLimit) {
        this.author = author;
        this.year = year;
        this.pages = pages;
        this.ISBN = ISBN;
        this.translator = translator;
        this.ageLimit = ageLimit;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public int getPages() {
        return pages;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTranslator() {
        return translator;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", pages=" + pages +
                ", ISBN='" + ISBN + '\'' +
                ", translator='" + translator + '\'' +
                ", ageLimit=" + ageLimit +
                '}';
    }
}
