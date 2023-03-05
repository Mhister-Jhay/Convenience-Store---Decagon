package org.decagon.staff.cashier;

import lombok.Data;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.decagon.product.Product;
import org.decagon.staff.Staff;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Data
public class Cashier extends Staff{

    private Integer cashier1CheckIn;
    private Integer cashier2CheckIn;
    private List<Cashier> listOfCashiers = new ArrayList<>();
    private List<Cashier> cashierCheckIn = new ArrayList<>();
    public Queue<ArrayList<Product>> customerFIFOQueue = new LinkedList<>();
    public Queue<ArrayList<Product>> customerPriorityQueue = new PriorityQueue<>(new Comparator< ArrayList<Product>>() {
        @Override
        public int compare(ArrayList<Product> ol, ArrayList<Product> o2) {
            return Integer.compare(o2.size(), ol.size());
        }
    });
    public Map<ArrayList<Product>, String> indexMap = new HashMap<>();
    public Cashier(String name, Integer age, String gender, String phoneNumber, String email) {
        super(name, age, gender, phoneNumber, email);
    }

    Cashier(Integer cashier1CheckIn, Integer cashier2CheckIn){
        this.setCashier1CheckIn(cashier1CheckIn);
        this.setCashier2CheckIn(cashier2CheckIn);
    }

    public Cashier(){
        super();
    }

    public void ListOfCashier() throws IOException {
        CashierList cashierList = () -> {
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("/Users/mac/IdeaProjects/convenienceStoreWeek4/src/main/resources/ConvenienceStoreStaff.xlsx"));
            XSSFSheet cashierSheet = workbook.getSheetAt(1);
            int lastRow = cashierSheet.getLastRowNum();
            int lastRowWithContent = 0;
            for (int i = 0; i < lastRow; i++) {
                XSSFRow row = cashierSheet.getRow(i);
                if (row != null) {
                    XSSFCell cell = row.getCell(0);
                    if (cell != null && cell.getCellType() != CellType.BLANK) {
                        lastRowWithContent = i;
                    }
                }
            }
            for (int j = 1; j <= lastRowWithContent; j++) {
                XSSFRow cashierRow = cashierSheet.getRow(j);
                getListOfCashiers().add(new Cashier(cashierRow.getCell(0).getStringCellValue(),
                        (int) cashierRow.getCell(1).getNumericCellValue(),
                        cashierRow.getCell(2).getStringCellValue(),
                        cashierRow.getCell(3).getStringCellValue(),
                        cashierRow.getCell(4).getStringCellValue()));
            }
        };
        cashierList.declareCashier();
    }

    public void CheckIn() throws IOException {
        CashierList checkInList = () -> {
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("/Users/mac/IdeaProjects/convenienceStoreWeek4/src/main/resources/ConvenienceStoreStaff.xlsx"));
            XSSFSheet checkInSheet = workbook.getSheetAt(2);
            int lastRow = checkInSheet.getLastRowNum();
            int lastRowWithContent = 0;
            for (int i = 0; i <= lastRow; i++) {
                XSSFRow row = checkInSheet.getRow(i);
                if (row != null) {
                    XSSFCell cell = row.getCell(0);
                    if (cell != null && cell.getCellType() != CellType.BLANK) {
                        lastRowWithContent = i;
                    }
                }
            }
            for (int j = 1; j <= lastRowWithContent; j++) {
                XSSFRow checkInRow = checkInSheet.getRow(j);
                getCashierCheckIn().add(new Cashier((int) checkInRow.getCell(1).getNumericCellValue(),
                                            (int) checkInRow.getCell(2).getNumericCellValue() ));
            }
        };
        checkInList.declareCashier();
    }
}
