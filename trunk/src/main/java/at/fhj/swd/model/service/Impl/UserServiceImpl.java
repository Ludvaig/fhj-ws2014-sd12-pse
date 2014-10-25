package at.fhj.swd.model.service.Impl;

import javax.ejb.Stateful;
import javax.inject.Inject;

import at.fhj.swd.model.data.UserDAO;
import at.fhj.swd.model.entity.User;
import at.fhj.swd.model.service.UserService;

/**
 * DAO Implementation for User entity
 * 
 * @author Jörg Huber
 * */

@Stateful
public class UserServiceImpl implements UserService {

	private User registeredUser;

	@Inject
	private UserDAO userDAO;

	@Override
	public void registerUser(String username, String password) {
		
		//prove input data
		if(username == null)
			throw new NullPointerException("username could not be null");
		if(password == null)
			throw new NullPointerException("password could not be null");
		
		Boolean registerd = userDAO.proveUserPassswordCombination(username,
				password);
		if (registerd == false) {
			throw new RuntimeException("not registerd");
		}
		registeredUser = new User();
	}

	@Override
	public User getRegisteredUser() {
		return registeredUser;
	}

}
