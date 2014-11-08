package at.fhj.swd.model.service.Impl;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.logging.Logger;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import at.fhj.swd.model.data.UserDAO;
import at.fhj.swd.model.entity.User;
import at.fhj.swd.model.service.UserService;

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
	
	@PersistenceContext()
  private EntityManager em;
  
	/*
  @EJB
  IEmailService emailService;
  */

	@Override
	@PermitAll()
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
		
		em.persist(user);
		
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

	private String GetNewToken() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}

	@Override
	@PermitAll()
	public User getRegisteredUser(String token) {
		
		//check if token is valid
		if(token == null || token.length()< 5)
			return null;
		
		return userDAO.loadUserByToken(token);
	}

	@Override
	@PermitAll()
	public void insertUser(User user) {
		//hotfix set empty HashedPassword (to avoid null entity manger exception)
		if(user.getHashedPassword() == null)
			user.setHashedPassword("");		
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

	@Override
	public User updateUser(User user)
	{
		if (user == null)
			throw new IllegalArgumentException("User is null.");
		
		return userDAO.updateUser(user);
	}
	
	@Override
	public User getUserById(long id) {
		
		return userDAO.loadUserById(id);
		
	}
	
	@Override
	public User getUserByUsername(String username){
	
		return userDAO.loadUserByName(username);
	}


}
