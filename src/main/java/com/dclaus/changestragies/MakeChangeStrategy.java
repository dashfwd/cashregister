package com.dclaus.changestragies;

import com.dclaus.data.CashRegisterData;

/**
 * Interface for any class that implements a strategy for making change.
 */
public interface MakeChangeStrategy {
    CashRegisterData makeChange(CashRegisterData existingRegister, int changeAmountInDollars);
}
