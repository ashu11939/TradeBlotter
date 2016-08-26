package team6.onlinetradeblotter.webproject;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import team6.onlinetradeblotter.ejb.TestSessionBean;
import team6.onlinetradeblotter.ejb.TestSessionBeanLocal;
import team6.onlinetradeblotter.jpa.User;

@RequestScoped
@Path("/app")
public class BlotterResource {
	
	TestSessionBeanLocal testbean;
	
	Context context;
	
	public BlotterResource(){
		try {
			context = new InitialContext();
			testbean = (TestSessionBeanLocal) context.lookup("java:app/OnlineTradeBlotterEJB/TestSessionBean!team6.onlinetradeblotter.ejb.TestSessionBeanLocal");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@GET
	@Produces("text/plain")
	public String hello() {
	System.out.println("Received a GET request from app");
	return "Hello from Backend!";
	}
	
	@GET
	@Path("/signin")
	@Produces("application/json")
	public List<User> getAllUsers(){
		List<User> users = new ArrayList<User>();
		users = testbean.getAllUsers();
		return users;
	}
	
	@GET
	@Path("/{userName}/{key}")
	@Produces("text/plain")
	boolean testUserKeyCorrect(@PathParam("userName") String userName, @PathParam("key") String key){
		testbean = new TestSessionBean();
		return testbean.checkLogin(userName,key);

	}
}
