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

/**
 * DAO Implementation for User entity
 * 
 * @author Joerg Huber, Lukas Kranabetter
 * */
@Stateless
public class UserServiceImpl implements UserService {

	@Inject
	private Logger log;

	@Inject
	@Named("userDAOImpl")
	private UserDAO userDAO;

	@Override
	public User proveUserPassswordCombination(String userName, String password) {

		log.log(Level.INFO, "prove password for [" + userName + "] pw ["
				+ password + "]!");

		// input validation
		if (userName == null)
			throw new IllegalArgumentException("username can not be null");
		if (password == null)
			throw new IllegalArgumentException("password can not be null");

		// find user by name
		User user = null;
		try {
			user = userDAO.findByName(userName);
		} catch (Exception e) {
			throw getDataAccessException("findByName", e);
		}

		// check password, if user is not null
		if (user != null && user.getPassword().equals(password)) {
			return user;
		}

		return null;
	}

	@Override
	@PermitAll()
	public String registerUser(String userName, String password) {

		log.log(Level.INFO, "register user [" + userName + "] pw [" + password
				+ "]!");

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
		try {
			userDAO.insert(user);
		} catch (Exception e) {
			throw getDataAccessException("update", e);
		}

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
		log.info("Register a user with form authetication");

		// input validation
		if (user == null)
			throw new IllegalArgumentException("user can not be null");

		try {
			userDAO.insert(user);
		} catch (Exception e) {
			throw getDataAccessException("update", e);
		}
	}

	private String getNewToken() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}

	@Override
	@PermitAll()
	public User getRegisteredUser(String token) {

		log.log(Level.INFO, "get registerd user by token [" + token + "]");

		// check if token is valid
		if (token == null || token.length() < 5)
			return null;

		return userDAO.findByToken(token);
	}

	@Override
	@PermitAll()
	public boolean userIsAdmin(User user) {
		log.log(Level.INFO, "check if user is  admin [" + user + "]");

		// input validation
		if (user == null)
			throw new IllegalArgumentException("user can not be null");

		return user.getUsername().endsWith("_a");
	}

	@Override
	@PermitAll()
	public boolean userIsPortalAdmin(User user) {

		log.log(Level.INFO, "check if user is portal admin [" + user + "]");

		// input validation
		if (user == null)
			throw new IllegalArgumentException("user can not be null");

		return user.getUsername().endsWith("_pa");
	}

	@Override
	@PermitAll()
	public void loggoutUser(String userName) {

		log.log(Level.INFO, "loggoutUser [" + userName + "]");

		// find user by name
		User user = null;
		try {
			user = userDAO.findByName(userName);
		} catch (Exception e) {
			throw getDataAccessException("findByName", e);
		}

		// throw exception if the user was not found
		if (user == null) {
			throw new ServiceLayerException("could not find user in database");
		}

		// set token initial
		user.setToken("");

		// persist user
		userDAO.update(user);
	}

	@Override
	@PermitAll()
	public User updateUser(User user) {

		log.log(Level.INFO, "update user [" + user + "]");

		// input validation
		if (user == null)
			throw new IllegalArgumentException("user is null.");

		try {
			return userDAO.update(user);
		} catch (Exception e) {
			throw getDataAccessException("update", e);
		}
	}

	@Override
	@PermitAll()
	public User getUserById(long id) {

		log.log(Level.INFO, "get user by id [" + id + "]");

		try {
			return userDAO.findById(id);
		} catch (Exception e) {
			throw getDataAccessException("findById", e);
		}
	}

	@Override
	@PermitAll()
	public User getUserByUsername(String userName) {

		log.log(Level.INFO, "get user by username [" + userName + "]");

		// input validation
		if (userName == null)
			throw new IllegalArgumentException("username can not be null");

		try {
			return userDAO.findByName(userName);
		} catch (Exception e) {
			throw getDataAccessException("findByName", e);
		}
	}

	// create ServiceLayerException from DAO Exception
	private RuntimeException getDataAccessException(String methodeName,
			Exception e) {
		String msg = "UserDAO Exception from methode " + methodeName;
		log.log(Level.SEVERE, msg + e);
		return new ServiceLayerException("Data Access Error", e);
	}
}