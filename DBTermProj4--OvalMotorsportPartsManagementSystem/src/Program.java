// Database Design Term Project
// ******
// OvalMotorsport Parts Management System Demo
// ******
// Program just for demo purpose, even the login credentials are not salted and hashed. (I will implement it if I have time)
// Main running Program for the System
// Created by Weiwei Su, Bo Wu, Donglin Yang

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Program {
	//program execution
	public void run() {
		
		//init Login Window
		CommandParser parser = new CommandParser();
		
		System.out.println("**************************************");
        System.out.println("OvalMotorsport Parts Management System");
        System.out.println("**************************************");
        System.out.println("This is for Demo & Course Purpose Only, does not contain any industrial specifications where a REAL System should have.");
        System.out.println("Please Login to Continue.  Enter 'help' for a list of commands.");
        System.out.println("To inquire an account, please contact the developer.");
        System.out.println();
        System.out.print("> "); 
		
        Command cmd = null;
        while ((cmd = parser.fetchCommand()) != null) {
            
            boolean result=false;
            
            if (cmd.getCommand().equals("help")) {
                result = doHelp();
            }
            //AUTHENTICATION SERVICE
            else if (cmd.getCommand().equals("login")) {
            	//Generates Login UI
            	Command loginCMD = null;
            	String userName = null; //should be salted and hashed in real world
            	String password = null; //should be salted and hashed in real world
            	System.out.print("UserName: ");
            	while((loginCMD = parser.fetchCommand()) != null) {
            		userName = loginCMD.getCommand();
            		break;
            	}
            	System.out.print("Password: ");
            	while((loginCMD = parser.fetchCommand()) != null) {
            		password = loginCMD.getCommand();
            		break;
            	}
            	//pass for authentication & Enter System if sucess.
        		if(userName != null && password != null) {
        			System.out.println("Processing...");
            		if(auth(userName, password)){
                		system(userName); //<--- vulnerable point.
                	}else {
                		System.out.println("Wrong UserName or PassWord.");
                	}
        		}
            }
            //Exit
            else if (cmd.getCommand().equals("exit")) {
    			System.out.println("Leaving the database, goodbye!");
    			break;
    		} else if (cmd.getCommand().equals("")) {
    		} else {
    			System.out.println("Invalid Command, try again!");
               	} 
                
    	    if (result) {
                    // ...
                }

                System.out.print("> "); 
        }

	}
	
	//System Help content (like ReadMe)
	private boolean doHelp() {
		//content
		
		//
		//
		//
		//
		
		return true;
	}
	
	//Authentication Service
	//In real world should use Crypt Lib to do this
	static boolean auth(String uname, String upwd) {
		//simple auth here for test
		if(uname.equals("admin") && upwd.equals("test")) {
			return true;
		}else {
			return false;
		}
	}
	
	//System UI & Functional Operation
	public void system(String user) {
		CommandParser parser = new CommandParser();
		Controller control = new Controller();
		//All system related info will be stored in info class.
		Info lv1 = new Info();
		
		//MAIN MENU 1st Level
		System.out.println("Login Sucess.");
		lv1.systemINFO();
		System.out.println("Welcome, " + user + ".");
		lv1.mainMenu();
		System.out.print("> "); 
		
		Command cmd = null;
		//command line
	    while ((cmd = parser.fetchCommand()) != null) {
	    	if(cmd.getCommand().equals("logout")) {
	    		System.out.println("Log Out sucess.");
	    		break;
	        }
	    	//access category
	    	else if(cmd.getCommand().equals("chkcat")) {
	    		control.chkcat();
	    		//indicate level after leave
	    		lv1.mainMenu(); 
	    	}
	    	//access order
	    	else if(cmd.getCommand().equals("order")) {
	    		System.out.println("Function not implemented yet.");
	    	}
	    	//access shopping cart
	    	else if(cmd.getCommand().equals("cart")) {
	    		System.out.println("Function not implemented yet.");
	    	}
	    	//access account information
	    	else if(cmd.getCommand().equals("acinfo")) {
	    		System.out.println("Function not implemented yet.");
	    	}
	    	//STD UI code within loop
	    	else if (cmd.getCommand().equals("")) {
	        	
    		} else {
    			System.out.println("Invalid Command, try again!");
               	}             

                System.out.print("> "); 
        
	    }
	}
	
	
	//I used the Command UI from Project 1, saved us some time.
    /**
     * @param args
     */
    public static void main(String[] args) {
        new Program().run();
    }
}