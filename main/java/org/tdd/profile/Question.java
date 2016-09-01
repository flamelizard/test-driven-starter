package org.tdd.profile;

/**
 * Created by Tom on 8/29/2016.
 */
public abstract class Question {
    private String text;

    public Question(int id, String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
