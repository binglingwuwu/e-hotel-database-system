package db;

import java.sql.*;

public class PaymentDB {

    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/ehotel";
    private final String USER = "root";
    private final String PASS = "115146";

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public PaymentDB() {

        try{
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void CreatePaymentDB(String amount,String method, String rentingId) {

        String sql = "INSERT INTO payment (amount, method, renting_id) "
                + "VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // 设置参数
            pstmt.setString(1, amount);
            pstmt.setString(2, method);
            pstmt.setString(3, rentingId);

            int rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
