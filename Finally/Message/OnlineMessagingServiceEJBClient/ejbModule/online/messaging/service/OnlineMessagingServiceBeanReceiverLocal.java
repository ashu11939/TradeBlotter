package online.messaging.service;

import java.util.List;

import javax.ejb.Local;

import model.DisplayMessages;

@Local
public interface OnlineMessagingServiceBeanReceiverLocal {
	
	public List<DisplayMessages> doDemo(String receiver) throws Exception;
}
