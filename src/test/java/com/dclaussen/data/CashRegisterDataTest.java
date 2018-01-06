package com.dclaussen.data;

/**
 * Unit test for CashRegisterData
 */

import com.dclaussen.enums.Denomination;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CashRegisterDataTest {
    private final CashRegisterData cashRegisterData = new CashRegisterData();

    @Before
    public void runBeforeTestMethod() {
        cashRegisterData.clear();
    }


    @Test
    public void testSetAndGet() {
        for (Denomination denomination : Denomination.values()) {
            cashRegisterData.set(denomination, denomination.getDollarValue());
        }

        for (Denomination denomination : Denomination.values()) {
            assertEquals(denomination.getDollarValue(), cashRegisterData.get(denomination));
        }
    }

    @Test
    public void testToString() {
        // by default, the register should be empty
        assertEquals("0 0 0 0 0", cashRegisterData.toString());

        cashRegisterData.set(Denomination.TWENTY, 20);
        cashRegisterData.set(Denomination.TEN, 10);
        cashRegisterData.set(Denomination.FIVE, 5);
        cashRegisterData.set(Denomination.TWO, 2);
        cashRegisterData.set(Denomination.ONE, 1);
        assertEquals("20 10 5 2 1", cashRegisterData.toString());

        for (Denomination denomination : Denomination.values()) {
            cashRegisterData.set(denomination, denomination.getDollarValue());
        }
        assertEquals("20 10 5 2 1", cashRegisterData.toString());

        for (Denomination denomination : Denomination.values()) {
            cashRegisterData.set(denomination, 100);
        }
        assertEquals("100 100 100 100 100", cashRegisterData.toString());

    }

    @Test
    public void testCopyValuesFrom() {
        cashRegisterData.set(Denomination.TWENTY, 20);
        cashRegisterData.set(Denomination.TEN, 10);
        cashRegisterData.set(Denomination.FIVE, 5);
        cashRegisterData.set(Denomination.TWO, 2);
        cashRegisterData.set(Denomination.ONE, 1);
        CashRegisterData cloned = new CashRegisterData();
        cloned.copyValuesFrom(cashRegisterData);
        assertEquals("20 10 5 2 1", cloned.toString());



    }

    @Test(expected = IllegalArgumentException.class)
    public void testLessThanZeroError() {
        cashRegisterData.set(Denomination.TWENTY, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOverMaximumQuantity() {
        cashRegisterData.set(Denomination.TWENTY, 100001);
    }
}
