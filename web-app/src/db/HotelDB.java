package db;

import entity.HotelEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDB {

    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/ehotel";
    private final String USER = "root";
    private final String PASS = "115146";

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public HotelDB() {

        try{
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public List<HotelEntity> ReadHotelDB(String hotelChain, String star, String region){

        statement = null;
        resultSet = null;
        List<HotelEntity> list = new ArrayList<HotelEntity>();
        String hotelChain_id = "";

        if(!hotelChain.isEmpty()){
            ChainDB chainDB = new ChainDB();
            hotelChain_id = chainDB.ReadChain(hotelChain);
        }

        try {
            String sql = "SELECT * from hotel where (Chain_id = ? OR ? = '') AND (category = ? OR ? = '')";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, hotelChain_id);
            pstmt.setString(2, hotelChain_id);
            pstmt.setString(3, star);
            pstmt.setString(4, star);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()){
                HotelEntity hotelEntity = new HotelEntity(resultSet.getString(1),
                        resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5),
                        resultSet.getString(6),resultSet.getString(7));
                hotelEntity.setChain_name(hotelChain);
                list.add(hotelEntity);
            }
            if (!region.isEmpty()){
                list.removeIf(e -> !e.getAddress().contains(region));
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

}
