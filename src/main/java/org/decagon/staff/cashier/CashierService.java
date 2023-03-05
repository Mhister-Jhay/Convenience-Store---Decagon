package org.decagon.staff.cashier;

import org.decagon.customer.CustomerService;
import org.decagon.product.beverages.Beverages;
import org.decagon.customer.Customer;
import org.decagon.product.Product;

import java.util.ArrayList;

public class CashierService implements CashierActions{
	
	Beverages beverages;
	Product product;
	Customer customer;
	Cashier cashier;
	public CashierService(Beverages beverages, Customer customer, Product product, Cashier cashier){
		this.beverages = beverages;
		this.customer = customer;
		this.product = product;
		this.cashier = cashier;
	}
	
	@Override
	public void openingStock(ArrayList<Beverages> list) {
		System.out.println();
		System.out.println("*************************************************DISPLAYING BEVERAGES");
		System.out.println();
		System.out.println("-----------------------------------------------------------------------");
		System.out.println();
		System.out.printf("%10s","---------------BEVERAGES-------------");
		System.out.println();
		System.out.printf("%-12s%-20s%-10s%-5s","PRODUCT-ID","NAME","PRICE($)","QUANTITY");
		System.out.println();
		System.out.print("----------------------------------------------");
			for(Beverages bev:list){
				System.out.println();
				System.out.printf("%-12s%-20s%-10.2f%-5d",bev.ID,bev.name,bev.price,bev.quantity);
			}
		
	}
		
	@Override
	public void customerCheckOutQueueFIFO() {
		while(!cashier.customerFIFOQueue.isEmpty()) {
			ArrayList<Product> poll = cashier.customerFIFOQueue.poll();
			String i = cashier.indexMap.get(poll);
			System.out.println();
	        System.out.println();
			System.out.println("----------------------- ORDER FOR "+i+" -----------");
			System.out.println();
			System.out.println();
			System.out.println("*****************************RECEIPT******************************");
			System.out.println("------------------------------------------------------------------");
			System.out.printf("%-15s%-20s%-10s%-10s%-10s","PRODUCT-ID","NAME","PRICE($)","QUANTITY","AMOUNT($)");
			System.out.println();
			product.setTotalProductAmount(0.0);
			product.setTotalProductQuantity(0);
			for (Product pro:poll) {
				System.out.println();
				System.out.printf("%-15s%-20s%-10.2f%-10d%-10.2f",pro.ID,pro.name,pro.price,pro.productQuantity,pro.amount);
				product.setTotalProductAmount(product.getTotalProductAmount()+ pro.amount);
				product.setTotalProductQuantity(product.getTotalProductQuantity() + pro.productQuantity);
				}
			System.out.println();
			System.out.print("-----------------------------------------------------------");
			System.out.println();
			System.out.printf("%-45s%-10d","Total Order",product.getTotalProductQuantity());
			System.out.println();
			System.out.print("------------------------------------------------------------");
			System.out.println();
			System.out.printf("%-55s%-10.2f","Total Amount ($)",product.getTotalProductAmount());
			System.out.println();
			System.out.print("------------------------------------------------------------");
			System.out.println();
			System.out.println("************ THANKS FOR SHOPPING WITH US "+i+" *********************");
		}
		
	}
	
	@Override
	public void customerCheckOutQueuePriority() {
		while(!cashier.customerPriorityQueue.isEmpty()) {
			ArrayList<Product> poll = cashier.customerPriorityQueue.poll();
			String i = cashier.indexMap.get(poll);
	        System.out.println();
	        System.out.println();
			System.out.println("----------------------- ORDER FOR "+i+" -----------");
			System.out.println();
			System.out.println("*****************************RECEIPT******************************");
			System.out.println("------------------------------------------------------------------");
			System.out.printf("%-15s%-20s%-10s%-10s%-10s","PRODUCT-ID","NAME","PRICE($)","QUANTITY","AMOUNT($)");
			System.out.println();
			product.setTotalProductAmount(0.0);
			product.setTotalProductQuantity(0);
			for (Product pro:poll) {
				System.out.println();
				System.out.printf("%-15s%-20s%-10.2f%-10d%-10.2f",pro.ID,pro.name,pro.price,pro.productQuantity,pro.amount);
				product.setTotalProductAmount(product.getTotalProductAmount()+ pro.amount);
				product.setTotalProductQuantity(product.getTotalProductQuantity() + pro.productQuantity);
			}
			System.out.println();
			System.out.print("-----------------------------------------------------------");
			System.out.println();
			System.out.printf("%-45s%-10d","Total Order",product.getTotalProductQuantity());
			System.out.println();
			System.out.print("------------------------------------------------------------");
			System.out.println();
			System.out.printf("%-55s%-10.2f","Total Amount ($)",product.getTotalProductAmount());
			System.out.println();
			System.out.print("------------------------------------------------------------");
			System.out.println();
			System.out.println("************ THANKS FOR SHOPPING WITH US "+i+" *********************");
	   }
	}

	public void closingStock() {
		System.out.println();
		System.out.println("**********************************************DISPLAYING CLOSING STOCK");
		System.out.println();
		System.out.println("-----------------------------------------------------------------------");
		System.out.println();
	
		System.out.println();
		System.out.println("*****DISPLAYING BEVERAGES");
		System.out.println();
		System.out.println("-----------------------------------------------------------------------");
		System.out.println();
		System.out.println();
		System.out.printf("%-15s%-20s%-10s%-10s","PRODUCT-ID","NAME","PRICE($)","QUANTITY");
		System.out.println();
		int i = 0;
		while (i< 11) {
			System.out.println();
			System.out.printf("%-15s%-20s%-10.2f%-10d",beverages.getBeverageList().get(i).ID,
													   beverages.getBeverageList().get(i).name,
													   beverages.getBeverageList().get(i).price,
													   beverages.getBeverageList().get(i).quantity);
			i++;
		}
		System.out.println();
		System.out.println("--------------------------------------------------------------------");
		
	}
		
}
