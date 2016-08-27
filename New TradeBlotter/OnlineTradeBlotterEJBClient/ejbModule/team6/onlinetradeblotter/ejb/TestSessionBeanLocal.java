package team6.onlinetradeblotter.ejb;

import java.util.List;

import javax.ejb.Local;

import team6.onlinetradeblotter.jpa.User;

@Local
public interface TestSessionBeanLocal {
	public List<User> getAllUsers();
	public boolean checkLogin(String userName , String password);
	public void registerUser(String firstName, String lastName, String userName, String password);
	public boolean checkUserName_in_Table(String userName,String tableName);
	public void updateNote(String userName, String note);
	public String getNote(String userName);
	
}
