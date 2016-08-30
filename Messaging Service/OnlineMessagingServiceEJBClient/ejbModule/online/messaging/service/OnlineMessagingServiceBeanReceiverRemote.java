package online.messaging.service;

import javax.ejb.Remote;

@Remote
public interface OnlineMessagingServiceBeanReceiverRemote {

	public String doDemo(String receiver) throws Exception;
}
