package com.dclaussen.changestragies;

import com.dclaussen.data.CashRegisterData;
import com.dclaussen.enums.Denomination;

/**
 * This class implements a strategy for making change.
 *
 * The general idea here is to do the following:
 *
 * 1. Take all the bills out of the register that won't be used for making change.
 *       For example, if we are making change for $7, we won't need any $20's, $10s,
 *       and we won't need any more that one $5 bill, no more than three $2 bills, and
 *       no more than seven $1 bills.
 *
 *       While this step isn't probably strictly necessary, it makes the remaining algorithm
 *       faster because it doesn't need to consider as many possibilities.
 *
 * 2. Using the remaining bills, starting from the largest bill try to combine the bills
 *    in order to make the exact amount.  If that works, we're done.
 *
 * 3. Else remove the largest bill from the candidate bills and go back to step 2.
 *
 *
 */
public class EliminateAndReduceChangeStrategy implements MakeChangeStrategy{
    @Override
    public
    CashRegisterData makeChange(CashRegisterData existingRegister, int changeAmountInDollars) {
        CashRegisterData changeToUser = new CashRegisterData();

        if (changeAmountInDollars < 0) {
            throw new IllegalArgumentException("Change amount must be greater than zero.");
        }

        if (changeAmountInDollars == 0) {
            return changeToUser; // this is really just a performance shortcut.
        }

        // Phase I:
        // During this phase, we'll make a copy of the register data, removing all the bills
        // that aren't needed when making change.
        CashRegisterData candidateBills = new CashRegisterData();
        candidateBills.copyValuesFrom(existingRegister);
        for (Denomination denomination: Denomination.values()) {
            while (true) {
                int totalAmountOfThisBill = candidateBills.get(denomination) * denomination.getDollarValue();
                if (totalAmountOfThisBill > changeAmountInDollars) {
                    candidateBills.decrement(denomination);
                }
                else {
                    break;
                }
            }
        }

        // Phase II:
        // During this phase, we'll try to make change given the remaining candidate bills.
        // If we can't make change, we'll try again after removing the largest bill, until
        // we either (a) make change or (b) fail
        while(candidateBills.getTotalDollars() >= changeAmountInDollars) {

            int remainingChangeAmount = changeAmountInDollars;

            // Next, we'll make a copy of the candidate coins, so we can operate on them
            CashRegisterData candidateBillsForThisRun = new CashRegisterData();
            candidateBillsForThisRun.copyValuesFrom(candidateBills);

            // We'll also make something to hold the money that is removed from the register
            changeToUser = new CashRegisterData();

            // Go through each denomination, starting with the largest bills first
            for (Denomination denomination : Denomination.values()) {
                // We will keep processing this denomination while:
                // (a) The remaining change is more than the denomination amount
                // (b) We have bills of the current denomination
                // (c) The register still has more money in it than we need in change
                while (remainingChangeAmount >= denomination.getDollarValue() &&
                        candidateBillsForThisRun.get(denomination) > 0 &&
                        candidateBillsForThisRun.getTotalDollars() >= remainingChangeAmount
                        ) {
                    candidateBillsForThisRun.decrement(denomination);
                    changeToUser.increment(denomination);
                    remainingChangeAmount -= denomination.getDollarValue();

                }
            }

            if (remainingChangeAmount != 0) {
                // At this point, our attempt to make change failed, so we'll remove the highest
                // bill from the
                candidateBills.removeHighestBill();
            }
            else {
                // otherwise, we were successful so we'll stop looping.
                break;
            }
        }

        if (changeToUser.getTotalDollars() != changeAmountInDollars) {
            throw new IllegalArgumentException("sorry");
        }

        CashRegisterData updatedRegister = new CashRegisterData();
        updatedRegister.copyValuesFrom(existingRegister);
        updatedRegister.take(changeToUser);

        // double check that the results make sense
        assert(existingRegister.getTotalDollars() - changeAmountInDollars == updatedRegister.getTotalDollars());
        assert(changeToUser.getTotalDollars() == changeAmountInDollars);

        existingRegister.copyValuesFrom(updatedRegister);

        return changeToUser;
    }

}
