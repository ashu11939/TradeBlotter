package team6.onlinetradeblotter.ejb;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import team6.onlinetradeblotter.jpa.Groupstatic;
import team6.onlinetradeblotter.jpa.Message;
import team6.onlinetradeblotter.jpa.PricingInfoWithEntitlement;
import team6.onlinetradeblotter.jpa.Scratchpad;
import team6.onlinetradeblotter.jpa.UserWithEntitlement;

import java.math.BigDecimal;
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
    
    @PersistenceContext(unitName="OnlineTradeBlotterJPA-PU")
    EntityManager em1;
    
    
    
    
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
    
    public List<PricingInfoWithEntitlement> getAllPricingInfoWithEntitlement1(String search,int entitlement){
		List<PricingInfoWithEntitlement> prices = new ArrayList<>();
		//Query query = em.createQuery("Select p.firm,p.price,p.product,p.qty,p.side,p.timeUpdated,p.tradeStatus from PricingInfoWithEntitlement p where p.product Like '%"+ search + "%' and p.entitlement >= "+entitlement,PricingInfoWithEntitlement.class);
		Query query = em.createQuery("Select p from PricingInfoWithEntitlement p where p.product Like '%"+ search + "%' and p.entitlement >= "+entitlement + "order by tradeID DESC",PricingInfoWithEntitlement.class);
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
    
    public void updateNote1(String userName, String note){
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
    		userPad.addNotes(note);
    		em.flush();    		
    	}
    	else{
    		// Insertion
    		Scratchpad newPad = new Scratchpad(userName,note);
    		em.persist(newPad);
    		em.flush();
    	}
	}

    
//    public String createEntry(String side, String firm,BigDecimal price,int qty,String tradeStatus,Date timeUpdated, String userName){
//    	Query query = em.createQuery("Select u.entitlement from UserWithEntitlement u where userName = :userName");
//		query.setParameter("userName", userName);
//		List<String> resultSet = null;
//    	int entitlement;
//		resultSet = query.getResultList();
//    	entitlement = Integer.parseInt(resultSet.get(0));
//    	PricingInfoWithEntitlement newPrice =new PriceWithEntitlement();
//    	return tradeStatus;
//    	
//    }
    
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
	
	public List<PricingInfoWithEntitlement> getAllPricingInfoWithEntitlementWithSort(String sort,int entitlement,int value){
		List<PricingInfoWithEntitlement> prices = new ArrayList<>();
		//Query query = em.createQuery("Select p.firm,p.price,p.product,p.qty,p.side,p.timeUpdated,p.tradeStatus from PricingInfoWithEntitlement p where p.product Like '%"+ search + "%' and p.entitlement >= "+entitlement,PricingInfoWithEntitlement.class);
		Query query = em.createQuery("Select p from PricingInfoWithEntitlement p where p.entitlement = :entitlement order by p."+sort ,PricingInfoWithEntitlement.class);
		query.setParameter("entitlement", entitlement);
		query.setMaxResults(value);
		prices = query.getResultList();
    	System.out.println(prices);
		return prices;
    } 
	
	
	public List<String> getAllUserGroups(String userName){
		List<String> groups = new ArrayList<>();
		Query query = em.createQuery("Select g.groupName from Groupstatic g join g.groupdynamics c where c.username = :userName");
		//Query query = em.createQuery("Select g.groupName from Groupstatic g");
		query.setParameter("userName", userName);
		groups = query.getResultList();
		return groups;
	}
	
//	public List<Message> getAllGroupMessages(String groupName){
//		List<Message> messages = new ArrayList<>();
//		Query query = em.createQuery("Select m from Message m where m.groupstat.groupName = :groupName",Message.class);
//    	query.setParameter("groupName", groupName);
//		messages  = query.getResultList();
//		//em.persist(arg0);
////    	Query query1 = em1.createQuery("Select m from Message m where m.groupID = :groupID",Message.class);
////		//Query query = em.createQuery("Select g.groupName from Groupstatic g");
////		//System.out.println("Here");
////		query1.setParameter("groupID", result);
////		messages = query1.getResultList();
//		return messages;
//	}
	
	
	public List<Message> getAllGroupMessages1(String groupName){
		List<Message> messages = new ArrayList<>();
		Query query = em.createQuery("Select m from Message m where m.groupName = :groupName");
    	query.setParameter("groupName", groupName);
		messages = query.getResultList();
		return messages;
	}
	
	public void addGroupMessages(String groupName,String message,String sender){
		Date date=new Date();
    	Message newMsg =new Message(groupName, message,sender,date);
    	em.persist(newMsg);
    	em.flush();
	}
	
}
