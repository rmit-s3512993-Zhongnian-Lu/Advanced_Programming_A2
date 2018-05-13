package Model;

import java.util.ArrayList;

import Exceptions.NoParentException;
import Exceptions.NoSuchAgeException;
import Exceptions.ProfileNotFoundException;
import Exceptions.RepeatException;
import Interfaces.ConnectionManager;
import Interfaces.ProfileManager;


public class test {

	public static void main(String[] args) {

		ProfileManager profiles = new ProfileManagerImpl();  
	
		
//		Profile p1 = new Profile("A", 19);
//		
//		Profile p2 = new Profile("B", 17);
//
//		Profile p3 = new Profile("C", 15);
//
//		Profile p4 = new Profile("D", 13);
//		
//		Profile p5 = new Profile("E",3);
//		
//		Profile p6 = new Profile("F", 1);
//		
//		Profile p7 = new Profile("G", 20);
//		
//		Profile p8 = new Profile("H", 30);
//		
//		Profile p9 = new Profile("I", 40);
//		
//		Profile p10 = new Profile("J", 240);
//		
//		try {
//			profiles.addProfile(p1);
//			profiles.addProfile(p2);
//			profiles.addProfile(p3);
//			profiles.addProfile(p4);
//			profiles.addProfile(p5);
//			profiles.addProfile(p6);
//			profiles.addProfile(p7);
//			profiles.addProfile(p8);
//			profiles.addProfile(p9);
//			profiles.addProfile(p10);
//		} catch (NoSuchAgeException e1) {
//			System.out.println(e1.getMessage());
//			}
//	
//
//		
//		ConnectionManager conns = new ConnectionManagerImpl(); 
//		conns.setPmanager(profiles);
////		
//		try {
//			conns.addConnection("A","B", "friends");
//			System.out.println("A, B, success!");
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
////		
////		try {
////			conns.addConnection("B","D","friends");
////			System.out.println("B, D, success!! friend");
////		} catch (Exception e) {
////			System.out.println(e.getMessage());
////		}
//////		
//
//////		
////		try {
////			conns.addConnection("D","C","friends");
////			System.out.println("CD, success!! friend");
////		} catch (Exception e) {
////			System.out.println(e.getMessage());
////		}
////		
////		
//		//test couple connection
//		try {
//			conns.addConnection("A","I","couple");
//			System.out.println("A,I success!! couple");
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//////		
////		
////		try {
////			conns.addConnection("G","D", "couple");
////			System.out.println("G, D success!! couple");
////		} catch (Exception e) {
////			System.out.println(e.getMessage());
////		}
//////		
////		
////		try {
////			conns.addConnection(7,8, 2);
////			System.out.println("7,8 success!! couple");
////		} catch (Exception e) {
////			System.out.println(e.getMessage());
////		}
//////		
//////		
////		try {
////			conns.addParentConnection(1,9,4 );
////			System.out.println("1, 9, 4 success!! parent");
////		} catch (Exception e) {
////			System.out.println(e.getClass());
////		}
//////		
////		try {
////			conns.addParentConnection(7, 8, 4);
////			System.out.println("7, 8, 4 success!! parent");
////		} catch (Exception e) {
////			System.out.println(e.getMessage());
////		}
////		
////		
////		try {
////			conns.addConnection(3,4, 1);
////		    System.out.println("3, 4,  success!! friend");
////		} catch (Exception e) {
////		    System.out.println(e.getMessage());
////		}
////		
////		
//		
//		//delete
//		try {
//			conns.removeConnections("C");
//			System.out.println("delete 1 success!");
//		} catch (NoParentException | ProfileNotFoundException e) {
//			System.out.println("test");
//		    System.out.println(e.getMessage());
		//}
//		
		
//		//test add colleague connection
//		try {
//			conns.addConnection(1,3,3);
//			System.out.println("1, 2, success!! colleague");
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
		
//		//test add classmates connection
//		try {
//			conns.addConnection(1,6,4);
//			System.out.println("1, 6, success!! classmates");
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
		
		
		//test getRelations
//		try {
//			ArrayList<Connection> relations = conns.getRelations(3);
//			for(Connection connection : relations) {
//				System.out.println(connection.getPerson1().getName() + 
//						connection.getPerson2().getName() + 
//						connection.getChild().getName());
//			}
//		}catch(Exception e) {
//			
//			System.out.println(e.getMessage());
//			
//		}
		
		ConnectionManager conns = new ConnectionManagerImpl(); 
		conns.setPmanager(profiles);
//	 test reading people
//		FileManager fmanager = new FileManager();
//		
//		try {
//			profiles.importList(fmanager.readPeople());
//		} catch (NoSuchAgeException e) {
//			System.out.println(e.getMessage());
//		}
//		
//		for(Profile profile : profiles.get_Plist()) {
//			System.out.println(profile.getName());
//		}
//		
//		//test reading connections
//		fmanager.readConnection(profiles, conns);
//		
//		
//		for(Connection connection : conns.get_Clist()) {
//			System.out.println(connection.getPerson1().getName()+" "+connection.getPerson2().getName()+connection.getClass());
//		}
		
	
		// test database reader people
		DatabaseReader dreader = new DatabaseReader();

		try {
			profiles.importList(dreader.readPeople());
		} catch (NoSuchAgeException e) {
			System.out.println(e.getMessage());
		}
		
		for(Profile profile : profiles.get_Plist()) {
			System.out.println(profile.getName());
		}

//		
		dreader.readConnection(profiles, conns);
		for(Connection connection : conns.get_Clist()) {
			System.out.println(connection.getPerson1().getName()+" "+connection.getPerson2().getName()+connection.getClass());
	}
	}
}