package at.fhj.swd.controller.user;

import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import at.fhj.swd.GlobalConstants;
import at.fhj.swd.model.entity.User;
import at.fhj.swd.model.service.UserService;
import at.fhj.swd.util.HashUtil;

/**
 * Managed bean as controller for user registration.<br> 
 * 
 * @author Lukas Kranabetter
 */
@ManagedBean(name = "registerController")
@RequestScoped
public class RegisterController 
{
	@Inject
	private Logger _log;
	
	@Inject
	private UserService _userService;
	
	private String _username;
	private String _password;
	private String _passwordRepeat;
	private String _firstname = "";
	private String _lastname = "";
	private String _mobilePhone = "";
	private String _email ="";
	private String _birthday = null;
	//private Gender gender = Gender.UNKNOWN;
	//private String imageLink = "unknown.jpg";//头像链接
	
	public String create()
	{
		//TODO: Check if input valid!
	  
	  // Build and persist entity beans
	  User user = new User();
	  
	  user.setUsername(this.getUsername().trim());
	  user.setHashedPassword(HashUtil.getPasswordHash(this.getUsername().trim(), this.getPassword().trim()));
	  user.setEmail(this.getEmail().trim());
	  
	  // TODO: Add optional parameters
	  /*
	  if(this.getBirthday() != null && this.getBirthday().trim().length() > 0)
	  	user.setBirthday(DateHelper.stringToUtilDate(this.getBirthday().trim()));
	  user.setFirstname(this.getFirstname());
	  user.setLastname(this.getLastname());
	  user.setMobilePhone(this.getMobilePhone());
	  user.setGender(this.getGender());
	  */
	  
	  // Store user to database.
	  try
	  {
	  	_userService.registerUser(user);
	  }
	  catch(Exception e)
	  {
	  	_log.severe(e.getMessage());
	    
	  	return "failure"; 
	  }
	  
	  return "success"; 
	}
	
	// Getter and setter
	
	public String getUsername() 
	{
		return _username;
	}
	
	public void setUsername(String username) 
	{
    _username = username;
	}
	
	public String getPassword()
	{
		return _password;
	}

	public void setPassword(String password)
	{
		_password = password;
	}

	public String getPasswordRepeat()
	{
		return _passwordRepeat;
	}

	public void setPasswordRepeat(String passwordRepeat)
	{
		_passwordRepeat = passwordRepeat;
	}

	public String getFirstname()
	{
		return _firstname;
	}

	public void setFirstname(String firstname)
	{
		_firstname = firstname;
	}

	public String getLastname()
	{
		return _lastname;
	}

	public void setLastname(String lastname)
	{
		_lastname = lastname;
	}

	public String getMobilePhone()
	{
		return _mobilePhone;
	}

	public void setMobilePhone(String mobilePhone)
	{
		_mobilePhone = mobilePhone;
	}

	public String getEmail()
	{
		return _email;
	}

	public void setEmail(String email)
	{
		_email = email;
	}

	public String getBirthday()
	{
		return _birthday;
	}

	public void setBirthday(String birthday)
	{
		_birthday = birthday;
	}
	
	public String getMaxUsernameLength()
	{
		return GlobalConstants.MAX_USERNAME_LENGTH;
	}
	
	public String getMinUsernameLength()
	{
		return GlobalConstants.MIN_USERNAME_LENGTH;
	}
	
	public String getMaxPasswordLength()
	{
		return GlobalConstants.MAX_PASSWORD_LENGTH;
	}
	
	public String getMinPasswordLength()
	{
		return GlobalConstants.MIN_PASSWORD_LENGTH;
	}
}