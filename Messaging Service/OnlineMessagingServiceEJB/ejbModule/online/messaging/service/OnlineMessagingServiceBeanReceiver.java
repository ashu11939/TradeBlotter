package online.messaging.service;

import java.util.ArrayList;
import java.util.List;
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

import model.DisplayMessages;

@Stateless
@Local(OnlineMessagingServiceBeanReceiverLocal.class)
@Remote(OnlineMessagingServiceBeanReceiverRemote.class)
public class OnlineMessagingServiceBeanReceiver
		implements OnlineMessagingServiceBeanReceiverRemote, OnlineMessagingServiceBeanReceiverLocal {

	@Override
	public List<DisplayMessages> doDemo(String receiver) throws Exception {

		return MyConstants.msglist;

	}
}
