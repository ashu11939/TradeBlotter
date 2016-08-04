package online.messaging.service;

import java.util.ArrayList;
import java.util.List;

import model.DisplayMessages;

public class MyConstants {
	public final static String JMS_CONNECTION_FACTORY_JNDI = "java:jboss/exported/jms/RemoteConnectionFactory";
	public final static String JMS_QUEUE_JNDI = "java:/jboss/exported/jms/queue/TestQ1";
	public final static String JMS_USERNAME = "jmsuser1";     // The role for this user is "guest" in ApplicationRealm
	public final static String JMS_PASSWORD = "password-1";
	public final static String WILDFLY_REMOTING_URL = "http-remoting://localhost:8080";
	public static String ReceivedMessages = null;
	public static String Sender = null;
	//To facilitate group messaging.
	public static List<DisplayMessages> msglist = new ArrayList<>();
	public static List<DisplayMessages> msgListTraders = new ArrayList<>();
	public static List<DisplayMessages> msgListCritical = new ArrayList<>();
	public static List<DisplayMessages> msgListPersonal = new ArrayList<>();
	public static List<DisplayMessages> msgListNull = new ArrayList<>();
	public static String JMS_USER_QUEUE = "java:/jboss/exported/jms/queue/";
}


