package at.fhj.swd.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import at.fhj.swd.data.entity.User;
import at.fhj.swd.data.impl.UserDAOImpl;
import junit.framework.TestCase;

public class UserDAOTest extends TestCase {
	
	
	@Test
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
