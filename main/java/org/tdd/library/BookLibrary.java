package org.tdd.library;

import org.tdd.library.exceptions.UserAlreadyExistsException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Tom on 9/10/2016.
 */
/*
Requirements
Add new book
Create client account
Borrow book
Return book
 */
public class BookLibrary {
    private final String name;
    private final Map<String, Book> books = new HashMap<>();
    private final Set<Reader> readers = new HashSet<>();

    public BookLibrary(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean addBook(Book book) {
        if (books.containsKey(book.getTitle())) {
            return false;
        }
        books.put(book.getTitle(), book);
        return true;
    }

    public void registerNewReader(String name, int age, String permanentStay)
            throws UserAlreadyExistsException {

        registerNewReader(new Reader(name, age, permanentStay));
    }

    public void registerNewReader(Reader reader)
            throws UserAlreadyExistsException {

        if (!addReader(reader)) {
            throw new UserAlreadyExistsException(reader.getName());
        }
    }

    private boolean addReader(Reader reader) {
        return readers.add(reader);
    }

    private Reader getUser(String name) {
//        Set API has no get(), too bad
        for (Reader reader : readers) {
            if (reader.getName().equals(name)) {
                return reader;
            }
        }
        return null;
    }

    private Book getBook(String title) {
        return books.getOrDefault(title, null);
    }

    public void borrowBook(String readerName, String bookTitle) {
        Reader reader = getUser(readerName);
        Book book = getBook(bookTitle);
        book.borrowTo(reader);
    }
}
