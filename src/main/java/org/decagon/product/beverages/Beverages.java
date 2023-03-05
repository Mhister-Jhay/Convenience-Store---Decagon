package org.decagon.product.beverages;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.decagon.product.Product;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
@Data
public class Beverages extends Product {

    private Integer bevOrderQuantity;
    private Integer bevQuantityLeft;
    private Double bevCost;
    private Double totalBevAmount = 0.0;
    private Integer totalBevQuantity =0;
    private Integer bevOption;
    public ArrayList<Beverages> beverageList = new ArrayList<>();
    Beverages(String ID, String name, Double price, Integer quantity){
        super(ID,name,price,quantity);
    }

    public Beverages(){
        super();
    }

    public void listOfBeverages() throws IOException {
        BeverageList beveragesList = () ->{
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("/Users/mac/IdeaProjects/convenienceStoreWeek4/src/main/resources/ConvenienceStoreProducts.xlsx"));
            XSSFSheet beverageSheet = workbook.getSheetAt(0);
            int lastRow = beverageSheet.getLastRowNum();
            int limit = 0;
            for (int i = 0; i <= lastRow; i++) {
                XSSFRow row = beverageSheet.getRow(i);
                if(row!= null) {
                    XSSFCell cell = row.getCell(0);
                    if(cell != null && cell.getCellType() != CellType.BLANK){
                        limit = i;
                    }
                }
            }
            for(int j = 1; j<= limit; j++){
                XSSFRow beverageRow = beverageSheet.getRow(j);
                beverageList.add(new Beverages(beverageRow.getCell(0).getStringCellValue(),
                        beverageRow.getCell(1).getStringCellValue(),
                        beverageRow.getCell(2).getNumericCellValue(),
                        (int) beverageRow.getCell(3).getNumericCellValue()));
            }
        };
        beveragesList.declareBeverages();
    }
}
