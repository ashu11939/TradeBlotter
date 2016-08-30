
import java.io.IOException;

import javax.jms.JMSException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.*;
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
	public String sendMessages(@QueryParam("sender") String sender, @QueryParam("message") String message)
			throws NamingException, JMSException, IOException {

		//sender = "jmsuser1";
		//message = "Hello";
		sessionBeanSender.doDemo(sender, message);
		// String Sample = "test";
		// sessionBeanSender.storeMessage(Sample, Sample, Sample, Sample,
		// Sample);

		System.out.println("Received a GET request from app");
		return "Hello from Backend!";
	}

	// Receive Messages API
	@GET
	@Produces("text/plain")
	@Path("/receiveMessage")
	public String receiveMessage(@QueryParam("receiver") String receiver) throws Exception {

		String received = sessionBeanReceiver.doDemo(receiver);
		//System.out.println(received);
		return received;
	}

}
