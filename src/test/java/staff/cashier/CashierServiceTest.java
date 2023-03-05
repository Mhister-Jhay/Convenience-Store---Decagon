package staff.cashier;

import org.decagon.customer.Customer;
import org.decagon.customer.CustomerService;
import org.decagon.product.Product;
import org.decagon.product.beverages.Beverages;
import org.decagon.staff.cashier.Cashier;
import org.decagon.staff.cashier.CashierService;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class CashierServiceTest {

    Beverages beverages = new Beverages();
    Product product = new Product();
    Customer customer = new Customer("John");
    Cashier cashier = new Cashier();
    CustomerService customerService = new CustomerService(beverages,customer,cashier);

    CashierService cashierService = new CashierService(beverages, customer, product, cashier);

    @Test
    public void customerCheckOutTest() throws IOException {
        beverages.listOfBeverages();
        customerService.buyProduct();
        while (!CustomerService.isPurchaseAdded) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        cashierService.customerCheckOutQueueFIFO();
        cashierService.customerCheckOutQueuePriority();
        assertEquals(0, cashier.customerFIFOQueue.size());
        assertEquals(0,cashier.customerPriorityQueue.size());

    }
}
