package online.messaging.service;

import javax.ejb.Local;

@Local
public interface OnlineMessagingServiceBeanReceiverLocal {
	
	public void doDemo(String receiver) throws Exception;
}
