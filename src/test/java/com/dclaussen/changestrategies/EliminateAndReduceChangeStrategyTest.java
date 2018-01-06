package com.dclaussen.changestrategies;

/**
 * Unit test for CashRegisterData
 */

import com.dclaussen.changestragies.EliminateAndReduceChangeStrategy;
import com.dclaussen.data.CashRegisterData;
import com.dclaussen.enums.Denomination;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class EliminateAndReduceChangeStrategyTest {
    private final CashRegisterData cashRegisterData = new CashRegisterData();
    private final EliminateAndReduceChangeStrategy changeStrategy = new EliminateAndReduceChangeStrategy();

    @Before
    public void runBeforeTestMethod() {
        cashRegisterData.clear();

    }

    @Test
    public void bigHugeTest() {
        for (int i=0;i<=10000;i++) {
            setInRegister(i, i, i, i, i);
            int totalDollars = cashRegisterData.getTotalDollars();
            changeStrategy.makeChange(cashRegisterData, totalDollars);

            setInRegister(i, i, i, 0, i);
            totalDollars = cashRegisterData.getTotalDollars();
            changeStrategy.makeChange(cashRegisterData, totalDollars);

        }
    }

    @Test
    public void testSixtyEightCases() {
        Map<Integer, Boolean> testCases = new HashMap<>();

        testCases.put(-1, true);
        testCases.put(69, true);
        testCases.put(100, true);
        testCases.put(4000, true);
        for (int i=0;i!=69;i++) {
            testCases.put(i, false);
        }

        for (int changeAmount : testCases.keySet()) {
            boolean shouldFail = testCases.get(changeAmount);
            setInRegister(1, 2, 3, 4, 5); // $68
            boolean didFail = false;
            try {
                changeStrategy.makeChange(cashRegisterData, changeAmount);
            }
            catch (IllegalArgumentException ex) {
                didFail = true;
            }

            Assert.assertEquals("Change amount " + changeAmount + " failed", shouldFail, didFail);

        }
    }

    @Test
    public void testExampleInDoc() {

        setInRegister(1, 0, 3, 4, 0);
        System.out.println("Checking for " + cashRegisterData.toString());
        try {
            CashRegisterData change = changeStrategy.makeChange(cashRegisterData, 11);
            System.out.println(" Change is " + change.toString());
        }
        catch (IllegalArgumentException ex) {
//            Assert.fail("testExampleInDoc should not have failed");
        }
    }

    @Test
    public void testMod10() {
        Map<Integer, Boolean> testCases = new HashMap<>();

        for (int i=0;i!=500;i++) {
            testCases.put(i, (i % 10 != 0));
        }

        for (int changeAmount : testCases.keySet()) {
            boolean shouldFail = testCases.get(changeAmount);
            setInRegister(0, 100, 0, 0, 0);
            boolean didFail = false;
            try {
                changeStrategy.makeChange(cashRegisterData, changeAmount);
            }
            catch (IllegalArgumentException ex) {
                didFail = true;
            }

            Assert.assertEquals("Change amount " + changeAmount + " failed", shouldFail, didFail);

        }
    }

    @Test
    public void testOddAmounts() {

        int[] oddAmountsShouldFail = { 1, 3, 5, 7, 9, 235};

        // we can't make change when we don't have enough money
        for (int i: oddAmountsShouldFail) {
            try {
                setInRegister(1, 2, 0, 4, 0);
                changeStrategy.makeChange(cashRegisterData, i);
                Assert.fail("Amount " + i + " should have failed");
            } catch (IllegalArgumentException ex) {

            }
        }
    }

    private void setInRegister(int twenty, int ten, int five, int two, int one) {
        cashRegisterData.set(Denomination.TWENTY, twenty);
        cashRegisterData.set(Denomination.TEN, ten);
        cashRegisterData.set(Denomination.FIVE, five);
        cashRegisterData.set(Denomination.TWO, two);
        cashRegisterData.set(Denomination.ONE, one);
    }

}
