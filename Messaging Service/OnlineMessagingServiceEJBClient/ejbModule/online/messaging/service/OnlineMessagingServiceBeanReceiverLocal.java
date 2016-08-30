package online.messaging.service;

import javax.ejb.Local;

@Local
public interface OnlineMessagingServiceBeanReceiverLocal {
	
	public String doDemo(String receiver) throws Exception;
}
