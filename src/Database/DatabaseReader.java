package Database;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Interfaces.ConnectionManager;
import Interfaces.ProfileManager;
import Model.Profile;

/*
 * Author: Jake Mott s3349405
 * 
 * This class contains predefined data but can contain methods
 * for reading in data from database. Methods return
 * ArrayLists to be called to feed the data to another class.
 * 
 */

public class DatabaseReader {
	
	/* Create new empty ArrayLists of profiles and connections */
	ArrayList<Profile> profiles = new ArrayList<Profile>();
	ArrayList<Connection> conns = new ArrayList<Connection>();	
	
	
	//Read personal information from table 'people' in database.
	public  ArrayList<Profile> readPeople() {
		
		 ArrayList<Profile> profiles = new ArrayList<Profile>();
		
		 Connection con = null;
	     Statement stmt = null;
	     ResultSet result = null;
	      
	      try {
	         Class.forName("org.hsqldb.jdbc.JDBCDriver");
	         con = DriverManager.getConnection(
	            "jdbc:hsqldb:hsql://localhost/testdb", "SA", "");
	         stmt = con.createStatement();
		     int counts = stmt.executeUpdate("CREATE TABLE people (name VARCHAR(50) NOT NULL, image VARCHAR(50),status VARCHAR(200), gender VARCHAR(3), age INT NOT NULL, state VARCHAR(10),PRIMARY KEY (name));");

	         result = stmt.executeQuery(
	            "SELECT * FROM people");
	         
	         
	         
	         while(result.next()){
	            
	            Profile addProfile = new Profile(result.getString("name"), result.getString("image"), result.getString("status"),
	            		result.getString("gender"), result.getInt("age"), result.getString("state"));
				profiles.add(addProfile);
	         }   
	      } catch (Exception e) {
	         e.printStackTrace(System.out);
	      }
	   
	      return profiles;
	}
	
	
	//Read connection information from 'relations' from database
	public void readConnection(ProfileManager pmanager, ConnectionManager cmanager) {
		
		Connection con = null;
	    Statement stmt = null;
	    ResultSet result = null;
		
		try {
		
		  Class.forName("org.hsqldb.jdbc.JDBCDriver");
	      con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/testdb", "SA", "");
	      stmt = con.createStatement();
          result = stmt.executeQuery( "SELECT * FROM relations");
	         while(result.next()){
	        	
	        	//create different type of connection based on the type information in the table
	        	try {
	    			if(result.getString("relation").equals("friends")) {
	    				cmanager.addConnection(result.getString("people1"),result.getString("people2"), "friends");
	    			}else if(result.getString("relation").equals("couple")){
	    				cmanager.addConnection(result.getString("people1"),result.getString("people2"), "couple");
	    			}else if(result.getString("relation").equals("colleague")){	
	    				cmanager.addConnection(result.getString("people1"),result.getString("people2"), "colleague");
	    			}else if(result.getString("relation").equals("classmates") ) {
	    				cmanager.addConnection(result.getString("people1"),result.getString("people2"), "classmates");
	    			}else if(result.getString("relation").equals("parent")) {
	    				cmanager.addParentConnection(result.getString("people1"),result.getString("people2"));
	    			}
				
	        	}catch(Exception e) {
	        	
	        		//Nothing trigger here, all invalid data will be just passed.
	        	}
	       }
		} catch (Exception e) {
		
		}
	}
}