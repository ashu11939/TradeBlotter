package online.messaging.service;

public class MyConstants {
	
	public final static String JMS_CONNECTION_FACTORY_JNDI = "jms/RemoteConnectionFactory";
	public final static String JMS_QUEUE_JNDI = "jms/queue/TestQ1";
	public final static String JMS_USERNAME = "jmsuser1";     // The role for this user is "guest" in ApplicationRealm
	public final static String JMS_PASSWORD = "password-1";
	public final static String WILDFLY_REMOTING_URL = "http-remoting://localhost:8080";

}
