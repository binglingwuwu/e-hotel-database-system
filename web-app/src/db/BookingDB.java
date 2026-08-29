package db;

import entity.Booking;
import entity.CustomerEntity;
import entity.RoomEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDB {

    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/ehotel";
    private final String USER = "root";
    private final String PASS = "115146";

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public BookingDB() {

        try{
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public List<Booking> ReadBooking (String customerId, String roomId, String idType){

        List<Booking> bookings = new ArrayList<>();

        CustomerDB customerDB = new CustomerDB();
        RoomDB roomDB = new RoomDB();

        CustomerEntity customer = customerDB.ReadCustomer(idType, customerId);
        RoomEntity room = roomDB.ReadRoomDB(roomId);

        statement = null;
        resultSet = null;

        try{
            String sql = "select * from booking where room_id='"+roomId+"'" + " and customer_id='"+customerId+"'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Booking booking = new Booking(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3),resultSet.getString(4),
                        resultSet.getString(5),resultSet.getString(6),
                        resultSet.getString(7),room.getNumber(),room.getPrice(),room.getView(),
                        customer.getCustomerName(),customer.getCustomerAddress());
                bookings.add(booking);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                statement.close();
                resultSet.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }


        return bookings;
    }

    public Booking ReadBooking (String bookingID){

        List<Booking> bookings = new ArrayList<>();

        CustomerDB customerDB = new CustomerDB();
        RoomDB roomDB = new RoomDB();

        statement = null;
        resultSet = null;

        try{
            String sql = "select * from booking where booking_id='"+bookingID+"'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                String roomID = resultSet.getString(6);
                String customerID = resultSet.getString(5);
                RoomEntity room = roomDB.ReadRoomDB(roomID);
                CustomerEntity customer = customerDB.ReadCustomer(customerID);
                Booking booking = new Booking(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3),resultSet.getString(4), customerID,roomID,
                        resultSet.getString(7),room.getNumber(),room.getPrice(),room.getView(),
                        customer.getCustomerName(),customer.getCustomerAddress());
                return booking;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                statement.close();
                resultSet.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public void CreateBooking(String startDate, String endDate,
                              String status, String customerId,
                              String roomId, String employee_id) {

        String sql = "INSERT INTO booking (start_date, end_date, status, customer_id, room_id, employee_id) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // 设置参数
            pstmt.setString(1, startDate);
            pstmt.setString(2, endDate);
            pstmt.setString(3, status);
            pstmt.setString(4, customerId);
            pstmt.setString(5, roomId);
            pstmt.setString(6, employee_id);

            int rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean updateBooking(String bookingId,
                                 String startDate,
                                 String endDate,
                                 String status,
                                 String customerId,
                                 String roomId,
                                 String employeeId) {

        String sql = "UPDATE booking " +
                "SET start_date = ?, end_date = ?, status = ?, " +
                "    customer_id = ?, room_id = ?, employee_id = ? " +
                "WHERE booking_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, startDate);
            pstmt.setString(2, endDate);
            pstmt.setString(3, status);
            pstmt.setString(4, customerId);
            pstmt.setString(5, roomId);
            pstmt.setString(6, employeeId);
            pstmt.setString(7, bookingId);

            int rows = pstmt.executeUpdate();
            return rows > 0;  // 如果受影响行数大于 0，表示更新成功
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean isAvailable(String roomId, String startDate, String endDate){

        try {
            statement = null;
            resultSet = null;
            String sql = "SELECT * FROM booking " +
                    "WHERE room_id = ? " +
                    "  AND NOT ((end_date < ? AND end_date < ?) " +
                    "  OR (start_date > ? AND start_date > ?))";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, roomId);
            pstmt.setString(2, startDate);
            pstmt.setString(3, endDate);
            pstmt.setString(4, startDate);
            pstmt.setString(5, endDate);
            resultSet = pstmt.executeQuery();

            if(resultSet.next()){
                return Boolean.FALSE;
            }else {
                return Boolean.TRUE;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
