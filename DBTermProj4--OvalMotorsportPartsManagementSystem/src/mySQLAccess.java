//mySQLAccess

/////////////
//File to setup connection to mySQL Database
/////////////

/////////////
//DB TERM PROJ
/////////////
//CREATED BY WEIWEI SU
//QUERY WRITTEN BY BO WU
//UNIV OF SOUTH FLORIDA @ 2018

//Code below is modified from Vogella's JDBC MySQL Connection Tutorial
//http://www.vogella.com/tutorials/MySQLJava/article.html
//Date Accessed: 04/01/2018

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
    private String dbName = "ovalmotorsport";
    private String dbUser = "root";
    private String dbPwd = "sert";
    
    //Database Operation for chkcat()
    //BrowseAllInventory
    public void browseAllInventory() throws Exception {
        try {
        	//Load MySQL Driver
        	Class.forName("com.mysql.jdbc.Driver").newInstance();
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/"+dbName, dbUser, dbPwd);
            
            //Test Op
            //WRITE QUERY HERE:
            String query = "SELECT * FROM parts";
            
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            //Execute Query
            resultSet = statement.executeQuery(query);
            
            //PRINT OUTPUT:
            while(resultSet.next()) {
            	int stock_num = resultSet.getInt("stock_num");
            	int price = resultSet.getInt("price");
            	String title = resultSet.getString("title");
            	String brand = resultSet.getString("brand");
            	String model = resultSet.getString("model");
            	String year = resultSet.getString("Years");
            	String mp_num = resultSet.getString("MP_num");
            	String category = resultSet.getString("category");
            	int qty = resultSet.getInt("qty_in_stock");
            	
            	System.out.format("%s, %s, %s, %s, %s, %s, %s, %s, %s\n",
            			stock_num, price, title, brand, model, year, mp_num, category, qty);
            }
            statement.close();            
        	
        } catch (Exception e) {
            throw e;
        }
    }
    //BrowseFilteredInventory
    public void browseFilteredInventory(String yr, String br, String mod, String cat) throws Exception {
    	try {
        	//Load MySQL Driver
        	Class.forName("com.mysql.jdbc.Driver").newInstance();
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/"+dbName, dbUser, dbPwd);
            
            //WRITE QUERY HERE:
            String query = "SELECT * FROM parts WHERE Years='"+yr+"'"
            		+ " AND brand='"+ br +"' AND model='"+ mod +"' AND category='"+ cat +"'";
            
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            //Execute Query
            resultSet = statement.executeQuery(query);
            
            //PRINT OUTPUT:
            if(!resultSet.next()) {
            	System.out.println("Nothing came up, did you mistype any search criteria?");
            }else{
                while(resultSet.next()) {
                	int stock_num = resultSet.getInt("stock_num");
                	int price = resultSet.getInt("price");
                	String title = resultSet.getString("title");
                	String brand = resultSet.getString("brand");
                	String model = resultSet.getString("model");
                	String year = resultSet.getString("Years");
                	String mp_num = resultSet.getString("MP_num");
                	String category = resultSet.getString("category");
                	int qty = resultSet.getInt("qty_in_stock");
                	
                	System.out.format("%s, %s, %s, %s, %s, %s, %s, %s, %s\n",
                			stock_num, price, title, brand, model, year, mp_num, category, qty);                	
                }
            }
            statement.close();            
        	
        } catch (Exception e) {
            throw e;
        }
    }
    
    //Database Operation for order
    //
    
    //Database Operation for cart
    
    //Database Operation for acinfo
}