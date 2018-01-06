package com.dclaus.services;

import com.dclaus.changestragies.EliminateAndReduceChangeStrategy;
import com.dclaus.changestragies.MakeChangeStrategy;
import com.dclaus.data.CashRegisterData;
import com.dclaus.enums.Denomination;

/**
 * This service represents the cash register, and handles operations on it.
 */
public class CashRegisterService {

    private static volatile CashRegisterService singleton = null;

    /**
     * If this were a real system, we'd probably do dependency injection, but
     * I'm keeping it simple for the purposes of this example.
     */
    private final CashRegisterData cashRegisterData = new CashRegisterData();

    /**
     * I only have one strategy for making change at this point, but I'm separating this
     * out into it's own interface/class with the idea that more could be added later.
     */
    private final MakeChangeStrategy makeChangeStrategy = new EliminateAndReduceChangeStrategy();

    public static CashRegisterService getInstance() {
        if (singleton == null) {
            synchronized (CashRegisterService.class) {
                if (singleton == null) {
                    singleton = new CashRegisterService();
                }
            }
        }
        return singleton;
    }

    /**
     *
     * @return The current cash register data
     */
    public CashRegisterData getCashRegisterData() {
        return cashRegisterData;
    }

    /**
     * Add bills to the cash register.
     * @param amountsToAdd
     */
    public void addToCashRegister(CashRegisterData amountsToAdd) {
        for (Denomination denomination : Denomination.values()) {
            try {
                cashRegisterData.add(denomination, amountsToAdd.get(denomination));
            }
            catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("Sorry, that would exceed the register's limit of $" + denomination.getDollarValue() + " bills");
            }
        }

    }

    /**
     * Take bills out of the cash register
     * @param newCashRegisterData
     */
    public void take(CashRegisterData newCashRegisterData) {
        cashRegisterData.take(newCashRegisterData);
    }

    /**
     * Make change from the cash register
     * @param amountOfChange - The desired amount of change, in dollars.
     * @return The change, broken down by each bill.
     */
    public CashRegisterData change(int amountOfChange) {
        return makeChangeStrategy.makeChange(this.cashRegisterData, amountOfChange);
    }
}
