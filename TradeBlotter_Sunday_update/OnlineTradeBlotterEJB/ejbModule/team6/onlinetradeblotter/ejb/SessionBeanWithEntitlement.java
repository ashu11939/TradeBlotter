package team6.onlinetradeblotter.ejb;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import team6.onlinetradeblotter.jpa.PricingInfoWithEntitlement;
import team6.onlinetradeblotter.jpa.Scratchpad;
import team6.onlinetradeblotter.jpa.UserWithEntitlement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.*;
/**
 * Session Bean implementation class TestSessionBean
 */
@Stateful
//@TransactionManagement(TransactionManagementType.BEAN)
@Local(SessionBeanWithEntitlementLocal.class)
@Remote(SessionBeanWithEntitlementRemote.class)
public class SessionBeanWithEntitlement implements SessionBeanWithEntitlementRemote, SessionBeanWithEntitlementLocal {

    /**
     * Default constructor. 
     */
	
    public SessionBeanWithEntitlement() {
        // TODO Auto-generated constructor stub
    }
    
    @PersistenceContext(unitName="OnlineTradeBlotterJPA-PU")
    EntityManager em;
    
    
    
    public int checkLoginWithEntitlement(String userName , String password){
    	// Check if username is correct.
    	//String queryString_checkUserID = String.format();
    	Query query = em.createQuery("Select username from UserWithEntitlement u where username = :userName");
    	query.setParameter("userName", userName);
    	List<String> resultList = query.getResultList();
    	System.out.println(resultList);
    	if (resultList.isEmpty()){
    		return 0;
    	}
    	else {
    		query = em.createQuery("Select u from UserWithEntitlement u where username = :userName");
        	query.setParameter("userName", userName);
        	List<UserWithEntitlement> resultList1 = new ArrayList<>();
        	resultList1 = query.getResultList();
        	System.out.println(resultList1);
        	String actualPassword = null;
        	int entitlement = 0;
        	for(UserWithEntitlement userEntitlement:resultList1){
        		actualPassword = userEntitlement.getPassword();
        		entitlement = userEntitlement.getEntitlement();
        	}
        	
    		if(actualPassword.equals(password))
    			return entitlement;
    		else
    			return 0;
    	}
// check if password is correct.
    }
    
    
    public List<PricingInfoWithEntitlement> getAllPricingInfoWithEntitlement(String search,int entitlement){
		List<PricingInfoWithEntitlement> prices = new ArrayList<>();
		//Query query = em.createQuery("Select p.firm,p.price,p.product,p.qty,p.side,p.timeUpdated,p.tradeStatus from PricingInfoWithEntitlement p where p.product Like '%"+ search + "%' and p.entitlement >= "+entitlement,PricingInfoWithEntitlement.class);
		Query query = em.createQuery("Select p from PricingInfoWithEntitlement p where p.product Like '%"+ search + "%' and p.entitlement >= "+entitlement,PricingInfoWithEntitlement.class);
		prices = query.getResultList();
    	System.out.println(prices);
		return prices;
    }
    
    public boolean registerUserWithEntitlement(String firstName, String lastName, String userName, String password,int entitlement){
    	Query query = em.createQuery("Select username from UserWithEntitlement u where username = :userName");
    	query.setParameter("userName", userName);
    	if(query.getResultList().isEmpty()){
    		Date date=new Date();
    	UserWithEntitlement newUser =new UserWithEntitlement(firstName, lastName,userName, password,date,entitlement);
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
		Query query = em.createQuery("Select u from UserWithEntitlement u where userName = :userName");
		String pwd = null;
    	query.setParameter("userName", userName);
    	List<UserWithEntitlement> resultList = query.getResultList();
    	System.out.println(resultList);
    	for(UserWithEntitlement user : resultList){
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
