package at.fhj.swd.model.data.impl;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import at.fhj.swd.model.data.UserDAO;
import at.fhj.swd.model.entity.User;

/**
 * DAO Implementation for User entity
 * 
 * @author Jörg Huber
 * */

public class UserDAOImpl implements UserDAO {

	@Inject
	public EntityManager em;

	@Override
	public User proveUserPassswordCombination(String username, String password) {
		User user = loadUserByName(username);

		// check password, if user is not null
		if (user != null) {
			if (user.getPassword().equals(password)) {
				return user;
			}
		}

		return null;
	}

	@Override
	public void insert(User user) {
		em.persist(user);
	}

	@Override
	public User loadUserByName(String username) {
		List<User> users = em
				.createQuery("from User user where user.username=:username",
						User.class).setParameter("username", username)
				.getResultList();

		if (users.size() == 1) {
			return users.get(0);
		} else if (users.size() == 0) {
			return null;
		}
		throw new RuntimeException("inavlid database state in user table");
	}


}
