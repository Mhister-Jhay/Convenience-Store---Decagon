package org.decagon.staff.manager;
import org.decagon.staff.applicant.Applicant;
import org.decagon.staff.cashier.Cashier;

public class ManagerService implements ManagerActions{

    Manager manager;
    Cashier cashier;
    Applicant applicant;
    public ManagerService(Cashier cashier, Manager manager, Applicant applicant){
        this.cashier = cashier;
        this.manager = manager;
        this.applicant = applicant;

    }

    @Override
    public void fireCashier() {
        int cashier1lateness = 0;
        int cashier2lateness = 0;
        int index;
        for(Cashier app: cashier.getCashierCheckIn()){
            if(app.getCashier1CheckIn()>8){
                cashier1lateness++;
            }else if(app.getCashier2CheckIn()>8){
                cashier2lateness++;
            }
        }
        if(cashier1lateness>cashier2lateness){
            index = 0;
        }else{
            index = 1;
        }
            System.out.println("*******************************************************");
            System.out.println("The Cashier Below Has Been FIRED By The Manager, Mr. "+manager.getTheManager().get(0).getName());
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.printf("%-20s%-8s%-10s%-15s%-30s","NAME","AGE","GENDER","PHONE NUMBER","EMAIL");
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.printf("%-20s%-8d%-10s%-15s%-30s",cashier.getListOfCashiers().get(index).getName(),
                                                        cashier.getListOfCashiers().get(index).getAge(),
                                                        cashier.getListOfCashiers().get(index).getGender(),
                                                        cashier.getListOfCashiers().get(index).getPhoneNumber(),
                                                        cashier.getListOfCashiers().get(index).getEmail());
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------");

            cashier.getListOfCashiers().remove(index);
            System.out.println("Number of Cashier Left In The Store: "+cashier.getListOfCashiers().size());
            System.out.println("-------------------------------------------------------------------------------------");

    }

    @Override
    public void hireCashier() {
        for(Applicant app: applicant.getListOfApplicants()){
            if((app.getAge()>=19 && app.getAge()<= 24)
                    && app.getLocation().equalsIgnoreCase("Island")
                    && app.getQualification().equalsIgnoreCase("OND")
                    && app.getGender().equalsIgnoreCase("Female")){
                System.out.println();
                System.out.println("-----------------------------------------------------------------------------------------");
                System.out.println();
                System.out.println("--------------------------------------------------------------");
                System.out.println("  Most Preferable Candidate for the Position is a Female");
                System.out.println("--------------------------------------------------------------");
                System.out.println("On Passing the Interview, The Applicant Below Has Been Hired as a Cashier");
                System.out.println("-----------------------------------------------------------------------------------------");
                System.out.printf("%10s","---------------------------------HIRED APPLICANTS--------------------------------------");
                System.out.println();
                System.out.printf("%-20s%-8s%-10s%-15s%-30s%-15s%-10s","NAME","AGE","GENDER","PHONE NUMBER","EMAIL","QUALIFICATION","LOCATION");
                System.out.println();
                System.out.print("-----------------------------------------------------------------------------------------");
                System.out.println();
                System.out.printf("%-20s%-8d%-10s%-15s%-30s%-15s%-10s",app.getName(),app.getAge(),app.getGender(),app.getPhoneNumber(),app.getEmail(),
                        app.getLocation(),app.getQualification());
                System.out.println();
                System.out.println("-----------------------------------------------------------------------------------------");
                System.out.println();
                cashier.getListOfCashiers().add(new Cashier(app.getName(),app.getAge(),app.getGender(),app.getPhoneNumber(),app.getEmail()));
                System.out.println("Number of Cashier In The Store: "+cashier.getListOfCashiers().size());
                System.out.println("-------------------------------------------------------------------------------------");

            }
        }
    }

}
