package team6.onlinetradeblotter.appclient;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import team6.onlinetradeblotter.ejb.TestSessionBeanRemote;
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
		//System.out.println("Here");
		try {
			testbean = (TestSessionBeanRemote) context.lookup("OnlineTradeBlotter/OnlineTradeBlotterEJB/TestSessionBean!team6.onlinetradeblotter.ejb.TestSessionBeanRemote");
			} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\n\n"+testbean.toString()+"\n\n");
		List<User> users = new ArrayList<>();
		users = testbean.getAllUsers();
        for (User user: users) {
            System.out.println("Got user " + user.getUsername());
        }
		//testbean.getAllUsers();
	}

	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public Main() {
		super();
	}

}