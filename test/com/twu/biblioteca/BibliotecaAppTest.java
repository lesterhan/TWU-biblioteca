package com.twu.biblioteca;

import java.io.*;
import java.util.*;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BibliotecaAppTest {
    private BibliotecaApp app;
    private PrintStream out;
    private InputStream in;
    private BufferedReader br;
    Library library;
    @Before
    public void SetUp() throws IOException {
        Map<String,Book> bookMap = new HashMap<String, Book>();
        Book b1 = mock(Book.class);
        when(b1.getDetails()).thenReturn("A Wrinkle In Time | Madeline L'engle      | 1995");
        bookMap.put("A Wrinkle In Time",b1);
        Book b2 = mock(Book.class);
        when(b2.getDetails()).thenReturn("Great Gatsby and t| F. Scott Fitzgerald   | 1953");
        bookMap.put("Great Gatsby and the Long Title",b2);
        this.out = mock(PrintStream.class);
        library = new Library(bookMap, out);
        this.br = mock(BufferedReader.class);
        this.app = new BibliotecaApp(library, out, br);
    }


    @Test
    public void welcomeMessageIsProduced() {
        app.displayWelcome();
        verify(out).println("Welcome to Biblioteca!!!!!");
    }


    @Test
    public void showMenuOnStartup(){
        app.displayMenu();
        verify(out).println("Menu");
        verify(out).println("1. Print Book List");
    }

    @Test
    public void when1IsChosenPrintList(){
        Library mockLib = mock(Library.class);
        app = new BibliotecaApp(mockLib, out, br);
        app.chooseOption(1);
        verify(mockLib).printBookList(out);
    }

    @Test
    public void userInputRetrieved() throws IOException {
        when(this.br.readLine()).thenReturn("1");
        assertThat(app.getUserInput(br), is("1"));

    }

    @Test
    public void shouldNotifyUserOnInvalidMenuOption() {
        app.chooseOption(-1);
        verify(out).println("Option not valid, please choose again");
    }
    @Test
    public void shouldQuitOnQuit() {
        app.chooseOption(1);
        app.chooseOption(0);
        verify(out).println("Goodbye!");
    }

    @Test
    public void shouldDisplaySuccessMessageUponSuccessfulCheckout() throws IOException {
        when(br.readLine()).thenReturn("A Wrinkle In Time");
        app.chooseOption(2);
        verify(out).println("Thank you! Enjoy the book.");
    }

    @Test
    public void shouldFailureMessageUponUnsuccessfulCheckout() throws IOException {
        when(br.readLine()).thenReturn("Hitchhikers Guide to the Galaxy");
        app.chooseOption(2);
        verify(out).println("That book is not available");
    }
}
