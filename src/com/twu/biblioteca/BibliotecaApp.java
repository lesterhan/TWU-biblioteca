package com.twu.biblioteca;

import java.io.*;
import java.util.*;
public class BibliotecaApp {


    public static void main(String[] args) {
        Map<String, Book> bookMap = new HashMap<String, Book>();
        bookMap.put("A Wrinkle In Time", new Book("A Wrinkle In Time", "Madeline L'engle", 1995));
        bookMap.put("Great Gatsby", new Book("Great Gatsby", "F. Scott Fitzgerald", 1953));
        bookMap.put("Anne of Green Gables", new Book("Anne of Green Gables", "A Lady", 1901));
        Library library = new Library(bookMap, System.out);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BibliotecaApp ba = new BibliotecaApp(library, System.out, reader);
        ba.start();

    }

    PrintStream out;
    BufferedReader reader;
    private Library library;

    public BibliotecaApp(Library library, PrintStream out, BufferedReader reader) {
        this.out=out;
        this.reader = reader;
        this.library = library;
    }

    public void start() {
        displayWelcome();
        boolean keepGoing = true;
        while(keepGoing) {
            displayMenu();
            keepGoing = chooseOption(Integer.parseInt(getUserInput(reader)));
        }
    }

    public void displayMenu() {
        out.println("Menu");
        out.println("0. Quit");
        out.println("1. Print Book List");
        out.println("2. Check Out Book");
    }

    public void displayWelcome() {
        out.println("Welcome to Biblioteca!!!!!");
    }

    public String getUserInput(BufferedReader reader) {
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

    public boolean chooseOption(int choice) {
        boolean keepGoing = true;
        if(choice == 0) {
            out.println("Goodbye!");
            keepGoing =  false;
        }
        else if(choice==1){
            library.printBookList(out);
        }
        else if(choice == 2) {
            attemptCheckout();
        }
        else {
            out.println("Option not valid, please choose again");
        }
        return keepGoing;
    }

    private void attemptCheckout() {
        out.println("Enter Title of Book:");
        String input = getUserInput(reader);
        if( library.checkOutWithTitle(input) ){
            out.println("Thank you! Enjoy the book.");
        } else {
            out.println("That book is not available");
        }
    }
}
