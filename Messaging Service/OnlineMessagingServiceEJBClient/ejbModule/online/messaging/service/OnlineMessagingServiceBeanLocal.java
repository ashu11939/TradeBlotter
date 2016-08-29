package online.messaging.service;

import java.io.IOException;

import javax.ejb.Local;
import javax.jms.JMSException;
import javax.naming.NamingException;

@Local
public interface OnlineMessagingServiceBeanLocal {

	public void doDemo(String Sender, String Message) throws NamingException, JMSException, IOException;
	public void storeMessage(String Sender, String Receiver, String Message, String Queue, String Time );
	
}
