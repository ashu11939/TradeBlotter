package online.messaging.service;

import java.io.IOException;

import javax.ejb.Remote;
import javax.jms.JMSException;
import javax.naming.NamingException;

@Remote
public interface OnlineMessagingServiceBeanRemote {
	
	public void doDemo(String Sender, String Message) throws NamingException, JMSException, IOException;
	public void storeMessage(String Sender, String Receiver, String Message, String Queue, String Time );

}
