package Model;
import java.util.ArrayList;

import Exceptions.NoParentException;
import Exceptions.NotToBeCoupledException;
import Exceptions.NotToBeFriendsException;
import Exceptions.RepeatException;
import Exceptions.TooYoungException;
import Interfaces.ConnectionManager;
import Interfaces.ProfileManager;

/*
 * Author: Zhongnian Lu s3512993
 * 
 * This class contains a list of all connections.
 * 
 * This class contains all methods to add different types of new connection.
 * 
 * This class helps search and display functions in Menu class. 
 * 
 */

public class ConnectionManagerImpl implements ConnectionManager{
	

	//Access list of connections
	private ArrayList<Connection> c_list = new ArrayList<Connection>();
	
	
	//Provide an instance of Profile manager to access profile list
	ProfileManager Pmanager;	
	
	
	// direct add a new connection into the list, only for import database
    public void addConnection(Connection target){
    	c_list.add(target);
	}
	
	
	//Method to create a friend connection and store it in the list
	public void addFriendConnection(int ID_1,int ID_2) throws NotToBeFriendsException, TooYoungException, RepeatException{
				
		Profile person1 = Pmanager.searchProfile(ID_1);
		Profile person2 = Pmanager.searchProfile(ID_2);
		
		//create a new connection with selected profiles
		Friend_Connection addConnect = new Friend_Connection(person1,person2);
		
		//check whether the friend connection is valid by calling age check method 
		addConnect.check(c_list);
		addConnect.repeat_check(c_list);
		c_list.add(addConnect);
	}
		
	
	// add new parent connection by passing three IDs including parents and child
    public void addParentConnection(int ID_1,int ID_2,int ID_child) throws NoParentException, RepeatException{
	    			
		Profile person1 = Pmanager.searchProfile(ID_1);
		Profile person2 = Pmanager.searchProfile(ID_2);
		Profile child = Pmanager.searchProfile(ID_child);
		
		
		//check whether the parent connection is valid by calling parent check method passing IDs of parents 
		Parent_Connection addConnect = new Parent_Connection(person1,person2,child);
		
		addConnect.check(c_list);
		addConnect.repeat_check(c_list);
		c_list.add(addConnect);
	
    }
		
  
	// add new couple connection by passing two IDs
    public void addCoupleConnection(int ID_1,int ID_2) throws RepeatException, NotToBeCoupledException{
		
		Profile person1 = Pmanager.searchProfile(ID_1);
		Profile person2 = Pmanager.searchProfile(ID_2);
			
		//check whether the parent connection is valid by calling parent check method passing IDs of parents 
		Couple_Connection addConnect = new Couple_Connection(person1,person2);
		
		addConnect.check(c_list);
		addConnect.repeat_check(c_list);
		
		c_list.add(addConnect);
		
	}

    
    
    //access connection list
    public ArrayList<Connection> get_Clist(){
	
    	return c_list;
	}
    
    
    
    //NEED TEST!!!!!!!!!!!!!!!!
    public void removeConnections(Profile targetProfile) throws NoParentException {
    	
    	// check whether the person has any independent
    	for(Connection connection : search_clist(targetProfile)) {
    		
    		if(connection instanceof Parent_Connection && 
    				(connection.getPerson1() == targetProfile) || (connection.getPerson2() == targetProfile)) {
    			
    			throw new NoParentException("Can't delete a person with at least one dependent");
    			
    		}
    		
    	}
    	
    	//remove all connection 
    	for(Connection connection : c_list) {
    		
    		if(connection.hasProfile(targetProfile)) {
    			
    			c_list.remove(connection);
    		}
    		
    	}
    	
    }
	
    
    
    //Method to create a array list of profiles connected to a target person
    public ArrayList<Profile> search(Profile target) {  	

		ArrayList<Profile> contain = new ArrayList<Profile>();
		
		for(int i=0;i<c_list.size();i++) {
			
		    Connection tem = c_list.get(i);	
			
		    //Use check tool created in connection to make sure target person is in the connection.
		    if(tem.hasProfile(target) == true) {
	
		        if((profile_repeat(tem.getPerson1(), contain) == false)
				    && tem.getPerson1().getID() != target.getID()) {
			
 		 	      contain.add(tem.getPerson1());	
		
		        }
		
		        if((profile_repeat(tem.getPerson2(), contain) == false) 
				    && tem.getPerson2().getID() != target.getID()) {
			
			       contain.add(tem.getPerson2());	
		
		        }
		
		        //rule of check parent connection is different.
		        if(tem instanceof Parent_Connection) {
		    	
				    if((profile_repeat(tem.getChild(), contain) == false)
					    && tem.getChild().getID() != target.getID()) {
				
				     contain.add(c_list.get(i).getChild());
			        }
		        }
		   }
	   }
		
		return contain;
    }
    
    
    
    //Method to create a connection list of profiles connected to a target person
    public ArrayList<Connection> search_clist(Profile target) {  	

		ArrayList<Connection> contain = new ArrayList<Connection>();
				
		for(Connection temConnection : c_list) {
					
		    if(temConnection.hasProfile(target) == true) {
			 
		 	   contain.add(temConnection);
		    }
	     
	    }
		
		return contain;
		
    }

    
    
    // Checking tool to help search method above: identify if a profile already included in the result of search
    public boolean profile_repeat(Profile target, 
    		                      ArrayList<Profile> check_plist) {
    
    	boolean repeat=false;
    	
    	for(int i = 0;i < check_plist.size();i++) {
    		
    		if(check_plist.get(i).getID() == target.getID()) {
    			
    			repeat = true;
    		}
    	
    	}
    	
    	return repeat;
    }


    
    //Method only for importing data from database.
	public void importList(ArrayList<Connection> readConnections) {
	
		int len = readConnections.size();
		
		for (int i = 0; i < len; i++) {
			
      		 this.addConnection(readConnections.get(i));
		
		}
		
	}


	@Override
	public void setPmanager(ProfileManager profiles) {

		Pmanager = profiles;
	}

}
    
	
    
    
