package at.fhj.swd.service;

import at.fhj.swd.data.entity.User;

/**
 * service to load information
 * about user objects
 * 
 * @author Joerg Huber, Lukas Kranabetter
 */
public interface UserService {
	
	/**
	 * register user with user name and password
	 * a new token will be created (store this token into Cookies)
	 * */
	String registerUser(String userName,String password);
	
	/**
	 * Register a user by form registration.
	 */
	void registerUser(User user);
	
	/**
	 * get the registered user
	 * if the user has not registered yet
	 * the method will return null
	 * */
	User getRegisteredUser(String token);
	
	/**
	 * check if User is Admin
	 * */
	boolean userIsAdmin(User user);
	
	/**
	 * check if User is Portaladmin
	 * */
	boolean userIsPortalAdmin(User user);
	
	/**
	 * logout the given user
	 * (remove the authToken from the user)
	 * */
	void loggoutUser(String userName);
	
	/**
	 * updates the given user
	 */
	User updateUser(User user);
	
	/**
	 * load User by Id from Database
	 * */
	User getUserById(long id);
	
	/**
	 * load User by Username from Database
	 * */
	User getUserByUsername(String username);

	/**
	 * check if password is correct for the user and load user from Database
	 * */
	User proveUserPassswordCombination(String username, String password); 
}