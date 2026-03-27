package com.globalbooks.catalog.service;

import com.globalbooks.catalog.model.Book;
import java.util.HashMap;
import java.util.Map;
import javax.jws.WebService;
import javax.jws.HandlerChain;


@WebService(endpointInterface = "com.globalbooks.catalog.service.CatalogService")
@HandlerChain(file = "handlers.xml")
public class CatalogServiceImpl implements CatalogService {

    // Simulating a database setup
    private static final Map<String, Book> bookDatabase = new HashMap<>();

    public CatalogServiceImpl() {
        bookDatabase.put("B101", new Book("B101", "Microservices Patterns", "Chris Richardson", 45.00));
        bookDatabase.put("B102", new Book("B102", "Domain-Driven Design", "Eric Evans", 55.00));
        bookDatabase.put("B103", new Book("B103", "SOA Governance", "Thomas Erl", 40.00));
    }

    @Override
    public Book getBookById(String bookId) {
        return bookDatabase.get(bookId); // In a real scenario, handle nulls with SOAP Faults
    }

    @Override
    public double getBookPrice(String bookId) {
        Book book = bookDatabase.get(bookId);
        if (book != null) {
            return book.getPrice();
        }
        return 0.0; // Return 0 if book not found
    }
}