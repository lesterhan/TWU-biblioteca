package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LibraryTest {

    PrintStream out;
    Library library;
    private Book book1;
    private Map<String, Book> bookMap;
    private Book book2;

    @Before
    public void setUp(){
        bookMap = new HashMap<String, Book>();
        book1 = mock(Book.class);
        when(book1.getDetails()).thenReturn("Book1 Details");
        book2 = mock(Book.class);
        when(book2.getDetails()).thenReturn("Book2 Details");
        out = mock(PrintStream.class);
        library = new Library(bookMap, out);
    }

    @Test
    public void shouldPrinterHeaderRowWhenListingBooks() {
        library.printBookList(out);
        verify(out).println("Title             | Author                | Year");
    }

    @Test
    public void shouldPrintBookDetailsWhenThereIsOneBook() {
        bookMap.put("Book1", book1);

        library.printBookList(out);

        verify(out).println("Book1 Details");
    }

    @Test
    public void shouldPrintBookDetailsWhenThereAreTwoBooks() {
        bookMap.put("Book1", book1);
        bookMap.put("Book2", book2);

        library.printBookList(out);
        verify(out).println("Book2 Details");
    }

    @Test
    public void removeBookFromLibraryUponCheckOut() {
        bookMap.put("Book1", book1);

        library.checkOutWithTitle("Book1");

        assertThat(bookMap.containsKey("Book1"), is(false));
    }

//    @Test
//    public void shouldDisplaySuccessMessageUponSuccessfulCheckout() throws IOException {
//        bookMap.put("Book1",book1);
//        library.checkOutWithTitle("Book1");
//        verify(out).println("Thank you! Enjoy the book.");
//    }

//    @Test
//    public void shouldFailureMessageUponUnsuccessfulCheckout() throws IOException {
//        when(br.readLine()).thenReturn("Hitchhikers Guide to the Galaxy");
//        app.chooseOption(2);
//        verify(out).println("That book is not available");
//    }
}