package org.tdd.booklibrary.exceptions;

/**
 * Created by Tom on 9/10/2016.
 */
public class UserAlreadyExists extends LibraryException {
    public UserAlreadyExists(String name) {
        super(name);
    }
}
