package org.tdd.booklibrary;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Tom on 9/22/2016.
 */
public class ExpirationTest {

    private Expiration expiration;

    @Before
    public void setup() {
        expiration = new Expiration();
    }

    @Test
    public void expiresWhenExpirationLengthSetNegative() {
        expiration.set(-1);

        assertTrue(expiration.hasExpired());
    }

    @Test
    public void doesNotExpireWhenExpirationGreaterThanZero() {
        expiration.set(1);

        assertFalse(expiration.hasExpired());
    }

    @Test
    public void doesNotExpireWhenExpirationNotSet() {
        assertFalse(expiration.hasExpired());
    }
}