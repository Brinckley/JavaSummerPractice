package com.brinckley.library.api;

import com.brinckley.library.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class APIService {

    private final String API = "AIzaSyCPt0wvuTDENvMkY9Mf-jEmUkPaOpW1LPU";
    private String request = "zyTCAlFPjgYC";
    private String website = "https://www.googleapis.com/books/v1/volumes/";
    private String key = "?key=";

    private final BookRepository bookRepository;

    @Autowired
    public APIService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public String APIGetter() {
        String topic = "";
        RestTemplate restTemplate = new RestTemplate();
        Object res = restTemplate.getForObject(
                website + request + key + API,
                Object.class
        );

        return res.toString();
//        Object res = restTemplate.getForObject(
//                "https://www.googleapis.com/books/v1/volumes/zyTCAlFPjgYC?"
//                        + topic + "key=" + API,
//                Object.class
//        );
//
//        Object[] res2 = restTemplate.getForObject(
//                "https://www.googleapis.com/books/v1/volumes?q=flowers+inauthor:keyes"
//                        + topic + "&key=" + API,
//                Object[].class
//        );

        //  https://www.googleapis.com/books/v1/volumes?q=flowers+inauthor:keyes&key=yourAPIKey
    }


}
