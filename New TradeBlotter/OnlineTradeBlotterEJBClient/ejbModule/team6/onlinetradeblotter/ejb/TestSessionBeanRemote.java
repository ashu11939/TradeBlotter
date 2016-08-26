package team6.onlinetradeblotter.ejb;

import java.util.List;

import javax.ejb.Remote;

import team6.onlinetradeblotter.jpa.User;

@Remote
public interface TestSessionBeanRemote {
	public List<User> getAllUsers();
	
}
