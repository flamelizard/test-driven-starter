package org.tdd.library;

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

    public void borrowTo(Reader reader) {
        owner = reader;
    }
}
