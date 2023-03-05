package org.decagon.staff.applicant;

import lombok.Data;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
public class Applicant {

    private String name;
    private Integer age;
    private String gender;
    private String phoneNumber;
    private String email;
    private String location;
    private String qualification;

    private List<Applicant> listOfApplicants = new ArrayList<>();

    Applicant(String name, Integer age, String gender, String phoneNumber, String email, String location, String qualification){
        this.setName(name);
        this.setAge(age);
        this.setGender(gender);
        this.setPhoneNumber(phoneNumber);
        this.setEmail(email);
        this.setLocation(location);
        this.setQualification(qualification);
    }

    public Applicant(){

    }

    public void ListOfApplicant() throws IOException {
        ApplicantList applicantList = () -> {
            XSSFWorkbook workbook= new XSSFWorkbook(new FileInputStream("/Users/mac/IdeaProjects/convenienceStoreWeek4/src/main/resources/ConvenienceStoreStaff.xlsx"));
            XSSFSheet applicantSheet = workbook.getSheetAt(3);
            int lastRow = applicantSheet.getLastRowNum();
            int lastRowWithContent = 0;
            for(int i = 0; i <= lastRow; i++){
                XSSFRow row = applicantSheet.getRow(i);
                if(row != null){
                    XSSFCell cell = row.getCell(0);
                    if(cell != null && cell.getCellType() != CellType.BLANK){
                        lastRowWithContent = i;
                    }
                }
            }
            for(int j = 1; j <= lastRowWithContent; j++){
                XSSFRow applicantRow = applicantSheet.getRow(j);
                getListOfApplicants().add(new Applicant(applicantRow.getCell(0).getStringCellValue(),
                        (int) applicantRow.getCell(1).getNumericCellValue(),
                        applicantRow.getCell(2).getStringCellValue(),
                        applicantRow.getCell(3).getStringCellValue(),
                        applicantRow.getCell(4).getStringCellValue(),
                        applicantRow.getCell(5).getStringCellValue(),
                        applicantRow.getCell(6).getStringCellValue()));
            }
        };
        applicantList.declareApplicant();
    }
}
