package com.dclaussen.ui.commands;

import com.dclaussen.data.CashRegisterData;
import com.dclaussen.services.CashRegisterService;

/**
 * Show requested change in each denomination: #$20 #$10 #$5 #$2 #$1
 * and remove money from cash register
 *
 * > change 11
 * 0 0 1 3 0
 */
public class ChangeCommand implements Command {

    @Override
    public void runCommand(String[] options) {
        if (options.length < 2) {
            throw new IllegalArgumentException("Sorry, you must specify the amount of change you want.");
        }
        String userRequestedAmount = options[1];
        try {
            int amountOfChange = Integer.parseInt(userRequestedAmount);
            CashRegisterData changeAmounts = CashRegisterService.getInstance().change(amountOfChange);
            System.out.println(changeAmounts.toString());
        }
        catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Sorry, the value '" + userRequestedAmount + "' is not a valid quantity.");
        }
    }

    public String getCommandName() {
        return "change";
    }
}
