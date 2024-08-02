package com.example.library.controller;

import com.example.library.domain.Book;
import com.example.library.exception.InsufficientStockException;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping("{id}/sell")
    public ResponseEntity<Integer> buyOne(@PathVariable Long id) throws InterruptedException, InsufficientStockException {
        return ResponseEntity.ok(bookService.sellOne(id));
    }
}
