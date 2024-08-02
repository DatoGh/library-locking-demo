package com.example.library.repository;

import com.example.library.domain.Book;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface  BookRepository extends JpaRepository<Book, Long> {
    @Query("select b from Book b where b.id = :id")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Book> findByIdWithLock(Long id);
}
