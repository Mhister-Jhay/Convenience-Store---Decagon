package org.decagon.customer;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.decagon.product.Product;
import org.decagon.staff.cashier.Cashier;
import org.decagon.product.beverages.Beverages;

import javax.swing.*;
import java.awt.*;
import java.io.*;


public class CustomerService {

	Beverages beverages;
	Customer customer;
	Cashier cashier;
	public static boolean isAllPurchaseAdded = false;
	public static boolean isPurchaseAdded = false;
	public CustomerService(Beverages beverages, Customer customer, Cashier cashier) {
		this.beverages = beverages;
		this.customer = customer;
		this.cashier = cashier;
	}

	synchronized public void buyProduct() {
		JLabel label = new JLabel();
		JFrame frame = new JFrame();
		JButton beverageButton = new JButton("Beverages");
		label.setText("Welcome "+customer.getName()+"!      Please SELECT product");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.TOP);
		label.setOpaque(true);
		label.setBackground(new Color(58, 100, 100));
		label.setSize(400, 50);
		beverageButton.addActionListener(e -> {
			String[] beverageChoice = new String[beverages.getBeverageList().size()];
			for(int i = 0; i < beverages.getBeverageList().size(); i++){
				beverageChoice[i] = beverages.getBeverageList().get(i).name;
			}
			JFrame newFrame = new JFrame();
			newFrame.setTitle("Select Beverages "+customer.getName());
			JComboBox<String> comboBox = new JComboBox<>(beverageChoice);
			JTextField quantityTextField = new JTextField(2);
			JButton selectButton = new JButton("Select");
			selectButton.addActionListener(e1 -> {
				String selectedBeverage = (String) comboBox.getSelectedItem();
				int orderQuantity = Integer.parseInt(quantityTextField.getText());
				int Quantity = getBeverageQuantity(selectedBeverage);
				if (Quantity == 0) {
					newFrame.dispose();
					JOptionPane.showMessageDialog(null, "OUT OF STOCK!\n"
							+ "Please Select A Different Beverage Product", "Out of Stock", JOptionPane.INFORMATION_MESSAGE);
				} else {
					beverages.setBevOrderQuantity(orderQuantity);
					if (beverages.getBevOrderQuantity() < Quantity) {
						beverages.setBevQuantityLeft(Quantity - beverages.getBevOrderQuantity());
						int index = getIndex(selectedBeverage);

						File productFile = new File("/Users/mac/IdeaProjects/convenienceStoreWeek4/src/main/resources/ConvenienceStoreProducts.xlsx");
						FileInputStream fis = null;
						try {
							fis = new FileInputStream(productFile);
						} catch (FileNotFoundException ex) {
							throw new RuntimeException(ex);
						}

						// Open the work book
						XSSFWorkbook productWorkbook = null;
						try {
							productWorkbook = new XSSFWorkbook(fis);
						} catch (IOException ex) {
							throw new RuntimeException(ex);
						}
						// Opening beverage sheet
						XSSFSheet BevSheet = productWorkbook.getSheetAt(0);
						XSSFRow BevRow = BevSheet.getRow(index+1);
						XSSFCell BevQuantity0 = BevRow.getCell(3);

						// Updating the quantity in the product file
						BevQuantity0.setCellValue(beverages.getBevQuantityLeft());

						try {
							fis.close();
						} catch (IOException ex) {
							throw new RuntimeException(ex);
						}
						FileOutputStream fos = null;
						try {
							fos = new FileOutputStream(productFile);
						} catch (FileNotFoundException ex) {
							throw new RuntimeException(ex);
						}
						try {
							productWorkbook.write(fos);
						} catch (IOException ex) {
							throw new RuntimeException(ex);
						}
						try {
							fos.close();
						} catch (IOException ex) {
							throw new RuntimeException(ex);
						}

						//Updating the beverage list containing all beverage objects
						beverages.getBeverageList().get(index).quantity = beverages.getBevQuantityLeft();

						beverages.setBevCost(beverages.getBevOrderQuantity() * beverages.getBeverageList().get(index).price);

						// Adding new order to the Purchase list
						customer.getCustomerPurchaseList().add(new Product(beverages.getBeverageList().get(index).ID,
								beverages.getBeverageList().get(index).name,
								beverages.getBeverageList().get(index).price,
								beverages.getBevOrderQuantity(),
								beverages.getBevCost()));

						beverages.setTotalBevAmount(beverages.getTotalBevAmount() + beverages.getBevCost());
						beverages.setTotalBevQuantity(beverages.getTotalBevQuantity() + beverages.getBevOrderQuantity());

						JLabel label1 = new JLabel("You've added\n" + beverages.getBevOrderQuantity() + "\n" + selectedBeverage + "\n to your Cart." + "\n Amount:\n" + beverages.getBevCost());
						JFrame finalFrame = new JFrame();
						JButton orderProduct = new JButton("Order More Products");
						JButton checkOut = new JButton("Check-Out");
						orderProduct.addActionListener(e112 -> {
							finalFrame.dispose();
						});
						checkOut.addActionListener(e113 -> {
							cashier.customerFIFOQueue.add(customer.getCustomerPurchaseList());
							cashier.indexMap.put(customer.getCustomerPurchaseList(), customer.getName());
							cashier.customerPriorityQueue.add(customer.getCustomerPurchaseList());
							isPurchaseAdded = true;
							if(cashier.customerFIFOQueue.size() == customer.getCustomerList().size()
									&& cashier.customerPriorityQueue.size() == customer.getCustomerList().size()) {
								isAllPurchaseAdded = true;
								isPurchaseAdded = true;
								finalFrame.dispose();
								newFrame.dispose();
								frame.dispose();
							}
							finalFrame.dispose();
							newFrame.dispose();
							frame.dispose();
						});
						JPanel finalButtonPanel = new JPanel();
						finalButtonPanel.add(orderProduct);
						finalButtonPanel.add(checkOut);

						JPanel mainPanel = new JPanel(new BorderLayout());
						mainPanel.add(label1, BorderLayout.NORTH);
						mainPanel.add(finalButtonPanel, BorderLayout.CENTER);
						finalFrame.add(mainPanel);
						finalFrame.pack();
						finalFrame.setTitle(customer.getName()+" --Check-Out");
						finalFrame.setVisible(true);
					} else if (beverages.getBevOrderQuantity() > Quantity) {
						JOptionPane.showMessageDialog(null, "Order exceeding Stock\n"
								+ "Reduce Order Quantity", "Quantity Not Available", JOptionPane.WARNING_MESSAGE);
						newFrame.dispose();
					}

				}
			});
			JPanel panel = new JPanel();
			panel.add(new JLabel("Beverage:"));
			panel.add(comboBox);
			panel.add(new JLabel("Quantity:"));
			panel.add(quantityTextField);
			panel.add(selectButton);
			newFrame.add(panel);
			newFrame.pack();
			newFrame.setLocationRelativeTo(null);
			newFrame.setVisible(true);
		});

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(beverageButton);

		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(label, BorderLayout.NORTH);
		mainPanel.add(buttonPanel, BorderLayout.CENTER);

		frame.add(mainPanel);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(400, 100);
		frame.setTitle("Jhay's Convenience");
		frame.setVisible(true);
	}
	public int getBeverageQuantity(String beverageName) {
		for (Beverages bev : beverages.getBeverageList()) {
			if (bev.name.equals(beverageName)) {
				return bev.quantity;
			}
		}
		return 0;
	}

	public int getIndex(String beverageName) {
		for (int i = 0; i < beverages.getBeverageList().size() - 1; i++) {
			if (beverages.getBeverageList().get(i).name.equalsIgnoreCase(beverageName)) {
				return i;
			}
		}
		return 0;
	}
}
