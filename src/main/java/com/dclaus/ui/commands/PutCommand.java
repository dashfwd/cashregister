package com.dclaus.ui.commands;

import com.dclaus.data.CashRegisterData;
import com.dclaus.services.CashRegisterService;
import com.dclaus.ui.UIUtil;

/**
 * Put bills in each denomination: #$20 #$10 #$5 #$2 #$1
 * and show current state
 * > put 1 2 3 0 5
 * $128 2 4 6 4 10
 */
public class PutCommand implements Command {

    @Override
    public void runCommand(String[] options) {
        try {
            CashRegisterData newCashRegisterData = UIUtil.parse(options);
            CashRegisterService.getInstance().addToCashRegister(newCashRegisterData);
            UIUtil.show(CashRegisterService.getInstance().getCashRegisterData());
        }
        catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String getCommandName() {
        return "put";
    }

}
