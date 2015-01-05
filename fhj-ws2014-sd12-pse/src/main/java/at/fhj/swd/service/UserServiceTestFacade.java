package at.fhj.swd.service;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import at.fhj.swd.data.entity.User;

@Stateless
@Remote(UserService.class)
public class UserServiceTestFacade implements UserService{
	
	@EJB(beanName="UserServiceImpl")
	private UserService service;

	@Override
	public String registerUser(String userName, String password) {
		
		return service.registerUser(userName, password);
	}

	@Override
	public void registerUser(User user) {
		service.registerUser(user);		
	}

	@Override
	public User getRegisteredUser(String token) {
		return service.getRegisteredUser(token);
	}

	@Override
	public void insertUser(User user) {
		service.insertUser(user);		
	}

	@Override
	public boolean UserIsAdmin(User user) {
		return service.UserIsAdmin(user);
	}

	@Override
	public boolean UserIsPortalAdmin(User user) {
		return service.UserIsPortalAdmin(user);
	}

	@Override
	public void loggoutUser(String userName) {
		service.loggoutUser(userName);
	}

	@Override
	public User updateUser(User user) {
		return service.updateUser(user);
	}

	@Override
	public User getUserById(long id) {
		return service.getUserById(id);
	}

	@Override
	public User getUserByUsername(String username) {
		return service.getUserByUsername(username);
	}

	@Override
	public User proveUserPassswordCombination(String username, String password) {
		return service.proveUserPassswordCombination(username, password);
	}

}
