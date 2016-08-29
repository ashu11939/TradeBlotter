
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
	
	public MessagingResource(){
		try {
			context = new InitialContext();
			sessionBeanSender = (OnlineMessagingServiceBeanLocal) context.lookup("java:app/OnlineMessagingServiceEJB/OnlineMessagingServiceBean!online.messaging.service.OnlineMessagingServiceBeanLocal");
			sessionBeanReceiver = (OnlineMessagingServiceBeanReceiverLocal) context.lookup("java:app/OnlineMessagingServiceEJB/OnlineMessagingServiceBeanReceiver!online.messaging.service.OnlineMessagingServiceBeanReceiverLocal");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Send Messages API
	@GET
	@Produces("text/plain")
	@Path("/sendMessages")
	public String sendMessages(@QueryParam("sender") String Sender, @QueryParam("message") String Message) throws NamingException, JMSException, IOException {
		
		Sender="jmsuser1";
		Message="Hello";
		sessionBeanSender.doDemo(Sender, Message);
		String Sample = "test";
		sessionBeanSender.storeMessage(Sample, Sample, Sample, Sample, Sample);
		
		System.out.println("Received a GET request from app");
		return "Hello from Backend!";
	}
	
	//Receive Messages API
	@GET
	@Produces("text/plain")
	@Path("/receiveMessage")
	public String receiveMessage(String receiver) throws Exception{
	
		sessionBeanReceiver.doDemo(receiver);
		System.out.println("Received a GET request from app");
		return "Hello from Backend!";
	}
	
	
}
