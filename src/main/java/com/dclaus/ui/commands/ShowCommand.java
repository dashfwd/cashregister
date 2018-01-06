package com.dclaus.ui.commands;

import com.dclaus.services.CashRegisterService;
import com.dclaus.ui.UIUtil;

/**
 * Show current state, including total and each denomination:
 *    $Total #$20 #$10 #$5 #$2 #$1
 * for example, $68 1 2 3 4 5 means:
 *     Total=$68 $20x1 $10x2 $5x3 $2x4 $1x5
 *
 * Example usage:
 * > show
 * $68 1 2 3 4 5
 */
public class ShowCommand implements Command {

    public String getCommandName() {
        return "show";
    }

    @Override
    public void runCommand(String[] options) {
        UIUtil.show(CashRegisterService.getInstance().getCashRegisterData());
    }

}
