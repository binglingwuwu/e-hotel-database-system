package db;

import entity.Log;

import java.sql.*;

public class EmployeeDB {

    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/ehotel";
    private final String USER = "root";
    private final String PASS = "115146";

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public EmployeeDB() {

        try{
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public boolean checkEmployee(String employeeID,String hotelID){

        statement = null;
        resultSet = null;

        try {
            statement = connection.createStatement();
            String sql = "select * from employee where employee_id = '" + employeeID + "'" + " and hotel_id = '" + hotelID + "'";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return true;
            }else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            try{
                statement.close();
                resultSet.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }

}
