package com.dclaus.ui.commands;

/**
 * Common interface used for all commands.
 */
public interface Command {
    /**
     * Run the command.
     * @param options an array of String representing each of the (space separated) parameters to the string.
     */
    void runCommand(String[] options);

    /**
     *
     * @return The name of the command that is typed in on the command line for this command.
     */
    String getCommandName();
}
