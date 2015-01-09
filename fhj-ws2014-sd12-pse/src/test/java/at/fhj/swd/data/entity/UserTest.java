package at.fhj.swd.data.entity;

import org.junit.Before;
import org.junit.Test;

import at.fhj.swd.data.entity.User;
import at.fhj.swd.service.impl.UserServiceImpl;
import junit.framework.TestCase;

public class UserTest extends TestCase {
	
	
	User user;
	
	@Before
	public void setUp() {
			
		user = new User();
	}

	@Test
	public void testEqual_success() {
	   // Tests
	   assertEquals(user, user);
	 } 
	
	@Test
	public void testEqualNull_fail() {
	   // Tests
	   assertNull(new User());
	 } 
	
	@Test
	public void testEqualWrongClass_fail() {
	   // Tests
		Object obj = new Object();
		assertEquals(false, new User().equals(obj));
	} 
	
	@Test
	public void testEqualId_success() {
		User userOne = new User();
		User userTwo = new User();
		
		userOne.setId((long) 1);
		userTwo.setId((long) 1);
		
	   // Tests
	   assertEquals(true, userOne.equals(userTwo));
	} 
	
	@Test
	public void testEqualWrongId_fail() {
		User userOne = new User();
		User userTwo = new User();
		
		userOne.setId((long) 1);
		userTwo.setId((long) 2);
		
	   // Tests
	   assertEquals(false, userOne.equals(userTwo));
	} 
	
	@Test
	public void testEqualullId_fail() {
		User userOne = new User();
		User userTwo = new User();
		
		
	   // Tests
	   assertEquals(false, userOne.equals(userTwo));
	} 
	
	@Test
	public void testHashCodeSameId_succes() {
		User userOne = new User();
		User userTwo = new User();
		
		userOne.setId((long) 1);
		userTwo.setId((long) 1);
		
	   // Tests
	   assertEquals(userOne.hashCode(), userTwo.hashCode());
	} 
	
	@Test
	public void testHashCodeDiffrentId_fail() {
		User userOne = new User();
		User userTwo = new User();
		
		userOne.setId((long) 1);
		userTwo.setId((long) 2);
		
	   // Tests
		assertFalse(userOne.hashCode() == userTwo.hashCode());
	} 
	
	@Test
	public void testHashCodeNullId_fail() {
		User userOne = new User();
		User userTwo = new User();
			
	   // Tests
		assertFalse(userOne.hashCode() == userTwo.hashCode());
	} 

	@Test
	public void testTelephoneNumber_succes(){
		User userOne = new User();
		  
		String telephoneNumber = "+43316123";
		  
		userOne.setTelephone(telephoneNumber);
		  
		assertEquals(userOne.getTelephone(), telephoneNumber);
	}
	
	@Test
	public void testTelephoneNumber_fail(){
		try {
			User userOne = new User();
		   
			String telephoneNumber = "ASDFE111";
			userOne.setTelephone(telephoneNumber);

			fail( "Missing exception" );
		} catch( IllegalArgumentException e ) {
		   
		  assertEquals( "Invalid telephone number", e.getMessage() ); 
		  }
		}
}
