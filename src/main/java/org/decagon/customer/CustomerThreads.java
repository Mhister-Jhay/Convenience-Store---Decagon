package org.decagon.customer;


import org.decagon.staff.cashier.Cashier;

public class CustomerThreads extends Thread{

    CustomerService customerService;
    Cashier cashier;
    Customer customer;
    public CustomerThreads(CustomerService customerService, Cashier cashier, Customer customer){
        this.customerService = customerService;
        this.cashier = cashier;
        this.customer = customer;
    }

    @Override
    public void run() {
        CustomerService.isPurchaseAdded = false;
        customerService.buyProduct();
        while (!CustomerService.isPurchaseAdded) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
