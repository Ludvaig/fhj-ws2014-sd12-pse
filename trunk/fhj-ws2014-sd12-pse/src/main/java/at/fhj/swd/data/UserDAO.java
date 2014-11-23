package at.fhj.swd.data;

import at.fhj.swd.data.entity.User;

public interface UserDAO {

	
		User proveUserPassswordCombination(String username, String password);
	
		void persist(User user);
		User loadUserByName(String username);
		
		
		User loadUserById(long id); 
		User loadUserByToken(String token); 
		User updateUser(User user);
		


	}


