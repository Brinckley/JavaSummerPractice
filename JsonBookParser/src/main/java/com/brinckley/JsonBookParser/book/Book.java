package com.brinckley.JsonBookParser.book;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Book implements Serializable {  // model for table

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // generating id for every new book
    private Long id;

    private String title;
    private String authors;
    private String publisher;
    private String publishedDate;
    private String description;
    private Integer pageCount;
    private String categories;
    private String language;
    private String googleBooksId;

    public Book(){}

    public Book(String title,
                String authors,
                String publisher,
                String publishedDate,
                String description,
                Integer pageCount,
                String categories,
                String language,
                String googleBooksId) {
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.description = description;
        this.pageCount = pageCount;
        this.categories = categories;
        this.language = language;
        this.googleBooksId = googleBooksId;
    }
}