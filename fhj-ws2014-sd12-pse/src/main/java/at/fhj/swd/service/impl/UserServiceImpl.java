package at.fhj.swd.service.impl;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.logging.Logger;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.inject.Inject;

import at.fhj.swd.data.UserDAO;
import at.fhj.swd.data.entity.User;
import at.fhj.swd.service.UserService;

/**
 * DAO Implementation for User entity
 * 
 * @author Jörg Huber, Lukas Kranabetter
 * */
@Stateless
public class UserServiceImpl implements UserService {

	@Inject
	private Logger _log;
	
	@Inject
	private UserDAO userDAO;
  
	/*
  @EJB
  IEmailService emailService;
  */

	@Override
	public User proveUserPassswordCombination(String username, String password) {
		User user = userDAO.findByName(username);
		
		// check password, if user is not null
		if (user != null) {
			if (user.getPassword().equals(password)) {
				return user;
			}
		}

		return null;
	}
	
	@Override
	@PermitAll()
	public String registerUser(String username, String password) {

		// prove if input data are set
		if (username == null)
			throw new NullPointerException("username can not be null");
		if (password == null)
			throw new NullPointerException("password can not be null");

		// check if the user password combination is correct
		User user = proveUserPassswordCombination(username, password);

		// throw exception if registration failed
		if (user == null) {
			throw new RuntimeException("not registerd");
		}

		// Generate a new token
		String token = getNewToken();

		// store token to user
		user.setToken(token);

		// persist the user
		userDAO.insert(user);

		//return the created token (store this token to cookies)
		return token;
	}
	
	/**
	 * Register a new user, permit for all.
	 * 
	 * @param user
	 */
	@Override
	@PermitAll()
	public void registerUser(User user)
	{
		_log.info("Register a user with form authetication");
		
		userDAO.insert(user);
		
		//if(isUsernameExist(user.getUsername())) throw new RuntimeException("This username already exists");
		//if(user.getEmail() == null) throw new RuntimeException("Invalid email address");
		//if(isEmailOverRegistered(user.getEmail())) throw new RuntimeException("One Email can register at most 3 accounts");
 
		//user.setActivated(false);

  
		// TODO: Implement email account activation
		// Example code
		/*
		ActivateCode activateCode = new ActivateCode();
		activateCode.setUser(user);
		activateCode.setCode(RandomHelper.getRandomString(ActivateCode.CODE_LENGTH));
		activateCode.setUsageType(ActivateCode.Usage.USER_ACTIVATE_ACCOUNT.id);
		em.persist(activateCode);
  
	  Email email = new Email();
	  email.setTitle(EmailConstants.USER_REGISTER_ACTIVATION_EMAIL_TITLE);
	  email.setContent(EmailConstants.getUserRegisterActivatioinEmailContent(user.getUsername(), activateCode.getActivatingLink()));
	  email.setToUser(user);
	  em.persist(email);
	  emailService.sendMail(email);
	  */
	}

	private String getNewToken() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}

	@Override
	@PermitAll()
	public User getRegisteredUser(String token) {
		
		//check if token is valid
		if(token == null || token.length()< 5)
			return null;
		
		return userDAO.findByToken(token);
	}

	@Override
	@PermitAll()
	public void insertUser(User user) {
		//hotfix set empty HashedPassword (to avoid null entity manger exception)
		if(user.getHashedPassword() == null)
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
		
		// load user by user name
		User user = userDAO.findByName(userName);

		// throw exception if the user was not found
		if (user == null) {
			throw new RuntimeException("could not find user in database");
		}
		
		//set token initial
		user.setToken("");
		
		//persist user
		userDAO.update(user);
	}

	@Override
	@PermitAll()
	public User updateUser(User user)
	{
		if (user == null)
			throw new IllegalArgumentException("User is null.");
		
		return userDAO.update(user);
	}
	
	@Override
	@PermitAll()
	public User getUserById(long id) {
		return userDAO.findById(id);
	}
	
	@Override
	@PermitAll()
	public User getUserByUsername(String username){
		return userDAO.findByName(username);
	}
}