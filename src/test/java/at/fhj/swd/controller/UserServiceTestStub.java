package at.fhj.swd.controller;

import at.fhj.swd.model.entity.User;
import at.fhj.swd.model.service.UserService;

public class UserServiceTestStub implements UserService {
	
	protected User user;
	private final static String authToken = "authToken";

	@Override
	public String registerUser(String userName, String password) {
		
		user = new User();
		user.setUsername(userName);
		user.setPassword(password);
		return authToken;
	}



	@Override
	public void insertUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean UserIsAdmin(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean UserIsPortalAdmin(User user) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public User getRegisteredUser(String token) {
		return user;
	}



	@Override
	public void loggoutUser(String userName) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public User updateUser(User user)
	{
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public User getUserById(long id)
	{
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public User getUserByUsername(String username)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
