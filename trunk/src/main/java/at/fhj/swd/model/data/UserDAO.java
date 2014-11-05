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
	
	/**
	 * persist a User to database
	 **/
	void persist(User user);
	
	/**
	 *load a User by his user name
	 */
	User loadUserByName(String username);
	
	
	/**
	 *load a User by user id
	 */
	User loadUserById(long id);
	
	/**
	 *load a User by his token
	 */
	User loadUserByToken(String token);
	
	/**
	 * updates the given user
	 * @param user
	 * @return
	 */
	User updateUser(User user);
	

}
