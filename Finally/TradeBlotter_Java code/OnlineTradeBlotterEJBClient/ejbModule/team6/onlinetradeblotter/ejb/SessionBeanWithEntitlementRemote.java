package team6.onlinetradeblotter.ejb;

import java.util.List;

import team6.onlinetradeblotter.jpa.Message;
import team6.onlinetradeblotter.jpa.PricingInfoWithEntitlement;

public interface SessionBeanWithEntitlementRemote {
	public int checkLoginWithEntitlement(String userName , String password);
	public boolean registerUserWithEntitlement(String firstName, String lastName, String userName, String password,int entitlement);
	public List<PricingInfoWithEntitlement> getAllPricingInfoWithEntitlement(String search,int entitlement);
	public List<PricingInfoWithEntitlement> getAllPricingInfoWithEntitlement1(String search,int entitlement);
    public void updateNote(String userName, String note);
    public void updateNote1(String userName, String note);
	public String getNote(String userName);
	public String forgotPassword(String userName,String firstName,String lastName);
	public List<PricingInfoWithEntitlement> getAllPricingInfoWithEntitlementWithSort(String sort,int entitlement,int value);
	public List<String> getAllUserGroups(String userName);
	public List<Message> getAllGroupMessages1(String groupName);
	public void addGroupMessages(String groupName,String message,String sender);
}
