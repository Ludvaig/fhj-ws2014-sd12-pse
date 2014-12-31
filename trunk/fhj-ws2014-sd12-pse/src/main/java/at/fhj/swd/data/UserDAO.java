package at.fhj.swd.data;

import at.fhj.swd.data.entity.User;

public interface UserDAO {
	User update(User user);
	void insert(User user);
	User findByName(String username);
	User findById(long id);
	User findByToken(String token);
}