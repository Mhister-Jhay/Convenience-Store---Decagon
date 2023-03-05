package org.decagon.staff.manager;

import lombok.Data;
import org.decagon.staff.Staff;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
public class Manager extends Staff {

    private List<Manager> theManager = new ArrayList<>();
    Manager(String name, Integer age, String gender, String phoneNumber, String email){
        super(name,age,gender,phoneNumber,email);
    }

    public Manager(){
        super();
    }

    public void declareManager() throws IOException {
        ManagerList managerList = () ->{
            XSSFWorkbook workbook= new XSSFWorkbook(new FileInputStream("/Users/mac/IdeaProjects/convenienceStoreWeek4/src/main/resources/ConvenienceStoreStaff.xlsx"));
            XSSFSheet managerSheet = workbook.getSheetAt(0);
            for(int i = 1; i < 2; i++){
                XSSFRow onlyRow = managerSheet.getRow(i);
                theManager.add(new Manager(onlyRow.getCell(0).getStringCellValue(),
                        (int) onlyRow.getCell(1).getNumericCellValue(),
                        onlyRow.getCell(2).getStringCellValue(),
                        onlyRow.getCell(3).getStringCellValue(),
                        onlyRow.getCell(4).getStringCellValue()));
            }
            workbook.close();
        };
        managerList.declareManager();
    }
}
