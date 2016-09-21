package org.tdd.library.exceptions;

/**
 * Created by Tom on 9/14/2016.
 */
/* Exception wrapper for all Book library exceptions
*
* Not sure if it is a good practice but I can use this "parent" class as
* polymorphic class to handle / catch all subclassed exception classes.
*
* */
public class LibraryException extends Exception {
    public LibraryException() {
    }

    public LibraryException(String text) {
        super(text);
    }
}
