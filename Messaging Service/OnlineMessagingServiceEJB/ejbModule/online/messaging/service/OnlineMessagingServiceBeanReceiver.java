package online.messaging.service;

import java.util.Properties;

import javax.ejb.*;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Stateless
@Local(OnlineMessagingServiceBeanReceiverLocal.class)
@Remote(OnlineMessagingServiceBeanReceiverRemote.class)
public class OnlineMessagingServiceBeanReceiver implements OnlineMessagingServiceBeanReceiverRemote, OnlineMessagingServiceBeanReceiverLocal {


	private QueueConnection queueConnection;
	private QueueSession queueSession;
	private Queue myQueue;
	private QueueReceiver queueReceiver;

	private boolean quit = false;
	
	@Override
	public void doDemo(String receiver) throws Exception {

		InitialContext ic = getInitialContext();
		
		init(ic);
		System.out.println("Receiver is ready to receive messages (To quit, send a \"quit\" message from QueueSender.class).");
		
		// Waiting until a "quit" message has been received on the queue.
		synchronized (this) {
			while (!quit) {
				try {
					wait();
				} 
				catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		}
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
		queueReceiver = queueSession.createReceiver(myQueue);
		queueReceiver.setMessageListener((MessageListener) this);
		queueConnection.start();
	}


	public void onMessage(Message msg) {
		
		try {
			String msgText;
			if (msg instanceof TextMessage) {
				msgText = ((TextMessage) msg).getText();
			} 
			else {
				msgText = msg.toString();
			}
			
			System.out.printf("\n<Msg_Receiver> %s", msgText);
			
			if (msgText.equalsIgnoreCase("quit")) {
				synchronized (this) {
					quit = true;
					this.notifyAll(); // Notify main thread to quit.
				}
			}
		} 
		catch (JMSException ex) {
			ex.printStackTrace();
		}
	}


	private void close() throws JMSException {
		queueReceiver.close();
		queueSession.close();
		queueConnection.close();
	}


	
	
}
