//mySQLAccess

/////////////
//File to setup connection to mySQL Database
/////////////

/////////////
//DB TERM PROJ
/////////////
//CREATED BY WEIWEI SU
//UNIV OF SOUTH FLORIDA @ 2018

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class mySQLAccess {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    //Database Name & Username for login
    private String dbName = "termprojdbtest";
    private String dbUser = "root";
    private String dbPwd = "sert";
    
    //Database Operation for chkcat()
    public void readAllInventory() throws Exception {
        try {
        	//Load MySQL Driver
        	Class.forName("com.mysql.jdbc.Driver").newInstance();
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/"+dbName, dbUser, dbPwd);
            
            //Test Op
            //WRITE QUERY HERE:
            String query = "SELECT * FROM TESTINV";
            
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            //Execute Query
            resultSet = statement.executeQuery(query);
            
            //PRINT OUTPUT:
            while(resultSet.next()) {
            	int stknum = resultSet.getInt("stocknum");
            	String title = resultSet.getString("title");
            	int qty = resultSet.getInt("qty");
            	
            	System.out.format("%s, %s, %s\n", stknum, title, qty);
            }
            statement.close();            
        	
        } catch (Exception e) {
            throw e;
        }
    }
}