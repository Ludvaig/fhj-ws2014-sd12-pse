package at.fhj.swd.model.service;

import at.fhj.swd.model.entity.User;

/**
 * service to load information
 * about user objects
 * 
 * @author Jörg Huber
 */
public interface UserService {
	
	/**
	 * register user with user name and password
	 * a new token will be created
	 * */
	String registerUser(String userName,String password);
	
	/**
	 * get the registered user
	 * if the user has not registered yet
	 * the method will return null
	 * */
	User getRegisteredUser(String token);
	
	/**
	 * insert new User to Database
	 * */
	void insertUser(User user);

	/**
	 * check if User is Admin
	 * */
	boolean UserIsAdmin(User user);
	
	/**
	 * check if User is Portaladmin
	 * */
	boolean UserIsPortalAdmin(User user);	
}