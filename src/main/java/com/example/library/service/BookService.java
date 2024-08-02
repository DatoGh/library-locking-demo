package com.example.library.service;

import com.example.library.domain.Book;
import com.example.library.exception.InsufficientStockException;
import com.example.library.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.ExhaustedRetryException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {


    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book with id " + id + " not found"));
    }

    @Transactional
    public int getStockByIdWithLock(Long id) {
        return bookRepository.findByIdWithLock(id)
                .map(Book::getStock)
                .orElseThrow(() -> new EntityNotFoundException("Book with id " + id + " not found"));
    }


    @Retryable(retryFor = ObjectOptimisticLockingFailureException.class, maxAttempts = 5, backoff = @Backoff(delay = 500, multiplier = 2))
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public int sellOne(Long id) throws InsufficientStockException, InterruptedException {

        Book book = bookRepository.findById/*WithLock*/(id)
                .orElseThrow(() -> new EntityNotFoundException("Book with id " + id + " not found"));

        int quantity = book.getStock();

        if (quantity < 1) {
            throw new InsufficientStockException("Book with id " + id + " is out of stock");
        } else {
            Thread.sleep(10_000);
            book.setStock(quantity - 1);
            bookRepository.save(book);
            return book.getStock();
        }
    }

    @Recover
    public int sellOneRecover(ObjectOptimisticLockingFailureException ex, Long id) {
        throw new ExhaustedRetryException("Retry exhausted for book with id " + id, ex);
    }

}
