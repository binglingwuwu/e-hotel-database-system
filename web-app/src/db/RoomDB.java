package db;

import entity.RoomEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDB extends baseDB{

    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/ehotel";
    private final String USER = "root";
    private final String PASS = "115146";

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public RoomDB() {

        try{
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public String findId(String RoomNumber, String View) {

        statement = null;
        resultSet = null;

        try {
            String sql = "SELECT * from room where ( room_number = ? OR ? = '') AND (view = ? OR ? = '')";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, RoomNumber);
            pstmt.setString(2, RoomNumber);
            pstmt.setString(3, View);
            pstmt.setString(4, View);
            resultSet = pstmt.executeQuery();
            if(resultSet.next()){
                return resultSet.getString(1);
            }else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public RoomEntity ReadRoomDB(String roomId){

        statement = null;
        resultSet = null;

        try{
            String sql = "select * from room where room.room_id='"+roomId+"'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                RoomEntity roomEntity = new RoomEntity(resultSet.getString(1),
                        resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5),
                        resultSet.getString(7), resultSet.getString(9));
                return roomEntity;
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

    public List<RoomEntity> ReadRoomDB(String H_id, String RoomCapacity, String MaxPrice){

        statement = null;
        resultSet = null;
        List<RoomEntity> roomEntityList = new ArrayList<RoomEntity>();

        try{
            String sql = "select * from room where hotel_id='"+H_id+"'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                RoomEntity roomEntity = new RoomEntity(resultSet.getString(1),
                        resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5),
                        resultSet.getString(7), resultSet.getString(9));
                if ((Integer.parseInt(roomEntity.getCapacity()) <= Integer.parseInt(RoomCapacity)) &&
                        (Double.parseDouble(roomEntity.getPrice()) <= Double.parseDouble(MaxPrice))){
                    roomEntityList.add(roomEntity);
                }
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

        return roomEntityList;

    }

    @Override
    public void closeAll() {

        try {
            statement.close();
            resultSet.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
