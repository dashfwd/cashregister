package com.dclaus.ui;

import com.dclaus.ui.commands.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Parses commands into tokens and delegates the handling of each type of command
 * to an implementation of the "Command" interface.
 */
public class CommandProcessor {
    private final String command;

    private static final Map<String,Command> commandMap = new HashMap<>();

    static {
        // make a list of all the commands the processor supports
        List<Command> definedCommands = new ArrayList<>();
        definedCommands.add(new ChangeCommand());
        definedCommands.add(new HelpCommand());
        definedCommands.add(new PutCommand());
        definedCommands.add(new QuitCommand());
        definedCommands.add(new ShowCommand());
        definedCommands.add(new TakeCommand());

        // NOTE: in an ideal system we wouldn't have to "declare" these above, and
        // instead we'd use a service discovery like feature to automatically register these.

        // allow them to be looked up by their name
        for (Command cmd : definedCommands){
            commandMap.put(cmd.getCommandName().toLowerCase(), cmd);
        }
    }

    public CommandProcessor(String command) {
        if (command == null) {
            command = "";
        }
        this.command = command.toLowerCase().trim();

    }

    /**
     * process the current command
     */
    public void process() {
        if ("".equals(command)) {
            return;
        }

        String[] parts = command.split(" ");
        if (parts.length > 0) {
            String firstWord = parts[0];
            Command command = commandMap.get(firstWord);
            if (command == null) {
                System.out.println("Unknown command, try 'help' for instructions");
                System.out.println("");
            }
            else {
                try {
                    command.runCommand(parts);
                }
                catch (IllegalArgumentException ex) {
                    System.out.println(ex.getMessage());
                }
                System.out.println("");
            }
            
        }
    }
}
