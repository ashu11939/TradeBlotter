package team6.onlinetradeblotter.ejb;

import java.util.List;

import javax.ejb.Local;

import team6.onlinetradeblotter.jpa.User;

@Local
public interface TestSessionBeanLocal {
	public List<User> getAllUsers();
	public boolean checkLogin(String userName , String password);
}
