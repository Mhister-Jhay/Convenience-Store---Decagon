package org.decagon.staff.cashier;

import java.io.FileNotFoundException;
import java.io.IOException;

@FunctionalInterface
public interface CashierList {
    public void declareCashier() throws IOException;
}
