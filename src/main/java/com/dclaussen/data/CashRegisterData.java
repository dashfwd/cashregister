package com.dclaussen.data;

import com.dclaussen.enums.Denomination;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class represents a collection of bills.
 */
public class CashRegisterData implements Cloneable {
    private static final int MAX_QUANTITY = 10000; // per Steven
    /**
     * Key   = Denomination (such as TWENTY)
     * Value = Quantity on hand of that denomination
     */
    private final Map<Denomination, Integer> denominationData = new ConcurrentHashMap<>();

    public CashRegisterData() {
        clear();
    }


    public void copyValuesFrom(CashRegisterData otherRegister) {
        for (Denomination denomination : Denomination.values()) {
            set(denomination, otherRegister.get(denomination));
        }
    }

    public void removeHighestBill() {
        for (Denomination denomination : Denomination.values()) {
            if (get(denomination) > 0 ) {
                decrement(denomination);
                break;
            }
        }
    }

    public void decrement(Denomination denomination) {
        assert(denomination != null);
        int existingQuantity = get(denomination);
        assert(existingQuantity > 0);
        set(denomination, existingQuantity - 1);
    }

    public void increment(Denomination denomination) {
        assert(denomination != null);
        int existingQuantity = get(denomination);
        assert(existingQuantity <= MAX_QUANTITY);
        set(denomination, existingQuantity + 1);
    }


    public void add(Denomination denomination, int amountToAdd) {
        int newQuantity = get(denomination) + amountToAdd;
        set(denomination, newQuantity);
    }

    public void set(Denomination denomination, int quantity) {
        assert(denomination != null);
        if (quantity < 0 || quantity > MAX_QUANTITY) {
            throw new IllegalArgumentException("Denomination quantity " + quantity + " is not allowed, must be between 0 and 10,000");
        }
        denominationData.put(denomination, quantity);
    }

    public int get(Denomination denomination) {
        assert(denomination != null);
        Integer denominationInteger = denominationData.get(denomination);
        if (denominationInteger == null) {
            return 0;
        }
        return denominationInteger;
    }

    public void take(CashRegisterData amountsToTake) {
        CashRegisterData updatedRegisterData = new CashRegisterData();
        updatedRegisterData.copyValuesFrom(this);

        for (Denomination denomination: Denomination.values()) {
            int currentValue = get(denomination);
            int valueToSubtract = amountsToTake.get(denomination);
            int newValue = currentValue - valueToSubtract;
            try {
                updatedRegisterData.set(denomination, newValue);
            }
            catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("Sorry, there are not enough " +
                                "$" + denomination.getDollarValue() + " bills, you asked for " +
                                valueToSubtract + "; there but there is only " + currentValue +
                                " in the register."
                );
            }
        }

        this.copyValuesFrom(updatedRegisterData);
    }

    public void clear() {
        for (Denomination denomination : Denomination.values()) {
            set(denomination, 0);
        }
    }

    public int getTotalDollars() {
        int totalDollars = 0;
        for (Denomination denomination : Denomination.values()) {
            totalDollars += get(denomination) * denomination.getDollarValue();
        }
        return totalDollars;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Denomination denomination : Denomination.values()) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(get(denomination));
        }
        return sb.toString();
    }


}
