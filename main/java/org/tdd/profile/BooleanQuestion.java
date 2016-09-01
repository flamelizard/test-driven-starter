package org.tdd.profile;

/**
 * Created by Tom on 8/29/2016.
 */
public class BooleanQuestion extends Question {
    public BooleanQuestion(int id, String text) {
        super(id, text);
    }

    @Override
    public boolean match(int expected, int actual) {
        return expected == actual;
    }
}
