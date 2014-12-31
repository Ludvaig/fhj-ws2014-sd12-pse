package at.fhj.swd.data.impl;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import at.fhj.swd.data.UserDAO;
import at.fhj.swd.data.entity.User;

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
	

	@Override
	public User loadUserById(long id) {
		List<User> users = em
				.createQuery("from User user where user.id=:id",
						User.class).setParameter("id", id)
				.getResultList();

		if (users.size() == 1) {
			return users.get(0);
		} else if (users.size() == 0) {
			return null;
		}
		throw new RuntimeException("inavlid database state in user table");
	}



	@Override
	public User loadUserByToken(String token) {
		List<User> users = em
				.createQuery("from User user where user.token=:token",
						User.class).setParameter("token", token)
				.getResultList();

		if (users.size() == 1) {
			return users.get(0);
		} else if (users.size() == 0) {
			return null;
		}
		throw new RuntimeException("inavlid database state in user table");
	}

	@Override
	public User update(User user)
	{
		if (user != null)
			return em.merge(user);
		else
			return null;
	}


}
