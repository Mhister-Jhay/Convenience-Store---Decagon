package org.decagon.staff.cashier;

import org.decagon.product.beverages.Beverages;

import java.util.ArrayList;

public interface CashierActions {
	
	public void openingStock(ArrayList<Beverages> list);
	
	public void customerCheckOutQueuePriority();
	
	public void customerCheckOutQueueFIFO();
	
	public void closingStock();

}
