package io.ztech.jkingsley.streetrun.dao;

import java.util.ArrayList;

import io.ztech.jkingsley.streetrun.beans.User;

public interface UserDAO {
	/*BigInteger user_id;
	String first_name;
	String last_name;
	float last_known_lat;
	float last_known_long;*/
	
	public ArrayList<User> getAllUsers();
	public User getUserById(Long user_id);
	
	public boolean insertUser(User user);
	public boolean updateUser(User user);
	public boolean deleteUser(User user);
}
