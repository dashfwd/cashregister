package com.dclaussen;

import com.dclaussen.ui.CommandProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Starting point of the cash register application; this just
 * loops showing a prompt, and uses the CommandProcessor to handle the actual commands.
 */
class CashRegisterMain {
    public static void main(String args[]) {
        CashRegisterMain cr = new CashRegisterMain();
        cr.run();
    }

    private void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("ready\n");
        try {
            while (true) {
                System.out.print("> ");
                String input = br.readLine();
                CommandProcessor commandProcessor = new CommandProcessor(input);
                commandProcessor.process();

            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
