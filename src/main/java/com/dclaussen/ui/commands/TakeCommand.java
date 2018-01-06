package com.dclaussen.ui.commands;

import com.dclaussen.data.CashRegisterData;
import com.dclaussen.services.CashRegisterService;
import com.dclaussen.ui.UIUtil;

/**
 * Take bills in each denomination: #$20 #$10 #$5 #$2 #$1
 * and show current state
 * > take 1 4 3 0 10
 * $43 1 0 3 4 0
 */
public class TakeCommand implements Command {

    @Override
    public void runCommand(String[] options) {
        CashRegisterData newCashRegisterData = UIUtil.parse(options);
        CashRegisterService.getInstance().take(newCashRegisterData);
        UIUtil.show(CashRegisterService.getInstance().getCashRegisterData());
    }

    public String getCommandName() {
        return "take";
    }

}
