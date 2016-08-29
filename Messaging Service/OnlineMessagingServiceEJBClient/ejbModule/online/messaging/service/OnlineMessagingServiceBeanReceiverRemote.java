package online.messaging.service;

import javax.ejb.Remote;

@Remote
public interface OnlineMessagingServiceBeanReceiverRemote {

	public void doDemo(String receiver) throws Exception;
}
