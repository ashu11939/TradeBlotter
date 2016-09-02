package team6.onlinetradeblotter.appclient;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import team6.onlinetradeblotter.ejb.SessionBeanWithEntitlementRemote;
import team6.onlinetradeblotter.ejb.TestSessionBeanRemote;
import team6.onlinetradeblotter.jpa.Message;
import team6.onlinetradeblotter.jpa.PricingInfo;
import team6.onlinetradeblotter.jpa.PricingInfoWithEntitlement;
import team6.onlinetradeblotter.jpa.User;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties prop = new Properties();
		prop.put(Context.INITIAL_CONTEXT_FACTORY, org.jboss.naming.remote.client.InitialContextFactory.class.getName()); 
		prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		prop.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		prop.put("jboss.naming.client.ejb.context", true);

		// Create the JNDI InitialContext.
		Context context = null;
		try {
			context = new InitialContext(prop);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TestSessionBeanRemote testbean = null;
		SessionBeanWithEntitlementRemote sessionbean = null;
		//System.out.println("Here");
		try {
			testbean = (TestSessionBeanRemote) context.lookup("OnlineTradeBlotter/OnlineTradeBlotterEJB/TestSessionBean!team6.onlinetradeblotter.ejb.TestSessionBeanRemote");
			sessionbean = (SessionBeanWithEntitlementRemote) context.lookup("OnlineTradeBlotter/OnlineTradeBlotterEJB/SessionBeanWithEntitlement!team6.onlinetradeblotter.ejb.SessionBeanWithEntitlementRemote");
			} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\n\n"+testbean.toString()+"\n\n");
//		List<User> users = new ArrayList<>();
//		//users = testbean.getAllUsers();
//        for (User user: users) {
//            System.out.println("Got user " + user.getUsername());
//        }
		System.out.println(testbean.checkLogin("btak","bt123"));
		
		System.out.println(testbean.registerUser("Nitish","Kumar","nk8663","12343"));
		
		List<PricingInfo> prices = new ArrayList<>();
		prices = testbean.getAllPricingInfo("Rel");
        for (PricingInfo price: prices) {
            System.out.println("Got price " + price.getProduct());
        }
        
        testbean.updateNote("btak", "These are my notes");
        testbean.updateNote("nk8663", "My notes changes");
        testbean.getNote("nk8663");
        System.out.println(testbean.forgotPassword("btak","Bhavya","Tak"));
        
        //Here
        
        System.out.println(sessionbean.checkLoginWithEntitlement("btak","bt123"));
		
		System.out.println(sessionbean.registerUserWithEntitlement("Nitish","Kumar","nk8663","12343",1));
//		
		List<PricingInfoWithEntitlement> pricesEntitlement = new ArrayList<>();
		pricesEntitlement = sessionbean.getAllPricingInfoWithEntitlement("",3);
        for (PricingInfoWithEntitlement priceEntitlement: pricesEntitlement) {
            System.out.println("Got price " + priceEntitlement.getProduct());
        }
//        
        sessionbean.updateNote("btak", "These are my notes");
        sessionbean.updateNote("nk8663", "My notes changes");
        sessionbean.getNote("nk8663");
        System.out.println(sessionbean.forgotPassword("btak","Bhavya","Tak"));
        
        List<PricingInfoWithEntitlement> pricesEntitlement1 = new ArrayList<>();
		pricesEntitlement = sessionbean.getAllPricingInfoWithEntitlementWithSort("price",3,20);
        for (PricingInfoWithEntitlement priceEntitlement: pricesEntitlement) {
            System.out.println("Got price " + priceEntitlement.getPrice());
        }
        
        //System.out.println("Reached Here");
        
        List<String> groups = new ArrayList<>();
		groups = sessionbean.getAllUserGroups("btak");
        for (String group: groups) {
            System.out.println("Got group " + group);
        }
        
        List<Message> msg = new ArrayList<>();
		msg = sessionbean.getAllGroupMessages1("102");
        for(Message message : msg){
        	System.out.println(message.getMessage());
        }
        
        sessionbean.addGroupMessages("USDINR", "This is my msg", "btak");
        
	}

	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public Main() {
		super();
	}

}