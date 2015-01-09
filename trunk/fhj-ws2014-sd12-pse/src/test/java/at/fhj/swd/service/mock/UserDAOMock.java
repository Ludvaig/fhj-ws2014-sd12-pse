package at.fhj.swd.service.mock;

import javax.enterprise.inject.Alternative;

import at.fhj.swd.data.UserDAO;
import at.fhj.swd.data.entity.User;

@Alternative
public class UserDAOMock implements UserDAO {

	
	
	
	@Override
	public User proveUserPassswordCombination(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User findByName(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
