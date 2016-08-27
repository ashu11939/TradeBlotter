package team6.onlinetradeblotter.ejb;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import team6.onlinetradeblotter.jpa.Scratchpad;
import team6.onlinetradeblotter.jpa.User;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.*;
/**
 * Session Bean implementation class TestSessionBean
 */
@Stateful
//@TransactionManagement(TransactionManagementType.BEAN)
@Local(TestSessionBeanLocal.class)
@Remote(TestSessionBeanRemote.class)
public class TestSessionBean implements TestSessionBeanRemote, TestSessionBeanLocal {

    public TestSessionBean() {
        // TODO Auto-generated constructor stub
    }
    
    @PersistenceContext(unitName="OnlineTradeBlotterJPA-PU")
    EntityManager em;
    
    public List<User> getAllUsers(){
    	System.out.println("Here");
    	List <User> list = new ArrayList<>() ;
    	Query query = em.createQuery("Select u from User u",User.class);
    	list = query.getResultList();
    	
    	return list;
    }
    
    public boolean checkLogin(String userName , String password){
    	// Check if username is correct.
    	boolean userNameExists = checkUserName_in_Table(userName,"User");
    	if (!userNameExists){
    		return false;
    	}
    	else {
        	String queryString_checkKey = String.format("Select password from User u where username = %s", userName);
			Query query = em.createQuery(queryString_checkKey);
        	List<String> resultList = query.getResultList();
        	String actualPassword = resultList.get(0);
        	
    		return actualPassword.equals(password);
    	}
    }
    
	public void registerUser(String firstName, String lastName, String userName, String password){
		
		User newUser = new User(firstName, lastName, userName, password);
		em.persist(newUser);
		em.flush();
	}
	
	public boolean checkUserName_in_Table(String userName,String tableName){
    	String queryString_checkUserID = String.format("Select username from %s where username = %s", tableName,userName);
    	Query query = em.createQuery(queryString_checkUserID);
    	List<String> resultList = query.getResultList();
    	boolean userNameExists = !resultList.isEmpty();
    	
    	return userNameExists;
    	
	}
	public void updateNote(String userName, String note){
		// check if the userName exists in the table.
		
		boolean userNameExists = checkUserName_in_Table(userName,"Scratchpad");
		
    	if (userNameExists){
    		// Updation
    		Scratchpad userPad = em.find(Scratchpad.class, userName);
    		userPad.setNotes(note);
    		em.flush();    		
    	}
    	else{
    		// Insertion
    		Scratchpad newPad = new Scratchpad(userName,note);
    		em.persist(newPad);
    		em.flush();
    	}
	}
	
	public String getNote(String userName){
		boolean userNameExists = checkUserName_in_Table(userName,"Scratchpad");
		String returnString ;
		if (userNameExists){
			Scratchpad userPad = em.find(Scratchpad.class, userName);
			returnString = userPad.getNotes();
		}
		else{
			returnString = "NotFound";
		}
		return returnString ;
	}
    
}

