package staff.manager;

import org.decagon.staff.applicant.Applicant;
import org.decagon.staff.cashier.Cashier;
import org.decagon.staff.manager.Manager;
import org.decagon.staff.manager.ManagerService;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ManagerServiceTest {
    Manager manager = new Manager();
    Cashier cashier= new Cashier();
    Applicant applicant = new Applicant();
    ManagerService managerService = new ManagerService(cashier,manager,applicant);

    @Test
    public void fireCashierTest() throws IOException {
        manager.declareManager();
        cashier.ListOfCashier();
        cashier.CheckIn();

        managerService.fireCashier();
        List<Cashier> actualUpdatedCashierList = cashier.getListOfCashiers();
        assertFalse(actualUpdatedCashierList.isEmpty());

        String cashierLeftName = actualUpdatedCashierList.get(0).getName();

        assertEquals(1, actualUpdatedCashierList.size());
        assertEquals("Ebuka Philip", cashierLeftName);
    }

    @Test
    public void hireCashierTest() throws IOException {
        manager.declareManager();
        cashier.ListOfCashier();
        cashier.CheckIn();
        managerService.fireCashier();


        applicant.ListOfApplicant();
        managerService.hireCashier();
        List<Cashier> actualUpdatedCashierList = cashier.getListOfCashiers();
        assertFalse(actualUpdatedCashierList.isEmpty());

        String newCashierName = actualUpdatedCashierList.get(1).getName();

        assertEquals(2, actualUpdatedCashierList.size());
        assertEquals("Joan Peterock", newCashierName);
    }
}
