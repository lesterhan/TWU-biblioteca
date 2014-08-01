package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.Map;

public class Library {
    private final Map<String, Book> bookMap;
    private final PrintStream out;

    public Library(Map<String, Book> bookMap, PrintStream out) {
        this.bookMap = bookMap;
        this.out = out;
    }

    public void printBookList(PrintStream out) {
        out.println(String.format("%-18s| %-22s| %-4s", "Title", "Author", "Year"));
        for(Book b : bookMap.values()) {
            out.println(b.getDetails());
        }
    }


    public Boolean checkOutWithTitle(String checkOutBookTitle) {
        boolean bookWasFound = bookMap.remove(checkOutBookTitle) != null;
        if(bookWasFound) {

            return true;
        }
        return false;
    }

}
