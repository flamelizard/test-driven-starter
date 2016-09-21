package org.tdd.library;

import org.tdd.library.exceptions.LibraryException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 9/10/2016.
 */
public class Reader {

    private final String name;
    private final int age;
    private final String permanentStay;
    private final List<Book> books = new ArrayList<>();

    public Reader(String name, int age, String permanentStay) {

        this.name = name;
        this.age = age;
        this.permanentStay = permanentStay;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reader reader = (Reader) o;

        return name.equals(reader.name);
    }

    @Override
    public int hashCode() {
//    string hash code to spread objects hash codes uniformly
        return name.hashCode();
    }

    public void borrows(Book book) throws LibraryException {
        book.setOwner(this);
        books.add(book);
    }

    public void returns(Book book) throws LibraryException {
        book.unsetOwner(this);
        books.remove(book);
    }
}
