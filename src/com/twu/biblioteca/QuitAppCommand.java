package com.twu.biblioteca;

/**
 * Created by hanlei on 8/1/14.
 */
public class QuitAppCommand implements Command {

    private final Menu menu;

    public QuitAppCommand(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void execute() {
        menu.displayQuit();
    }
}
