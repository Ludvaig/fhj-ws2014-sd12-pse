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
	public void insert(User user) {
		dao.insert(user);
	}

	@Override
	public User findByName(String username) {
		return dao.findByName(username);
	}

	@Override
	public User findById(long id) {
		return dao.findById(id);
	}

	@Override
	public User findByToken(String token) {
		return dao.findByToken(token);
	}

	@Override
	public User update(User user) {
		return dao.update(user);
	}
}
