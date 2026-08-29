package db;

import entity.CustomerEntity;

import java.sql.*;

public class RentingDB {

    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/ehotel";
    private final String USER = "root";
    private final String PASS = "115146";

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public RentingDB() {

        try{
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void CreateRenting(String startDate, String endDate,
                              String bookingId, String customerId,
                              String roomId, String employee_id) {

        String sql = "INSERT INTO renting (start_date, end_date, customer_id, room_id, booking_id employee_id) "
                + "VALUES (?, ?, ?, ?, ?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // 设置参数
            pstmt.setString(1, startDate);
            pstmt.setString(2, endDate);
            pstmt.setString(3, customerId);
            pstmt.setString(4, roomId);
            pstmt.setString(5, bookingId);
            pstmt.setString(6, employee_id);

            int rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String GetRentingId(String startDate, String endDate,
                               String status, String customerId,
                               String roomId, String employee_id) {

        statement = null;
        resultSet = null;

        String sql = "select * from renting where start_date = '"+startDate+"'" + " and end_date = '"+endDate+"'"
                + " and customer_id = '"+customerId+"'" + " and room_id = '"+roomId+"'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                return resultSet.getString(1);
            }

        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return "";
    }

}
