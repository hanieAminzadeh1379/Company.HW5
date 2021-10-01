package com.company;

import com.company.CompanyDatabase;
import com.company.Employee;
import com.company.WorkUnit;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        CompanyDatabase companyDatabase = new CompanyDatabase();
        Scanner scanner = new Scanner(System.in);
        showMenu();
        int choose = scanner.nextInt();
        boolean runProgram = true;

        while (runProgram) {
            switch (choose) {
                case 1: {
                    Employee employee = new Employee();
                    scanner.nextLine();
                    employee.setFirstName(scanner.nextLine());
                    System.out.println("Enter your FirstName:");

                    employee.setLastName(scanner.nextLine());
                    System.out.println("Enter your LastName:");

                    employee.setBirthDate(scanner.nextLine());
                    System.out.println("Enter your BirthDate:");

                    employee.setPersonnelId(scanner.nextInt());
                    System.out.println("Enter your PersonnelNumber:");

                    employee.setWorkUnit(scanner.nextInt());
                    System.out.println("Enter your Workunit:");
                    companyDatabase.addEmployee(employee);
                    showMenu();

                    choose = scanner.nextInt();
                    break;
                }
                case 2: {
                    WorkUnit workUnit = new WorkUnit();
                    scanner.nextLine();
                    workUnit.setName(scanner.nextLine());
                    System.out.println("Enter your workunitName:");

                    workUnit.setPhoneNumber(scanner.nextLine());
                    System.out.println("Enter your workunit phoneNumber");

                    companyDatabase.addWorkUnit(workUnit);
                    showMenu();
                    choose = scanner.nextInt();
                    break;
                }
                case 3: {
                    System.out.println("Enter employee id:");
                    int employeeId = scanner.nextInt();

                    System.out.println("Witch one do you wnt to edit:\n+" +
                            "1)FirstName\n"+
                            "2)LastName");
                    int firstOrLast = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter new Name");
                    companyDatabase.editEmployeeInformation(firstOrLast, scanner.nextLine(), employeeId);
                    showMenu();
                    choose = scanner.nextInt();
                    break;
                }
                case 4: {
                    System.out.println("Enter unit Id");
                    int workUnitId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter new Name");
                    String newName = scanner.nextLine();

                    companyDatabase.editUnitName(newName, workUnitId);
                    showMenu();
                    choose = scanner.nextInt();
                    break;
                }
                case 5: {
                    companyDatabase.showWorkUnits();
                    showMenu();
                    choose = scanner.nextInt();
                    break;
                }
                case 6:
                    System.out.println("Enter work unit id");
                    companyDatabase.showWorkUnitEmployee(scanner.nextInt());

                    showMenu();
                    choose = scanner.nextInt();
                    break;
                case 7: {
                    runProgram = false;
                    break;
                }
            }
        }
    }

    public static void showMenu() {
        System.out.println("1)add employee\n" +
                "2)add workunit\n" +
                "3)edit employee information\n" +
                "4)edit work unit name\n"+
                "5)show all work unit\n"+
                "6)show work unit's employee\n"+
                "7)exit");
    }
}