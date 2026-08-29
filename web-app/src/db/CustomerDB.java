package db;

import entity.CustomerEntity;

import java.sql.*;

public class CustomerDB {

    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/ehotel";
    private final String USER = "root";
    private final String PASS = "115146";

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public CustomerDB() {

        try{
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void CreatCustomer(String name,String idType,String id,String address) {

        String sql = "INSERT INTO customer (customer_id,name, id_type, id_number, address) "
                + "VALUES (?,?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // 设置参数
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, idType);
            pstmt.setString(4, id);
            pstmt.setString(5, address);

            int rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public CustomerEntity ReadCustomer(String name, String idType, String id, String address){

        statement = null;
        resultSet = null;
        CustomerEntity customer = null;
        String sql = "select * from customer where name = '"+name+"'" +
                " and id_number = '"+id+"'" + " and address = '"+address+"'"+ " and id_type='"+idType+"'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                customer = new CustomerEntity(resultSet.getString(1),
                        resultSet.getString(2),resultSet.getString("address"));
            }
            return customer;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public CustomerEntity ReadCustomer(String idType, String id){

        statement = null;
        resultSet = null;
        CustomerEntity customer = null;
        String sql = "select * from customer where id_number = '"+id+"'" + " and id_type='"+idType+"'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                customer = new CustomerEntity(resultSet.getString(1),
                        resultSet.getString(2),resultSet.getString("address"));
            }
            return customer;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public CustomerEntity ReadCustomer(String id){

        statement = null;
        resultSet = null;
        CustomerEntity customer = null;
        String sql = "select * from customer where id_number = '"+id+"'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                customer = new CustomerEntity(resultSet.getString(1),
                        resultSet.getString(2),resultSet.getString("address"));
            }
            return customer;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
