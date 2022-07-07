package com.brinckley.JsonBookParser.book;

import com.brinckley.JsonBookParser.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookParserRepository extends JpaRepository<Book, Long> {
}
