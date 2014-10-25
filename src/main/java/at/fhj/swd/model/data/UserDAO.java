package at.fhj.swd.model.data;

import at.fhj.swd.model.entity.User;

/**
 * Data Access Object for the User entity
 * 
 * @author Jörg Huber
 * */
public interface UserDAO {

	/**
	 * prove if the given combination of user name
	 * and password is valid
	 * */
	User proveUserPassswordCombination(String userName,String password);
	
	void insert(User user);
	
	User loadUserByName(String username);
}
