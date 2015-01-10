package at.fhj.swd.selenium;

import static org.junit.Assert.assertFalse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import at.fhj.swd.selenium.pageobjects.UserPage;


public class UserPageTest extends AbstractTestSetup{

	
	private UserPage userPage;
	
	@Before
	public void setup(){
		super.setup();
		loginUser();
		userPage = tmpPage.goToUserPage();
	}
	
	@Test
	public void testOpenUserPage(){
		String expected = "User profile";
		Assert.assertEquals(expected, userPage.GetTextOfxPathElement(".//*[@id='userForm']/h1"));
	}
	
	@Test
	public void testChangePhonenumberDefect() throws InterruptedException{
		String expectedPhoneNumber = "hallo123";			
		userPage.changePhoneNumber(expectedPhoneNumber);
		
		String inputTextElementVal = driver.findElement(By.id("userForm:phoneNumber")).getAttribute("value");
		assertFalse(inputTextElementVal.contains(expectedPhoneNumber));
	}
	
	@Test
	public void testChangePhonenumberValid() throws InterruptedException{
		String expectedPhoneNumber = "01234567";
		userPage.changePhoneNumber(expectedPhoneNumber);
		
		String inputTextElementVal = driver.findElement(By.id("userForm:phoneNumber")).getAttribute("value");
		Assert.assertEquals(expectedPhoneNumber, inputTextElementVal);
	}
	
}
