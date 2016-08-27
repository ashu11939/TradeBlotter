package team6.onlinetradeblotter.webproject;


import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import team6.onlinetradeblotter.ejb.TestSessionBeanLocal;
import team6.onlinetradeblotter.jpa.PricingInfo;


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
	
//	@GET
//	@Path("/signin")
//	@Produces("application/json")
//	public List<User> getAllUsers(){
//		List<User> users = new ArrayList<User>();
//		users = testbean.getAllUsers();
//		return users;
//	}
	
//	@GET
//	@Path("/register")
//	@Produces("text/plain")
//	public void register(@QueryParam("firstName") String firstName, @QueryParam("lastName") String lastName, @QueryParam("username") String username, @QueryParam("password") String password){
//		//if(testbean.)
//		System.out.println(firstName+lastName); 
//		
//	}
//	@GET
//	@Path("/register")
//	@Produces("text/plain")
//	public String register(@QueryParam("firstName") String firstName, @QueryParam("lastName") String lastName, @QueryParam("username") String username, @QueryParam("password") String password){
//		if(testbean.)
//		return "Yes";
//		
//	}
	
	@GET
	@Path("/signin")
	@Produces("text/plain")
	public String testUserKeyCorrect(@QueryParam("userName") String userName, @QueryParam("key") String key){
		System.out.println(userName+key);
		if(testbean.checkLogin(userName,key))
		return "yes";
		else
			return "no";
	}
	
	@GET
	@Path("/getPrices")
	@Produces("application/json")
	public List<PricingInfo> getAllPricingInfo(@QueryParam("search") @DefaultValue("")String search){
		List<PricingInfo> list = new ArrayList<>();
		list = testbean.getAllPricingInfo(search);
		return list;
	}
	
//	@GET
//	@Path("/getPrices")
//	@Produces("application/json")
//	public List<PricingInfo>(@QueryParam("search") @DefaultValue("")String search){
//		
//	}
	
	@GET
	@Path("/register")
	@Produces("text/plain")
	public String registerUser(@QueryParam("firstName") String firstName, @QueryParam("lastName") String lastName, @QueryParam("userName") String userName, @QueryParam("key") String key){
		System.out.println(userName+key+firstName+lastName);
		if(testbean.registerUser(firstName,lastName,userName,key))
		return "yes";
		else
			return "no";
	}
	
	@GET
	@Path("/getNotes")
	@Produces("application/json")
	public String getNotes(@QueryParam("userName") String userName){
		return testbean.getNote(userName);
	}
	
	@GET
	@Path("/saveNotes")
	@Produces("application/json")
	public void saveNotes(@QueryParam("userName") String userName,@QueryParam("notes") String notes){
		//return 
		testbean.updateNote(userName, notes);
	}
	
}
