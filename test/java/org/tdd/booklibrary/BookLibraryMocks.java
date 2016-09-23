package org.tdd.booklibrary;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by Tom on 9/23/2016.
 */
/*
Mock
Injected mock seems to have only working methods preset through "when().then
()". Other, non-overridden methods, seems to be no-operation, they do
nothing.
 */
public class BookLibraryMocks {

    @Mock
    Expiration expireMock;
    @InjectMocks
    Book book;
    private String name = "National Lib";
    private BookLibrary library = new BookLibrary(name);
    private Reader reader1 = new Reader("Homer", 40, "Greece");
    private Book book1 = new Book("Oliver Twist", "...slammed the door");

    private void addReader(Reader reader) throws Exception {
        library.registerNewReader(reader);
    }

    private void addBook(Book book) {
        library.addBook(book);
    }

    @Before
    public void injectMocks() {
        book = new Book("Big Gatsby", "..life is a party");
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void bookIsOverdueWhenBorrowExpired() throws Exception {
        when(expireMock.hasExpired()).thenReturn(true);

        addBook(book);
        addReader(reader1);

        library.borrowBook(reader1, book.getTitle());

        assertTrue(book.isOverdue());
    }
}
