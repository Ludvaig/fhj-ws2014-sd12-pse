package at.fhj.swd.data;

import at.fhj.swd.data.entity.User;

public interface UserDAO {
		User proveUserPassswordCombination(String username, String password);
		User loadUserByName(String username);
		User loadUserById(long id); 
		User loadUserByToken(String token); 
		User update(User user);
		void insert(User user);
}