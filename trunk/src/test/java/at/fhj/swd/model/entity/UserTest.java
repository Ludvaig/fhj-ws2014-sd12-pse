package at.fhj.swd.model.entity;

import at.fhj.swd.model.entity.User;
import junit.framework.TestCase;

public class UserTest extends TestCase {

	public void testEqual_success() {

		User user = new User();

	   // Tests
	   assertEquals(user, user);
	 } 
	
	public void testEqualNull_fail() {
	   // Tests
	   assertEquals(false, new User().equals(null));
	 } 
	
	public void testEqualWrongClass_fail() {
	   // Tests
	   assertEquals(false, new User().equals(new String()));
	} 
	
	public void testEqualId_success() {
		User userOne = new User();
		User userTwo = new User();
		
		userOne.setId((long) 1);
		userTwo.setId((long) 1);
		
	   // Tests
	   assertEquals(true, userOne.equals(userTwo));
	} 
	
	public void testEqualWrongId_fail() {
		User userOne = new User();
		User userTwo = new User();
		
		userOne.setId((long) 1);
		userTwo.setId((long) 2);
		
	   // Tests
	   assertEquals(false, userOne.equals(userTwo));
	} 
	
	public void testEqualullId_fail() {
		User userOne = new User();
		User userTwo = new User();
		
		
	   // Tests
	   assertEquals(false, userOne.equals(userTwo));
	} 
	
	public void testHashCodeSameId_succes() {
		User userOne = new User();
		User userTwo = new User();
		
		userOne.setId((long) 1);
		userTwo.setId((long) 1);
		
	   // Tests
	   assertEquals(userOne.hashCode(), userTwo.hashCode());
	} 
	
	public void testHashCodeDiffrentId_fail() {
		User userOne = new User();
		User userTwo = new User();
		
		userOne.setId((long) 1);
		userTwo.setId((long) 2);
		
	   // Tests
		assertFalse(userOne.hashCode() == userTwo.hashCode());
	} 
	
	public void testHashCodeNullId_fail() {
		User userOne = new User();
		User userTwo = new User();
			
	   // Tests
		assertFalse(userOne.hashCode() == userTwo.hashCode());
	} 

	public void testTelephoneNumber_succes(){
		User userOne = new User();
		  
		String telephoneNumber = "+43316123";
		  
		userOne.setTelephone(telephoneNumber);
		  
		assertEquals(userOne.getTelephone(), telephoneNumber);
	}
		 
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
