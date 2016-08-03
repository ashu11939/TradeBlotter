package team6.onlinetradeblotter.ejb;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import team6.onlinetradeblotter.jpa.PricingInfo;
import team6.onlinetradeblotter.jpa.Scratchpad;
import team6.onlinetradeblotter.jpa.User;

import java.util.ArrayList;
import java.util.Date;
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

    /**
     * Default constructor. 
     */
	
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
    	//String queryString_checkUserID = String.format();
    	Query query = em.createQuery("Select username from User u where username = :userName");
    	query.setParameter("userName", userName);
    	List<String> resultList = query.getResultList();
    	System.out.println(resultList);
    	if (resultList.isEmpty()){
    		return false;
    	}
    	else {
    		query = em.createQuery("Select password from User u where username = :userName");
        	query.setParameter("userName", userName);
        	resultList = query.getResultList();
        	System.out.println(resultList);
        	String actualPassword = resultList.get(0);
        	
    		return actualPassword.equals(password);
    	}
//    	
//    	
//    	// check if password is correct.
    }
    
    public List<PricingInfo> getAllPricingInfo(String search){
		List<PricingInfo> prices = new ArrayList<>();
		Query query = em.createQuery("Select p from PricingInfo p where p.product Like '%"+ search + "%'",PricingInfo.class);
    	prices = query.getResultList();
    	System.out.println(prices);
		return prices;
    	
    }
    
    public boolean registerUser(String firstName, String lastName, String userName, String password){
    	Query query = em.createQuery("Select username from User u where username = :userName");
    	query.setParameter("userName", userName);
    	if(query.getResultList().isEmpty()){
    		Date date=new Date();
    	User newUser =new User(firstName, lastName,userName, password,date);
    	em.persist(newUser);
    	em.flush();
    	return true;
    	}
    	else
    		return false;
    }
    
    
    public void updateNote(String userName, String note){
		// check if the userName exists in the table.
		
    	Query query = em.createQuery("Select userName from Scratchpad u where userName = :userName");
    	query.setParameter("userName", userName);
    	List<String> resultList = query.getResultList();
    	System.out.println(resultList);
    	
    	if(!resultList.isEmpty())
		
    	//if (userNameExists)
    	{
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
		//boolean userNameExists = checkUserName_in_Table(userName,"Scratchpad");
		String returnString ;
		Query query = em.createQuery("Select notes from Scratchpad u where userName = :userName");
    	query.setParameter("userName", userName);
    	List<String> resultList = query.getResultList();
    	System.out.println(resultList);
    	
    	if(!resultList.isEmpty())
		
		//if (userNameExists){
    	{
			Scratchpad userPad = em.find(Scratchpad.class, userName);
			returnString = userPad.getNotes();
		}
		else{
			returnString = " ";
		}
		return returnString ;
	}
    
	public String forgotPassword(String userName,String firstName,String lastName){
		Query query = em.createQuery("Select u from User u where userName = :userName");
		String pwd = null;
    	query.setParameter("userName", userName);
    	List<User> resultList = query.getResultList();
    	System.out.println(resultList);
    	for(User user : resultList){
    		System.out.println(user.getFirstName());
    		System.out.println(user.getLastName());
    		if(user.getFirstName().equals(firstName)&&user.getLastName().equals(lastName))
    			pwd=user.getPassword();
    	}
    	if(pwd!=null)
    	return pwd;
    	else
    		return "Credentials didn't match";
	}
	
	
	
}
