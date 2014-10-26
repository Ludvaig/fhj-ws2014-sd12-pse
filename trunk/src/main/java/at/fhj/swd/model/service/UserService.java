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
	 * on success this method will return true
	 * */
	void registerUser(String userName,String password);
	
	/**
	 * get the registered user
	 * if the user has not registered yet
	 * the method will return null
	 * */
	User getRegisteredUser();
	
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