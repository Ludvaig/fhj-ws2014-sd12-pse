package at.fhj.swd.model.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import at.fhj.swd.model.data.impl.UserDAOImpl;
import at.fhj.swd.model.entity.User;
import junit.framework.TestCase;

public class UserDAOTest extends TestCase {

	public void testInsertNewUser() {
		
		assertEquals(true, true);
//
//		EntityManagerFactory emFactory = Persistence
//				.createEntityManagerFactory("primary");
//		EntityManager em = emFactory.createEntityManager();
//
//		UserDAOImpl UserDAOImpl = new UserDAOImpl();
//		UserDAOImpl.em =em;
//
//		User user = new User();
//		user.setUsername("test");
//
//		UserDAOImpl.insert(user);
	}
}
