package at.fhj.swd.service.mock;

import javax.enterprise.inject.Alternative;

import at.fhj.swd.data.UserDAO;
import at.fhj.swd.data.entity.User;

@Alternative
public class UserDAOMock implements UserDAO {

	public User user;
	public RuntimeException insertException;
	public RuntimeException findByNameException;
	public RuntimeException findByIdException;
	public RuntimeException updateException;	
	@Override
	public User proveUserPassswordCombination(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(User user) {
		if(insertException != null)
			throw insertException;
		this.user = user;
		
	}

	@Override
	public User findByName(String username) {
		if(findByNameException != null)
			throw findByNameException;
		return user;
	}

	@Override
	public User findById(long id) {
		if(findByIdException != null)
			throw findByIdException;
		return user;
	}

	@Override
	public User findByToken(String token) {
		// TODO Auto-generated method stub
		return user;
	}

	@Override
	public User update(User user) {
		if(updateException != null)
			throw updateException;
		
		return user;
	}

}
