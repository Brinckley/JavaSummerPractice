package com.brinckley.library.book;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {  // model for table

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // generating id for every new book
    private Long id;

    private String title;
    private String author;
    private int pages;
    private String isbn; // The International Standard Book Number (ISBN)
    private String translator;
    private int agelimit;

    public Book(){}

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.pages = 0;
        this.isbn = isbn;
        this.translator = "";
        this.agelimit = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    public int getAgelimit() {
        return agelimit;
    }

    public void setAgelimit(int agelimit) {
        this.agelimit = agelimit;
    }
}
