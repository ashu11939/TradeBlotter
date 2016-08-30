
import java.io.IOException;
import java.util.List;

import javax.jms.JMSException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.*;

import model.DisplayMessages;
import online.messaging.service.OnlineMessagingServiceBeanLocal;
import online.messaging.service.OnlineMessagingServiceBeanReceiverLocal;

@Path("/msg")
public class MessagingResource {

	private OnlineMessagingServiceBeanLocal sessionBeanSender;
	private OnlineMessagingServiceBeanReceiverLocal sessionBeanReceiver;

	Context context;

	public MessagingResource() {
		try {
			context = new InitialContext();
			sessionBeanSender = (OnlineMessagingServiceBeanLocal) context.lookup(
					"java:app/OnlineMessagingServiceEJB/OnlineMessagingServiceBean!online.messaging.service.OnlineMessagingServiceBeanLocal");
			sessionBeanReceiver = (OnlineMessagingServiceBeanReceiverLocal) context.lookup(
					"java:app/OnlineMessagingServiceEJB/OnlineMessagingServiceBeanReceiver!online.messaging.service.OnlineMessagingServiceBeanReceiverLocal");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@GET
	@Produces("text/plain")
	public String testFunction() {
		return "Test Function is working.";
	}

	// Send Messages API
	@GET
	@Produces("text/plain")
	@Path("/sendMessages")
	public String sendMessages(@QueryParam("sender") String sender, @QueryParam("message") String message, @QueryParam("group") String groupName)
			throws NamingException, JMSException, IOException {

		sessionBeanSender.doDemo(sender, message, groupName);
		
		System.out.println("Received a GET request from app");
		return "Hello from Backend!";
	}

	// Receive Messages API
	@GET
	@Path("/receiveMessage")
	@Produces("application/json")
	public List<DisplayMessages> receiveMessage(@QueryParam("receiver") String groupName) throws Exception {
		
		
		
		List<DisplayMessages> received = sessionBeanReceiver.doDemo(groupName);
		System.out.println(received);
		
		return received;
	}

}
