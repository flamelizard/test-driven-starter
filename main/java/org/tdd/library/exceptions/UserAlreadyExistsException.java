package org.tdd.library.exceptions;

/**
 * Created by Tom on 9/10/2016.
 */
public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String name) {
        super(name);
    }
}
