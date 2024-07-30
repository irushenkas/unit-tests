package com.example.test.controller;

import com.example.test.model.Book;
import com.example.test.repository.BookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class HomeController {

    private final BookRepository bookRepository;

    public HomeController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/book")
    public String getBook(@RequestParam("id") int id) {
        try {
            Book book = bookRepository.find(id);
            if(book != null) {
                return book.getName();
            }
            return "Book not found by id: " + id;
        } catch (SQLException e) {
            return "Sorry, error";
        }
    }
}