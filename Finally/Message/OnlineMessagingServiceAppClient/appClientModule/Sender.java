import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import online.messaging.service.OnlineMessagingServiceBeanLocal;

import java.util.Properties;
import java.util.Scanner;
//import online.messaging.service;
//jms stuff
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.io.IOException;

public class Sender {

	private QueueConnection queueConnection;
	private QueueSession queueSession;
	private Queue myQueue;
	private QueueSender queueSender;
	
	public void doDemo() throws Exception {
		InitialContext ic = getInitialContext();
		init(ic);
		sendSomeMessages();
		close();
	}


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


	private void sendSomeMessages() throws IOException, JMSException {
		
		Scanner sc = new Scanner(System.in);
				
		String text = "This is message ";
		text += sc.next();
		TextMessage aMessage = queueSession.createTextMessage(text);
		aMessage.setIntProperty("counter",1);
		queueSender.send(aMessage);
		System.out.printf("JMS message sent: %s\n", text);
		
		String sample = "testData";
		OnlineMessagingServiceBeanLocal databaseBean = null;
		databaseBean.storeMessage(sample,sample,sample,sample,sample);
		
		
		
		
	}

	
	private void close() throws JMSException {
		queueSender.close();
		queueSession.close();
		queueConnection.close();
	}
}