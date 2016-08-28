package team6.onlinetradeblotter.ejb;

import java.util.List;

import team6.onlinetradeblotter.jpa.PricingInfoWithEntitlement;

public interface SessionBeanWithEntitlementLocal {
	public int checkLoginWithEntitlement(String userName , String password);
	public boolean registerUserWithEntitlement(String firstName, String lastName, String userName, String password,int entitlement);
	public List<PricingInfoWithEntitlement> getAllPricingInfoWithEntitlement(String search,int entitlement);
    public void updateNote(String userName, String note);
	public String getNote(String userName);
	public String forgotPassword(String userName,String firstName,String lastName);
}
