package com.twu.biblioteca;

/**
 * Created by hanlei on 8/1/14.
 */
public class PrintBookListCommand implements Command {
    private final Library library;

    public PrintBookListCommand(Library library) {
        this.library = library;
    }

    @Override
    public void execute() {
        library.printBookList();
    }
}
