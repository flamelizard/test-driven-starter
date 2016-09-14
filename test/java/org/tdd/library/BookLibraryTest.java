package org.tdd.library;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tdd.library.exceptions.BookDoesNotExists;
import org.tdd.library.exceptions.UserAlreadyExistsException;
import org.tdd.library.exceptions.UserDoesNotExist;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by Tom on 9/10/2016.
 */
/*
Complex tests should go to a separate class and init library before.

However compiler will let me run only "public" class and there can be only one
public class per a file (where filename matches public class name)
*/

public class BookLibraryTest {

    private String name;
    private BookLibrary library;
    private Book book1;
    private Reader reader1;

    @Before
    public void createNamedLibrary() {
        name = "Town library";
        library = new BookLibrary(name);
    }

    @Before
    public void createReaderAndBooks() {
        book1 = new Book("Oliver Twist", "...slammed the door");
        reader1 = new Reader("Homer", 40, "Greece");
    }

    //    helper method
    private void presetLibraryAndReader() throws Exception {
        library.registerNewReader(reader1);
        library.addBook(book1);
    }

    @Test
    public void libraryHasName() {
        assertThat(library.getName(), equalTo(name));
    }

    @Test
    public void canAddNonDuplicateBook() {
        Assert.assertTrue(library.addBook(book1));
    }

    @Test
    public void cannotAddDuplicateBook() {
        library.addBook(book1);
        Assert.assertFalse(library.addBook(book1));
    }

    @Test
    public void canCreateReader() throws Exception {
        library.registerNewReader("Homer Simpson", 40, "Springfield");
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void exceptionWhenAddingDuplicateReader() throws Exception {
        library.registerNewReader("Homer Simpson", 40, "Springfield");
        library.registerNewReader("Homer Simpson", 40, "Springfield");
    }

    @Test
    public void canBorrowBook() throws Exception {
        presetLibraryAndReader();

        library.borrowBook(reader1.getName(), book1.getTitle());
    }

    @Test(expected = BookDoesNotExists.class)
    public void cannotBorrowNonExistingBook() throws Exception {
        presetLibraryAndReader();

        library.borrowBook(reader1.getName(), "Non-existing title");
    }

    @Test(expected = UserDoesNotExist.class)
    public void cannotBorrowOnNonExistingReader() throws Exception {
        presetLibraryAndReader();

        library.borrowBook("Invalid-reader-name", book1.getTitle());
    }

}
