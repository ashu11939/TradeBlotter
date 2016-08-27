package team6.onlinetradeblotter.ejb;

import java.util.List;

import javax.ejb.Local;

import team6.onlinetradeblotter.jpa.PricingInfo;
import team6.onlinetradeblotter.jpa.User;

@Local
public interface TestSessionBeanLocal {
	public List<User> getAllUsers();
	public boolean checkLogin(String userName , String password);
	public boolean registerUser(String firstName, String lastName, String userName, String password);
	public List<PricingInfo> getAllPricingInfo(String search);
    public void updateNote(String userName, String note);
	public String getNote(String userName);
}
