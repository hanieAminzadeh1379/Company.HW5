package com.company;

import java.sql.*;

public class CompanyDatabase {
    private Connection connection;


    public CompanyDatabase() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "13811383");
    }

    public void addEmployee(Employee employee) throws SQLException {
        if (connection != null) {
            Statement statement = connection.createStatement();
            if (checkUnit(employee, statement)) {
                statement.executeUpdate(String.format("INSERT INTO company.employee(id,first_name,last_name,personnel_number,birth_date,work_unit) VALUES (Null,'%s','%s','%s','%s','%s')", employee.getFirstName(), employee.getLastName(), employee.getPersonnelId(), employee.getBirthDate(), employee.getWorkUnit()));
            } else {
                System.out.println("the work unit is not valid");
            }
        }
    }

    public void addWorkUnit(WorkUnit workUnit) throws SQLException {
        if (connection != null) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("INSERT INTO company.work_unit(Id,Name,phoneNumber) VALUES (Null,'%s','%s')", workUnit.getName(), workUnit.getPhoneNumber()));
        }
    }

    public void editEmployeeInformation(int id, String newName, int firstOrLast) throws SQLException {
        Statement statement = connection.createStatement();
        if (firstOrLast == 1) {
            statement.executeUpdate(String.format("UPDATE company.employee SET first_name = '%s' WHERE (id = '%s')", newName, id));
        } else if (firstOrLast == 2) {
            statement.executeUpdate(String.format("UPDATE company.employee SET last_name = '%s' WHERE (id = '%s')", newName, id));
        }
    }

    public void editUnitName(String newName, int id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(String.format("UPDATE company.work_unit SET name = '%s' WHERE (id = '%s')", newName, id));
    }

    public void showWorkUnits() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM company.workunit");
        while (resultSet.next()) {
            System.out.print(resultSet.getString("ID\n"+"Name"));

        }
    }

    public void showWorkUnitEmployee(int workUnit) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM company.employee where work_unit='%s'",
                workUnit));
        while (resultSet.next()) {
            System.out.println(resultSet.getString("first_name\n"+ "last_name"));

        }
    }

    public boolean checkUnit(Employee employee, Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM company.workUnit");
        boolean result = false;
        while (resultSet.next()) {
            if (resultSet.getInt("ID") == employee.getWorkUnit()) {
                result = true;
                break;
            }
        }
        return result;
    }
}