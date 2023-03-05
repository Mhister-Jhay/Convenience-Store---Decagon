package staff.cashier;

import org.decagon.staff.cashier.Cashier;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CashierListTest {

    Cashier cashier = new Cashier();

    @Test
    public void cashierListTest() throws IOException {

        cashier.ListOfCashier();

        List<Cashier> actualCashierList = cashier.getListOfCashiers();
        assertFalse(actualCashierList.isEmpty());
        String firstCashierName = cashier.getListOfCashiers().get(0).getName();

        assertEquals(2, actualCashierList.size());
        assertEquals("Abigail Richards", firstCashierName);
    }

    @Test
    public void cashierCheckInTest() throws IOException {

        cashier.CheckIn();

        List<Cashier> actualCheckInList =  cashier.getCashierCheckIn();
        assertFalse(actualCheckInList.isEmpty());
        int firstCashierCheckIn = cashier.getCashierCheckIn().get(0).getCashier1CheckIn();

        assertEquals(25, actualCheckInList.size());
        assertEquals(6, firstCashierCheckIn);
    }
}
