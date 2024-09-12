package org.example;

import java.sql.*;

public class EmployeeMange
{
    private static final String URL = "jdbc:mysql://localhost:3306/vaibhav";
    private static final String username = "root";
    private static final String password = "vaibhav04";

    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    public static void main(String[] args) {
        try {
            //making database connectivity
            connection = DriverManager.getConnection(URL, username, password);

            //creating table
            createEmployeeTable();
           // insertDataInTable("Rahul",100,"rahul@gmail.com");
            fetchDataFromTable(1);
           // deleteDataFromTable(3);
            updateDataFromTable(2,"Ankur",44,"ankur@gmial.com");

        }catch (Exception ex){
            ex.printStackTrace();
        }
        }

    private static void updateDataFromTable(int id, String Updatedname, int Updateage, String Updateemail) throws SQLException{
        String sql = "UPDATE Employee SET name = '"+Updatedname+"'," +
                "age = "+Updateage+"," +
                "email = '"+Updateemail+"'" +
                "WHERE id = ?";

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        int row = preparedStatement.executeUpdate();

        if(row>0) System.out.println("updation successful");
        else System.out.println("updation not successful");
    }

    private static void deleteDataFromTable(int id) throws SQLException{
        String sql = "DELETE FROM Employee WHERE id = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        int row = preparedStatement.executeUpdate();
        if(row>0) System.out.println("data deleted successfully");
        else System.out.println("Failed deletion");
    }

    private static void fetchDataFromTable(int id) throws SQLException{
        String sql = "SELECT * FROM Employee WHERE id = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            System.out.println("Employee name :"+resultSet.getString("name"));
            System.out.println("Employee age :"+resultSet.getInt("age"));
            System.out.println("Employee email :"+resultSet.getString("email"));
        }
        System.out.println();
    }

    private static void insertDataInTable(String name, int age, String email) throws SQLException{
        String sql = "INSERT INTO Employee(name,age,email) VALUES(?,?,?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,name);
        preparedStatement.setInt(2,age);
        preparedStatement.setString(3,email);
        preparedStatement.executeUpdate();
        System.out.println("Data Inserted successfully");
    }

    private static void createEmployeeTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Employee (id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(100)," +
                "age INT," +
                "email VARCHAR(150)" +
                ")";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.execute();
        System.out.println("Table Created successfully...........");
    }
}
