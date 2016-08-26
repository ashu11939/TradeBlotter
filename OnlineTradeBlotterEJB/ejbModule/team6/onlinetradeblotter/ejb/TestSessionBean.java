package team6.onlinetradeblotter.ejb;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    
    public void get
    
}
