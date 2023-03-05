package customer;

import org.decagon.customer.Customer;
import org.decagon.customer.CustomerService;
import org.decagon.product.Product;
import org.decagon.product.beverages.Beverages;
import org.decagon.staff.cashier.Cashier;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CustomerServiceTest {

    Beverages beverages = new Beverages();
    Customer customer = new Customer("John");
    Cashier cashier = new Cashier();

    CustomerService customerService = new CustomerService(beverages,customer,cashier);

    @Test
    public void customerServiceTest() throws IOException {
        beverages.listOfBeverages();
        customerService.buyProduct();

        while (!CustomerService.isPurchaseAdded) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        ArrayList<Product> actualPurchaseList = customer.getCustomerPurchaseList();
        Queue<ArrayList<Product>> actualFIFOQueue = cashier.customerFIFOQueue;
        Queue<ArrayList<Product>> actualPriorityQueue = cashier.customerPriorityQueue;
        assertFalse(actualPriorityQueue.isEmpty());
        assertFalse(actualFIFOQueue.isEmpty());
        assertFalse(actualPurchaseList.isEmpty());

        assertEquals(1, actualPurchaseList.size());
        assertEquals(1, actualFIFOQueue.size());
        assertEquals(1, actualPriorityQueue.size());

    }
}
