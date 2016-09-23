package org.tdd.booklibrary;

import java.util.Calendar;

/**
 * Created by Tom on 9/21/2016.
 */
public class Expiration {
    private Calendar expiresAt;

    public void set(int lengthInDays) {
        expiresAt = Calendar.getInstance();
        expiresAt.add(Calendar.DAY_OF_MONTH, lengthInDays);
    }

    public boolean hasExpired() {
        if (expiresAt == null) return false;
        return Calendar.getInstance().compareTo(expiresAt) > 0;
    }
}
