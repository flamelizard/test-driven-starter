package org.tdd.profile;

/**
 * Created by Tom on 8/29/2016.
 */
public abstract class Question {
    private final int id;   // question ID
    private String text;

    public Question(int id, String text) {
        this.text = text;
        this.id = id;
    }

    public String getText() {
        return text;
    }

    //    strange, it just compares expected condition? no text compare?
    abstract public boolean match(int expected, int actual);
}
