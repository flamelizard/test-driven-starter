package org.tdd.booklibrary;

import org.tdd.booklibrary.exceptions.BookNotAvailable;
import org.tdd.booklibrary.exceptions.LibraryException;
import org.tdd.booklibrary.exceptions.ReaderMismatchOnReturnedBook;

/**
 * Created by Tom on 9/10/2016.
 */
public class Book {
    private final String content;
    private final String title;
    private Reader owner;
    private Expiration expiration = new Expiration();

    public Book(String name, String content) {
        this.title = name;
        this.content = content;
    }

    public String getTitle() {
        expiration.hasExpired();
        return title;
    }

    public void setOwner(Reader reader) throws LibraryException {
        if (owner != null) {
            throw new BookNotAvailable();
        }
        owner = reader;
        expiration.set(BookLibrary.BORROW_LENGTH);
    }

    public void unsetOwner(Reader reader) throws LibraryException {
        if (owner != null && !owner.equals(reader)) {
            throw new ReaderMismatchOnReturnedBook();
        }
        owner = null;
    }

    public boolean isAvailable() {
        return owner == null;
    }

    public boolean isOverdue() {
        return expiration.hasExpired();
    }
}
