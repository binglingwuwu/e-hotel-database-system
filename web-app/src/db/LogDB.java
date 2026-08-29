package db;

import entity.Log;
import java.sql.*;

public class LogDB {

    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/ehotel";
    private final String USER = "root";
    private final String PASS = "115146";
    private Connection connection;

    private String Userid;
    private String Password;
    private String role;
    private Statement statement;
    private ResultSet resultSet;
    private Log logs;

    public LogDB() {

        try{
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public Log getLogs(String Userid) {

        this.Userid = Userid;

        statement = null;
        resultSet = null;

        try {
            statement = connection.createStatement();
            String sql = "select * from log where userid = '" + Userid + "'";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                this.Password = resultSet.getString(2);
                this.role = resultSet.getString(3);
                logs = new Log(this.Userid,this.Password,this.role);
                return logs;
            }else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            try{
                statement.close();
                resultSet.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }

    public boolean insertLog(String Userid, String Password, String role) {

        this.Userid = Userid;
        this.Password = Password;
        this.role = role;
        PreparedStatement pstmt = null;

        statement = null;
        resultSet = null;

        try{
            statement = connection.createStatement();
            String sql = "INSERT INTO log (Userid, password, state) VALUES (?, ?, ?)";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, Userid);
            pstmt.setString(2, Password);
            pstmt.setString(3, role);

            // 执行更新操作，返回受影响的行数
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            // 释放资源
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void closeAll() {

        try {
            statement.close();
            resultSet.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
