package org.tdd.library;

import org.tdd.library.exceptions.BookNotAvailable;
import org.tdd.library.exceptions.LibraryException;
import org.tdd.library.exceptions.ReaderMismatchOnReturnedBook;

import java.util.Calendar;

/**
 * Created by Tom on 9/10/2016.
 */
public class Book {
    private final String content;
    private final String title;
    private Reader owner;
    private Calendar borrowExpires;
    private Calendar calc;

    public Book(String name, String content) {
        this.title = name;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setOwner(Reader reader) throws LibraryException {
        if (owner != null) {
            throw new BookNotAvailable();
        }
        owner = reader;
        setWhenBorrowExpires();
    }

    private void setWhenBorrowExpires() {
        calc = Calendar.getInstance();
        calc.add(Calendar.DAY_OF_MONTH, BookLibrary.BORROW_LENGTH);
        borrowExpires = calc;
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
        if (owner == null) return false;

        Calendar today = Calendar.getInstance();
        return today.compareTo(borrowExpires) > 0;
    }
}
