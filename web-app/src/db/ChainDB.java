package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ChainDB {

    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/ehotel";
    private final String USER = "root";
    private final String PASS = "115146";

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public ChainDB() {

        try{
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public String ReadChain(String chain){

        statement = null;
        resultSet = null;
        String sql = "select * from hotel_chain where name = '"+chain+"'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                chain = resultSet.getString("chain_id");
            }
            return chain;
        }catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

}
