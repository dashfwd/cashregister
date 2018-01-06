package com.dclaus.enums;

/**
 * Represents a bill of a particular denomination ($20, $10, $5, $2, or $1).
 */
public enum Denomination {
    TWENTY(20),
    TEN(10),
    FIVE(5),
    TWO(2),
    ONE(1);

    private final int dollarValue;
    Denomination(int dollarValue) {
        this.dollarValue = dollarValue;
    }

    public int getDollarValue() {
        return dollarValue;
    }

}
