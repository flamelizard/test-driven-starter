package org.tdd.library;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tdd.library.exceptions.BookDoesNotExists;
import org.tdd.library.exceptions.BookNotAvailable;
import org.tdd.library.exceptions.UserAlreadyExists;
import org.tdd.library.exceptions.UserDoesNotExist;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
    private Reader reader2 = new Reader("Dracula", 60, "Pennsylvania");
    private Book book2 = new Book("Harry Potter", "...wave magical wand");

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

    private void addReader(Reader reader) throws Exception {
        library.registerNewReader(reader);
    }

    private void addBook(Book book) {
        library.addBook(book);
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

    @Test(expected = UserAlreadyExists.class)
    public void exceptionWhenAddingDuplicateReader() throws Exception {
        library.registerNewReader("Homer Simpson", 40, "Springfield");
        library.registerNewReader("Homer Simpson", 40, "Springfield");
    }

    @Test
    public void addedBookIsAvailableForBorrow() {
        addBook(book1);

        assertTrue(library.isBookAvailable(book1.getTitle()));
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

    @Test(expected = BookNotAvailable.class)
    public void cannotBorrowAlreadyBorrowedBook() throws Exception {
        addReader(reader1);
        addReader(reader2);
        addBook(book1);

        library.borrowBook(reader1, book1.getTitle());
        library.borrowBook(reader2, book1.getTitle());
    }

    @Test
    public void readerCanBorrowMultipleBooks() throws Exception {
        addReader(reader1);
        addBook(book1);
        addBook(book2);

        library.borrowBook(reader1, book1.getTitle());
        library.borrowBook(reader1, book2.getTitle());

        assertThat(reader1.getBooks(), equalTo
                (Arrays.asList(book1, book2)));
    }

    @Test
    public void readerReturnsBook() throws Exception {
        addBook(book1);
        addReader(reader1);

        library.borrowBook(reader1.getName(), book1.getTitle());
        library.returnBook(reader1.getName(), book1.getTitle());

        assertTrue(library.isBookAvailable(book1.getTitle()));

    }

    @Test
    public void borrowIsTimeLimited() {
    }

}
