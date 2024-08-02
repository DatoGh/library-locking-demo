# Library Management System

## Overview

This is a personal side project I've developed to study and master locking operations and transactions in Spring Boot applications. It's a simple Library Management System that demonstrates various Spring features and libraries for handling concurrent operations and ensuring data consistency.

## Key Features

- Book inventory management
- Transactional operations for selling books
- Optimistic and pessimistic locking strategies
- Retry mechanism for handling concurrent modifications
- RESTful API for book operations

## Technologies Used

- Java 17
- Spring Boot 3.3.2
- Spring Data JPA
- PostgreSQL
- Lombok
- Spring Retry
- SpringDoc OpenAPI

## Main Components

1. **BookService**: Implements core business logic with transactional methods and retry mechanisms.
2. **BookRepository**: JPA repository with custom query for pessimistic locking.
3. **BookController**: RESTful endpoints for book operations.
4. **Exception Handling**: Custom exceptions and global exception handler.
5. **Configuration**: Retry configuration and database settings.

## Learning Focus

### Transactional Operations

This project uses `@Transactional` annotations to ensure atomic operations, particularly in the `BookService` class. This is crucial for maintaining data consistency during book sales.

### Locking Strategies

- **Optimistic Locking**: Implemented using `@Version` annotation in the `Book` entity.
- **Pessimistic Locking**: Used in `BookRepository` with `@Lock(LockModeType.PESSIMISTIC_WRITE)`.

### Retry Mechanism

The `@Retryable` annotation is used in the `sellOne` method of `BookService` to handle `ObjectOptimisticLockingFailureException`, demonstrating how to manage concurrent modifications.

### Exception Handling

Custom exceptions like `InsufficientStockException` and `RetryExhaustedException` are used along with a `GlobalExceptionHandler` to manage and respond to various error scenarios.

## Purpose

This project is my personal playground for understanding and implementing:

1. Transactional operations in Spring Boot
2. Different locking strategies (Optimistic vs Pessimistic)
3. Retry mechanisms for handling concurrent operations
4. Exception handling in a Spring Boot application

While the project uses a simple book entity, the concepts I'm exploring here can be applied to more complex systems requiring robust handling of concurrent operations and data consistency.

## API Documentation

API documentation is available through SpringDoc OpenAPI. After running the application, visit `/swagger-ui.html` for interactive API documentation.

## Notes

This is a personal study project. It's not intended for production use but rather as a learning tool for myself to better understand Spring Boot and related concepts.