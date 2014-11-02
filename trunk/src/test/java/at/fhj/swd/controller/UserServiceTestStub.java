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

}