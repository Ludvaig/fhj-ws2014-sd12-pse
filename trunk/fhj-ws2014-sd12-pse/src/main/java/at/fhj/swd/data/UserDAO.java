package at.fhj.swd.data;

import at.fhj.swd.data.entity.User;

public interface UserDAO {
		User proveUserPassswordCombination(String username, String password);
		void insert(User user);
		User findByName(String username);
		User findById(long id); 
		User findByToken(String token); 
		User update(User user);
	}