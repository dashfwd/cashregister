package com.dclaus.ui.commands;

/**
 * Simple help, shows usage.
 */
public class HelpCommand implements Command {

    @Override
    public void runCommand(String[] options) {
        System.out.println("You can use the following commands:");
        System.out.println("  show - shows the current quantity of each denomination in the register");
        System.out.println("  put {quantities} - puts a given quantity of each bill into the register");
        System.out.println("  take {quantities} - takes a given quantity of each bill from the register");
        System.out.println("  change {dollar amount} - makes change for the given amount from the register");
        System.out.println("  quit - exit the program");
    }

    public String getCommandName() {
        return "help";
    }

}
