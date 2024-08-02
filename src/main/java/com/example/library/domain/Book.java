package com.example.library.domain;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity
@Table(name = "book")
@Data
@Getter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int stock;

    @Version
    private int version;
}
