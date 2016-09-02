package team6.onlinetradeblotter.ejb;

import static org.junit.Assert.*;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.junit.*;

public class Test1 {

	@Test
	public void testCheckLogin(){
		Context context = null;
		Properties prop = new Properties();
		prop.put(Context.INITIAL_CONTEXT_FACTORY, org.jboss.naming.remote.client.InitialContextFactory.class.getName()); 
		prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		prop.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		prop.put("jboss.naming.client.ejb.context", true);
		try {
			context = new InitialContext(prop);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TestSessionBeanRemote testbean = null;
		//System.out.println("Here");
		try {
			testbean = (TestSessionBeanRemote) context.lookup("OnlineTradeBlotter/OnlineTradeBlotterEJB/TestSessionBean!team6.onlinetradeblotter.ejb.TestSessionBeanRemote");
			} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(testbean.checkLogin("btak","bt123"));
		
	}
	@Test
	public void testCheckLoginEntitlement(){
		Context context = null;
		Properties prop = new Properties();
		prop.put(Context.INITIAL_CONTEXT_FACTORY, org.jboss.naming.remote.client.InitialContextFactory.class.getName()); 
		prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		prop.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		prop.put("jboss.naming.client.ejb.context", true);
		try {
			context = new InitialContext(prop);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SessionBeanWithEntitlementRemote testbean = null;
		//System.out.println("Here");
		try {
			testbean = (SessionBeanWithEntitlementRemote) context.lookup("OnlineTradeBlotter/OnlineTradeBlotterEJB/SessionBeanWithEntitlement!team6.onlinetradeblotter.ejb.SessionBeanWithEntitlementRemote");
			} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(testbean.checkLoginWithEntitlement("btak","bt123"),3);
		
	}
}
