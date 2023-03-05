package customer;

import org.decagon.customer.Customer;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CustomerListTest{

    Customer customer = new Customer();

    @Test
    public void customerListTest(){

        customer.declareCustomer();
        List<Customer> actualCustomerList = customer.getCustomerList();
        assertFalse(actualCustomerList.isEmpty());

        String firstCustomerName = actualCustomerList.get(0).getName();

        assertEquals(5, actualCustomerList.size());
        assertEquals("John", firstCustomerName);

    }
}
