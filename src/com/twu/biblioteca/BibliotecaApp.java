package com.twu.biblioteca;

import java.io.*;
import java.util.*;
public class BibliotecaApp {


    private Menu menu;

    public static void main(String[] args) {
        Map<String, Book> bookMap = new HashMap<String, Book>();
        bookMap.put("A Wrinkle In Time", new Book("A Wrinkle In Time", "Madeline L'engle", 1995));
        bookMap.put("Great Gatsby", new Book("Great Gatsby", "F. Scott Fitzgerald", 1953));
        bookMap.put("Anne of Green Gables", new Book("Anne of Green Gables", "A Lady", 1901));
        Library library = new Library(bookMap, System.out);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Menu menu = new Menu(System.out, reader);
        BibliotecaApp ba = new BibliotecaApp(library, System.out, reader, menu);
        ba.start();

    }

    PrintStream out;
    BufferedReader reader;
    private Library library;

    public BibliotecaApp(Library library, PrintStream out, BufferedReader reader, Menu menu) {
        this.out=out;
        this.menu = menu;
        this.reader = reader;
        this.library = library;
    }

    public void start() {
        menu.displayWelcome();
        boolean keepGoing = true;
        while(keepGoing) {
            menu.display();
            keepGoing = chooseOption(Integer.parseInt(getUserInput(reader)));
        }
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
        Map<Integer, Command> commandMap = new HashMap<Integer, Command>();

        commandMap.put(0, new QuitAppCommand(menu));
        commandMap.put(1, new PrintBookListCommand(library));
        commandMap.put(2, new CheckoutBookCommand(library,menu));


        boolean keepGoing = true;
        if(choice == 0) {
            menu.displayQuit();
            keepGoing = false;
        }
        else if(choice==1){
            library.printBookList();
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
