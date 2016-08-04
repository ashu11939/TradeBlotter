package online.messaging.service;

import java.io.IOException;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.Init;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "messagingType", propertyValue = "javax.jms.MessageListener"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jboss/exported/jms/queue/TestQ1"),
		@ActivationConfigProperty(propertyName = "ConnectionFactoryName", propertyValue = "java:/ConnectionFactory"), })
public class MessageReceiver implements MessageListener {

	public void onMessage(Message message) {
		try {
			if (message instanceof TextMessage) {
				TextMessage m = (TextMessage) message;
				System.out.println(m.getText());
				MyConstants.ReceivedMessages = m.getText();
			}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}