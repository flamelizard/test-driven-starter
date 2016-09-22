package org.tdd.library;

import org.tdd.library.exceptions.BookDoesNotExists;
import org.tdd.library.exceptions.LibraryException;
import org.tdd.library.exceptions.UserAlreadyExists;
import org.tdd.library.exceptions.UserDoesNotExist;

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
    - set owner on the book, make a lease on reader instance
Return book

Expectation
===========
BookLibrary public, the other classes private
BookLibrary methods using Reader and Book parameters would be private in real
 world example
 */
public class BookLibrary {
    public static final int BORROW_LENGTH = 30;
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
            throws UserAlreadyExists {

        registerNewReader(new Reader(name, age, permanentStay));
    }

    public void registerNewReader(Reader reader)
            throws UserAlreadyExists {

        if (!addReader(reader)) {
            throw new UserAlreadyExists(reader.getName());
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

    public void borrowBook(String readerName, String bookTitle)
            throws LibraryException {

        Reader reader = getUser(readerName);
        borrowBook(reader, bookTitle);
    }

    public void borrowBook(Reader reader, String bookTitle)
            throws LibraryException {

        if (reader == null) {
            throw new UserDoesNotExist();
        }
        Book book = getBook(bookTitle);
        if (book == null) {
            throw new BookDoesNotExists();
        }
        reader.borrows(book);
    }

    public void returnBook(String readerName, String title) throws LibraryException {
        returnBook(getUser(readerName), title);
    }

    private void returnBook(Reader reader, String title) throws LibraryException {
        reader.returns(getBook(title));
    }

    public boolean isBookAvailable(String title) {
        return getBook(title).isAvailable();
    }
}
