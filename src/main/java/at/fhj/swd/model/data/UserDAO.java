package at.fhj.swd.model.data;

import at.fhj.swd.model.entity.User;

public interface UserDAO {

	
		User proveUserPassswordCombination(String username, String password);
	
		void persist(User user);
		User loadUserByName(String username);
		
		
		User loadUserById(long id); 
		User loadUserByToken(String token); 
		User updateUser(User user);
		


	}


