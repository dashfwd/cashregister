package com.dclaussen.changestragies;

import com.dclaussen.data.CashRegisterData;

/**
 * Interface for any class that implements a strategy for making change.
 */
public interface MakeChangeStrategy {
    CashRegisterData makeChange(CashRegisterData existingRegister, int changeAmountInDollars);
}
