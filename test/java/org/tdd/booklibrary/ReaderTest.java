package org.tdd.booklibrary;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

/**
 * Created by Tom on 9/10/2016.
 */
public class ReaderTest {

    private final Reader tony = new Reader("Tony", 48, "LA");
    private final Reader otherTony = new Reader("Tony", 55, "Roswell");
    private final Reader jack = new Reader("Jack", 20, "Moscow");

    @Test
    public void matchesWhenComparingReadersSharingName() {
        assertThat(tony, equalTo(otherTony));
    }

    @Test
    public void notMatchesWhenReaderComparedToNull() {
        assertThat(tony, not(equalTo(null)));
    }

    @Test
    public void notMatchesWhenComparingTwoReaders() {
        assertThat(tony, not(equalTo(jack)));
    }
}