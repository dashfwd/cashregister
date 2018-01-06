package com.dclaussen.ui.commands;

/**
 * Command to exit the program.
 */
public class QuitCommand implements Command {

    @Override
    public void runCommand(String[] options) {
        System.exit(0);
    }

    public String getCommandName() {
        return "quit";
    }

}
