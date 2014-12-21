package at.fhj.swd.service.user;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import at.fhj.swd.data.UserDAO;
import at.fhj.swd.data.entity.User;

@Stateless
@Remote(UserDAO.class)
public class UserDAOTestFacade implements UserDAO {

	@EJB(beanName="UserDAOImpl")
	private UserDAO dao;
	
	@Override
	public User proveUserPassswordCombination(String username, String password) {
		return dao.proveUserPassswordCombination(username, password);
	}

	@Override
	public void persist(User user) {
		dao.persist(user);
	}

	@Override
	public User loadUserByName(String username) {
		return dao.loadUserByName(username);
	}

	@Override
	public User loadUserById(long id) {
		return dao.loadUserById(id);
	}

	@Override
	public User loadUserByToken(String token) {
		return dao.loadUserByToken(token);
	}

	@Override
	public User updateUser(User user) {
		return dao.updateUser(user);
	}
}
