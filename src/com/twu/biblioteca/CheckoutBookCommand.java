package com.twu.biblioteca;

/**
 * Created by hanlei on 8/1/14.
 */
public class CheckoutBookCommand implements Command {
    private final Menu menu;
    private final Library library;

    public CheckoutBookCommand(Library library, Menu menu) {
        this.library = library;
        this.menu = menu;
    }

    @Override
    public void execute() {
        library.checkOutWithTitle(menu.getUserInput());
    }
}
