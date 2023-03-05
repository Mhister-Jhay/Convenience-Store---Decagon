package org.decagon.customer;

import lombok.Data;
import org.decagon.product.Product;

import java.util.*;

@Data
public class Customer {


    private String name;
    private List<Customer> customerList = new ArrayList<>();
    private ArrayList<Product> customerPurchaseList;


    // The queue in which all customer's purchase is checked out base on FIFO

    public Customer(String name){
        this.setName(name);
        this.setCustomerPurchaseList(new ArrayList<>());
    }
    public Customer(){

    }
    public void declareCustomer(){
        CustomerList listOfCustomers = () -> {
            customerList.add(new Customer("John"));
            customerList.add(new Customer("Abigail"));
            customerList.add(new Customer("Rachael"));
            customerList.add(new Customer("Peter"));
            customerList.add(new Customer("Delight"));
        };
        listOfCustomers.declareCustomer();
    }
}
