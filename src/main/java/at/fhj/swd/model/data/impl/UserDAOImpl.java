package at.fhj.swd.model.data.impl;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	public Boolean proveUserPassswordCombination(String userName,
			String password) {

		List<User> users = em
				.createQuery("from User user where user.id=:username",
						User.class).setParameter("username", userName)
				.setParameter("password", password).getResultList();

		if (users.size() == 1) {
			return true;
		} else if (users.size() == 0) {
			return false;
		}
		throw new RuntimeException("inavlid database state in user table");
	}

	@Override
	public void insert(User user) {
		em.persist(user);
	}

}
