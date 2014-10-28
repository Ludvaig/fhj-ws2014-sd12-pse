package at.fhj.swd.controller;

import at.fhj.swd.model.entity.User;
import at.fhj.swd.model.service.UserService;

public class UserServiceTestStup implements UserService {
	
	protected User user;

	@Override
	public void registerUser(String userName, String password) {
		
		user = new User();
		user.setUsername(userName);
		user.setPassword(password);
	}

	@Override
	public User getRegisteredUser() {
	
		return user;
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

}
