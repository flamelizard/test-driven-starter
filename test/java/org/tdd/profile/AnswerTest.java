package org.tdd.profile;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

/**
 * Created by Tom on 9/1/2016.
 */
public class AnswerTest {
    @Test
    public void matchAgainstNullReturnsFalse() {
        assertFalse(new Answer(new BooleanQuestion(2, ""), Bool.TRUE)
                .match(null));
    }
}