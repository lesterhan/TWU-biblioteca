package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by hanlei on 8/1/14.
 */
public class MenuTest {

    private Menu menu;
    private PrintStream out;
    private BufferedReader reader;

    @Before
    public void setUp(){
        out = mock(PrintStream.class);
        reader = mock(BufferedReader.class);
        menu = new Menu(out, reader);

    }

    @Test
    public void shouldPrintWelcomeMessage(){

        menu.displayWelcome();
        verify(out).println("Welcome to Biblioteca!!!!!");
    }

    @Test
    public void shouldDisplayMenu(){
        menu.display();
        verify(out).println("Menu");
        verify(out).println("1. Print Book List");
    }

}
