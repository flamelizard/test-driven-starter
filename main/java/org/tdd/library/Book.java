package org.tdd.library;

import org.tdd.library.exceptions.BookNotAvailable;
import org.tdd.library.exceptions.LibraryException;
import org.tdd.library.exceptions.ReaderMismatchOnReturnedBook;

/**
 * Created by Tom on 9/10/2016.
 */
public class Book {
    private final String content;
    private final String title;
    private Reader owner;

    public Book(String name, String content) {
        this.title = name;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void borrowTo(Reader reader) throws LibraryException {
        if (owner != null) {
            throw new BookNotAvailable();
        }
        owner = reader;
    }

    public boolean isAvailable() {
        return owner == null;
    }

    public void returnBy(Reader reader) throws LibraryException {
        if (!owner.equals(reader)) {
            throw new ReaderMismatchOnReturnedBook();
        }
        owner = null;
    }
}
