package org.tdd.library.exceptions;

/**
 * Created by Tom on 9/10/2016.
 */
public class UserAlreadyExists extends Exception {
    public UserAlreadyExists(String name) {
        super(name);
    }
}
