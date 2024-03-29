package at.fhj.swd.data.impl;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import at.fhj.swd.data.UserDAO;
import at.fhj.swd.data.entity.User;
import at.fhj.swd.data.exceptions.DataSourceLayerException;

import javax.inject.Named;

/**
 * DAO Implementation for User entity
 * 
 * @author Jörg Huber
 * */

@Named
public class UserDAOImpl implements UserDAO {

	@Inject
	public EntityManager em;

	@Override
	public User proveUserPassswordCombination(String username, String password) {
		User user = findByName(username);

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
	public User findByName(String username) {
		List<User> users = em
				.createQuery("from User user where user.username=:username",
						User.class).setParameter("username", username)
				.getResultList();

		return getUserFromList(users);
	}

	@Override
	public User findById(long id) {
		List<User> users = em
				.createQuery("from User user where user.id=:id", User.class)
				.setParameter("id", id).getResultList();

		return getUserFromList(users);
	}

	@Override
	public User findByToken(String token) {
		List<User> users = em
				.createQuery("from User user where user.token=:token",
						User.class).setParameter("token", token)
				.getResultList();

		return getUserFromList(users);
	}

	private User getUserFromList(List<User> users) {
		if (users.size() == 1) {
			return users.get(0);
		} else if (users.size() == 0) {
			return null;
		}
		throw new DataSourceLayerException("inavlid database state in user table");
	}

	@Override
	public User update(User user) {
		if (user != null)
			return em.merge(user);
		else
			return null;
	}
}
