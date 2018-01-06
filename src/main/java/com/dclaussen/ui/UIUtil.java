package com.dclaussen.ui;

import com.dclaussen.data.CashRegisterData;
import com.dclaussen.enums.Denomination;

/**
 * Utility class for common code used by the Command classes.  This could also be done
 * in a base class or something as well, but a utility class is probably fine here.
 */
public class UIUtil {

    /** There are no instances of this class, it's just a utility class. */
    private UIUtil() {

    }

    /**
     * Parse the command tokens into CashRegisterData, checking for common errors.
     *
     * @param parts - Array with command name first, followed by all the denomination quantities
     *               next, like ["put", "20", "10", "5", "2", "1"]
     * @return
     */
    public static CashRegisterData parse(String [] parts) {
        if (parts.length < 6) {
            throw new IllegalArgumentException("Too few quantities specified, you must specify 5 quantities.");
        }
        CashRegisterData cashRegisterData = new CashRegisterData();
        int i=1;
        for (Denomination denomination : Denomination.values()) {
            String usersDenomination = parts[i++];
            try {
                int quantity = Integer.parseInt(usersDenomination);
                cashRegisterData.set(denomination, quantity);
            }
            catch (NumberFormatException ex) {
                throw new IllegalArgumentException("The value '" + usersDenomination + " is not a valid quantity.");
            }
        }

        return cashRegisterData;
    }

    /**
     * Used by commands to show the cash register status.
     * @param crd
     */
    public static void show(CashRegisterData crd) {
        System.out.println("$" + crd.getTotalDollars() + " " + crd.toString());
    }
}
