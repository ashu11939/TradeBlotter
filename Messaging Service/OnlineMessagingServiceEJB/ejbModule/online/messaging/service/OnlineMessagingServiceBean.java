package online.messaging.service;

import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import javax.ejb.*;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class OnlineMessagingServiceBean
 */
@Stateless
@Local(OnlineMessagingServiceBeanLocal.class)
@Remote(OnlineMessagingServiceBeanRemote.class)
public class OnlineMessagingServiceBean implements OnlineMessagingServiceBeanRemote, OnlineMessagingServiceBeanLocal {

	@PersistenceContext(unitName="OnlineMessagingServiceJPA-PU")
    EntityManager em;

	private QueueConnection queueConnection;
	private QueueSession queueSession;
	private Queue myQueue;
	private QueueSender queueSender;

	
	//Send Messages after receiving api request
	public void doDemo(String Sender, String Message) throws NamingException, JMSException, IOException{
		InitialContext ic = getInitialContext();
		init(ic);
		sendSomeMessages(Message);
		close();
	}
	
	
	// doDemo Implementation for sending messages ##############################################
	
	private InitialContext getInitialContext() throws NamingException {

		InitialContext context = null;
		
		try {
			Properties props = new Properties();
			props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			props.put(Context.PROVIDER_URL, MyConstants.WILDFLY_REMOTING_URL); 
			props.put(Context.SECURITY_PRINCIPAL, MyConstants.JMS_USERNAME);
			props.put(Context.SECURITY_CREDENTIALS, MyConstants.JMS_PASSWORD);

			context = new InitialContext(props);
			System.out.println("\n\tGot initial Context: " + context);
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return context;
	}

	
	private void init(Context ctx) throws NamingException, JMSException {

		QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) ctx.lookup(MyConstants.JMS_CONNECTION_FACTORY_JNDI);

		// If you don't pass JMS credential here then you will get
		// [javax.jms.JMSSecurityException: HQ119031: Unable to validate user: null]
		queueConnection = queueConnectionFactory.createQueueConnection(MyConstants.JMS_USERNAME, MyConstants.JMS_PASSWORD);

		queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		myQueue = (Queue) ctx.lookup(MyConstants.JMS_QUEUE_JNDI);
		queueSender = queueSession.createSender(myQueue);
		queueConnection.start();
	}


	private void sendSomeMessages(String message) throws IOException, JMSException {
		
		Scanner sc = new Scanner(System.in);
				
		String text = "This is message ";
		text += message;
		TextMessage aMessage = queueSession.createTextMessage(text);
		aMessage.setIntProperty("counter",1);
		queueSender.send(aMessage);
		System.out.printf("JMS message sent: %s\n", text);
		
		
	}

	private void close() throws JMSException {
		queueSender.close();
		queueSession.close();
		queueConnection.close();
	}
	
	// ##############################################
	
	
    //TODO : Store messages in database table messaging.
    public void storeMessage(String Sender, String Receiver, String Message, String Queue, String Time ) {
    	//Query q
    	System.out.println("Sender : " + Sender);
    }
    
    
    //Peer to peer messaging service


}
