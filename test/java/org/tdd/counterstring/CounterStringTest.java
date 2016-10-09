package org.tdd.counterstring;

import org.junit.Test;
import org.tdd.counterstring.clibboard.ClipboardWrap;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by Tom on 9/3/2016.
 */
/*
TDD and unit tests are great tools.

See immediately what works and what does not by running unit tests.
Unit tests describe what class behaviour is - no more, no less.

Make a change and run tests to see what you did break :-)
 */
public class CounterStringTest {

    private int length;

    @Test
    public void returnsEmptyStringOnZeroLength() throws Exception {
        length = 0;

        assertThat(CounterString.generate(length), isEmptyOrNullString());
    }

    @Test
    public void returnsDelimiterOnLengthOne() {
        length = 1;

        assertThat(CounterString.generate(length), equalTo("*"));
    }

    @Test
    public void returnsStringForLength2() {
        length = 2;

        assertThat(CounterString.generate(length), equalTo("2*"));
    }

    @Test
    public void returnsStringForLength3() {
        length = 3;

        assertThat(CounterString.generate(length), equalTo("*3*"));
    }

    @Test
    public void returnsStringForLength4() {
        length = 4;

        assertThat(CounterString.generate(length), equalTo("2*4*"));
    }

    @Test
    public void returnsStringForLength5() {
        length = 5;

        assertThat(CounterString.generate(length), equalTo("*3*5*"));
    }

    @Test
    public void returnsStringForLength10() {
        length = 10;

        String cString = CounterString.generate(length);

        assertThat(cString, equalTo("*3*5*7*10*"));
        assertTrue(cString.length() == length);
    }

    @Test
    public void length15() {
        length = 15;
        assertThat(CounterString.generate(length), equalTo("*3*5*7*9*12*15*"));
    }

    @Test
    public void matchesLengthFor50() {
        length = 50;
        assertTrue(CounterString.generate(length).length() == length);
    }

    @Test
    public void matchesLengthFor100() {
        length = 100;
        assertTrue(CounterString.generate(length).length() == length);
    }

    @Test
    public void copyStringToClipboard() {
        String string = CounterString.generate(10, true);
        assertThat(string, equalTo(ClipboardWrap.getContent()));
    }

//    This piece usually does not belong to tests that should be self-validating

//    @Test
//    public void visualTest() {
//        int[] lengths = {20, 21, 50, 51, 52, 100, 150};
//        for (int length: lengths) {
//            System.out.println(CounterString.generate(length));
//        }
//    }
}