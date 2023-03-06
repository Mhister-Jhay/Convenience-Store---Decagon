package org.decagon;


import org.decagon.customer.Customer;
import org.decagon.customer.CustomerService;
import org.decagon.customer.CustomerThreads;
import org.decagon.product.Product;
import org.decagon.product.beverages.Beverages;
import org.decagon.staff.applicant.Applicant;
import org.decagon.staff.cashier.Cashier;
import org.decagon.staff.cashier.CashierService;
import org.decagon.staff.manager.Manager;
import org.decagon.staff.manager.ManagerService;


import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Manager manager = new Manager();
        manager.declareManager();

        Cashier cashier = new Cashier();
        cashier.ListOfCashier();
        cashier.CheckIn();

        Applicant applicant = new Applicant();
        applicant.ListOfApplicant();
//
		System.out.println("*********************************************** FIRE CASHIER");
		System.out.println("--------------------------------------------------------------");
		System.out.println("Let the Manager Fire a Cashier (Minimum lateness allowed is 4)");
		System.out.println("--------------------------------------------------------------");
		System.out.println();
        ManagerService managerService = new ManagerService(cashier, manager, applicant);
        managerService.fireCashier();

		System.out.println();

		System.out.println("*********************************************** HIRE CASHIER");
		System.out.println();
        managerService.hireCashier();

		Beverages beverages = new Beverages();
		beverages.listOfBeverages();
		Customer customer = new Customer();
		customer.declareCustomer();
		Product product = new Product();
		CashierService cashierService = new CashierService(beverages, customer,product,cashier);

		System.out.println();
		System.out.println("********************************************************************");
		System.out.println("LET US DISPLAY THE AVAILABLE STOCK FROM THE FILE BEFORE ANY PURCHASE");
		System.out.println("********************************************************************");
		System.out.println();
		System.out.println("**********************************************DISPLAYING OPENING STOCK");
		System.out.println();
		System.out.println("-----------------------------------------------------------------------");
		System.out.println();
		cashierService.openingStock(beverages.beverageList);

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("*****************************************************************");
		System.out.println("      CUSTOMER BUYS PRODUCT FROM THE STORE AND GETS INTO QUEUE         ");
		System.out.println("*****************************************************************");
		System.out.println();
		System.out.println();
		// Let's say the maximum number of customers attended to in the store is 3
		ExecutorService executor = Executors.newFixedThreadPool(customer.getCustomerList().size());
		for (int i = 0; i < customer.getCustomerList().size(); i++){
			CustomerService customerService = new CustomerService(beverages, customer.getCustomerList().get(i), cashier);
			CustomerThreads customerThread = new CustomerThreads(customerService, cashier, customer);
			executor.submit(customerThread);
		}


        while(!CustomerService.isAllPurchaseAdded){
            try{
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(cashier.customerFIFOQueue.size()== customer.getCustomerList().size()){
				executor.close();
                break;
            }
        }
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("*****************************************************************");
		System.out.println("             CASHIER CHECKS OUT CUSTOMER BASED ON FIFO        ");
		System.out.println("*****************************************************************");
		System.out.println();
		System.out.println();
		cashierService.customerCheckOutQueueFIFO();

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("*****************************************************************");
		System.out.println("      CASHIER CHECKS OUT CUSTOMER BASED ON ITEM-QUANTITY PRIORITY         ");
		System.out.println("*****************************************************************");
		System.out.println();
		System.out.println();
		cashierService.customerCheckOutQueuePriority();

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("*****************************************************************");
		System.out.println("                  CASHIER DISPLAYS CLOSING STOCK        ");
		System.out.println("*****************************************************************");
		System.out.println();
		System.out.println();
		cashierService.closingStock();
    }

}
