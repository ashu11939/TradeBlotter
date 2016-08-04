package online.messaging.service;

import java.util.List;

import javax.ejb.Remote;

import model.DisplayMessages;

@Remote
public interface OnlineMessagingServiceBeanReceiverRemote {

	public List<DisplayMessages> doDemo(String receiver) throws Exception;
}
