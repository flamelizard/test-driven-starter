package org.tdd.library;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 9/10/2016.
 */
public class Reader {

    private final String name;
    private final int age;
    private final String permanentStay;
    private final List<Book> bookcase = new ArrayList<>();

    public Reader(String name, int age, String permanentStay) {

        this.name = name;
        this.age = age;
        this.permanentStay = permanentStay;
    }

    public String getName() {
        return name;
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

    public void borrows(Book book) {
        bookcase.add(book);
    }
}
