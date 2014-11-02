package at.fhj.swd.model.service.Impl;

import java.math.BigInteger;
import java.security.SecureRandom;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;

import at.fhj.swd.model.data.UserDAO;
import at.fhj.swd.model.entity.User;
import at.fhj.swd.model.service.UserService;

/**
 * DAO Implementation for User entity
 * 
 * @author Jörg Huber
 * */
@Stateless
public class UserServiceImpl implements UserService {

	@Inject
	private UserDAO userDAO;

	@Override
	public String registerUser(String username, String password) {

		// prove if input data are set
		if (username == null)
			throw new NullPointerException("username could not be null");
		if (password == null)
			throw new NullPointerException("password could not be null");

		// check if the user password combination is correct
		User user = userDAO.proveUserPassswordCombination(username, password);

		// throw exception if registration failed
		if (user == null) {
			throw new RuntimeException("not registerd");
		}

		// Generate a new token
		String token = GetNewToken();

		// store token to user
		user.setToken(token);

		// persist the user
		userDAO.persist(user);

		//return the created token (store this token to cookies)
		return token;
	}

	private String GetNewToken() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}

	@Override
	public User getRegisteredUser(String token) {
		return userDAO.loadUserByToken(token);
	}

	@Override
	public void insertUser(User user) {
		userDAO.persist(user);
	}

	@Override
	public boolean UserIsAdmin(User user) {
		return user.getUsername().endsWith("_a");
	}

	@Override
	public boolean UserIsPortalAdmin(User user) {
		return user.getUsername().endsWith("_pa");
	}

	@Override
	public void loggoutUser(String userName) {
		
		// load user by user name
		User user = userDAO.loadUserByName(userName);

		// throw exception if the user was not found
		if (user == null) {
			throw new RuntimeException("could not find user in database");
		}
		
		//set token initial
		user.setToken("");
		
		//persist user
		userDAO.persist(user);
	}

}
