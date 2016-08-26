package team6.onlinetradeblotter.ejb;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    
}
