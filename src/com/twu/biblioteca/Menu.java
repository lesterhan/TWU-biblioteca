package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by hanlei on 8/1/14.
 */
public class Menu {


    private final BufferedReader reader;
    private PrintStream out;

    public Menu(PrintStream out, BufferedReader reader) {
        this.out = out;
        this.reader = reader;
    }

    public String getUserInput() {
        String inputLn = "";

        try {
            inputLn = reader.readLine();
            if(inputLn.length()==0){
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputLn;
    }

    public void display() {
        out.println("Menu");
        out.println("0. Quit");
        out.println("1. Print Book List");
        out.println("2. Check Out Book");
    }

    public void displayWelcome() {
        out.println("Welcome to Biblioteca!!!!!");
    }

    public void displayQuit() {
        out.println("Goodbye!");
    }
}
