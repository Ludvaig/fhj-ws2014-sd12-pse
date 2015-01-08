package at.fhj.swd.service.impl;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.inject.Inject;

import at.fhj.swd.data.UserDAO;
import at.fhj.swd.data.entity.User;
import at.fhj.swd.service.UserService;
import at.fhj.swd.service.exceptions.ServiceLayerException;
import at.fhj.swd.service.exceptions.UserLoginException;

import javax.inject.Named;

import org.eclipse.jetty.util.log.Log;

/**
 * DAO Implementation for User entity
 * 
 * @author Joerg Huber, Lukas Kranabetter
 * */
@Stateless
public class UserServiceImpl implements UserService {

	@Inject
	private Logger _log;

	@Inject
	@Named("userDAOImpl")
	private UserDAO userDAO;

	@Override
	public User proveUserPassswordCombination(String userName, String password) {

		_log.log(Level.INFO, "prove password for [" + userName + "] pw ["
				+ password + "]!");

		// input validation
		if (userName == null)
			throw new IllegalArgumentException("username can not be null");
		if (password == null)
			throw new IllegalArgumentException("password can not be null");

		// find user by name
		User user= getUserByName(userName);

		// check password, if user is not null
		if (user != null && user.getPassword().equals(password)) {
			return user;
		}

		return null;
	}

	@Override
	@PermitAll()
	public String registerUser(String userName, String password) {
		
		_log.log(Level.INFO, "register user [" + userName + "] pw ["
				+ password + "]!");

		// input validation
		if (userName == null)
			throw new IllegalArgumentException("userName can not be null");
		if (password == null)
			throw new IllegalArgumentException("password can not be null");

		// check if the user password combination is correct
		User user = proveUserPassswordCombination(userName, password);

		// throw exception if registration failed
		if (user == null) {
			throw new UserLoginException("Login failed.");
		}

		// generate a new token
		String token = getNewToken();

		// store token to user
		user.setToken(token);

		// persist the user
		userDAO.insert(user);

		// return the created token (store this token to cookies)
		return token;
	}

	/**
	 * Register a new user, permit for all.
	 * 
	 * @param user
	 */
	@Override
	@PermitAll()
	public void registerUser(User user) {
		_log.info("Register a user with form authetication");

		// input validation
		if (user == null)
			throw new IllegalArgumentException("user can not be null");

		userDAO.insert(user);
	}
	
	private User getUserByName(String userName)
	{
		// find user by name
		User user;
		try {
			user = userDAO.findByName(userName);
		} catch (Exception e) {
			String msg = "UserDAO Exception from methode findByName";
			_log.log(Level.SEVERE, msg + e);
			throw new ServiceLayerException("Data Access Error", e);
		}
		return user;
	}

	private String getNewToken() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}

	@Override
	@PermitAll()
	public User getRegisteredUser(String token) {

		_log.log(Level.INFO, "get registerd user by token [" + token + "]");

		// check if token is valid
		if (token == null || token.length() < 5)
			return null;

		return userDAO.findByToken(token);
	}

	@Override
	@PermitAll()
	public void insertUser(User user) {
		// hotfix set empty HashedPassword (to avoid null entity manger
		// exception)
		if (user.getHashedPassword() == null)
			user.setHashedPassword("");
		userDAO.insert(user);
	}

	@Override
	@PermitAll()
	public boolean UserIsAdmin(User user) {
		return user.getUsername().endsWith("_a");
	}

	@Override
	@PermitAll()
	public boolean UserIsPortalAdmin(User user) {
		return user.getUsername().endsWith("_pa");
	}

	@Override
	@PermitAll()
	public void loggoutUser(String userName) {
		
		_log.log(Level.INFO, "loggoutUser [" + userName + "]");

		// find user by name
		User user= getUserByName(userName);

		// throw exception if the user was not found
		if (user == null) {
			throw new RuntimeException("could not find user in database");
		}

		// set token initial
		user.setToken("");

		// persist user
		userDAO.update(user);
	}

	@Override
	@PermitAll()
	public User updateUser(User user) {
		
		_log.log(Level.INFO, "update user [" + user + "]");

		// input validation
		if (user == null)
			throw new IllegalArgumentException("user is null.");

		return userDAO.update(user);
	}

	@Override
	@PermitAll()
	public User getUserById(long id) {

		_log.log(Level.INFO, "get user by id [" + id + "]");

		return userDAO.findById(id);
	}

	@Override
	@PermitAll()
	public User getUserByUsername(String username) {

		_log.log(Level.INFO, "get user by username [" + username + "]");

		// input validation
		if (username == null)
			throw new IllegalArgumentException("username can not be null");

		return userDAO.findByName(username);
	}
}