package at.fhj.swd.selenium;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import at.fhj.swd.selenium.pageobjects.LoginPage;
import at.fhj.swd.selenium.pageobjects.TmpPage;


public class LoginPageTest {
	WebDriver driver;   
	LoginPage loginPage;
	TmpPage tmpPage;
	
	@Before
	public void setup(){
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://127.0.0.1:8080/fhj-ws2014-sd12-pse/login.jsf");
		loginPage = new LoginPage(driver);
	}
	
	@After
	public void teardown(){
		driver.quit();
	}
	
	@Test(expected=TimeoutException.class)
	public void testLoginUserDefect(){
		loginPage.loginUser("wronguser", "wrongpassword");		
	}

	@Test
	public void testLoginUserValid(){
		loginPage.loginUser("Herbert", "vergessen");
		assertFalse(driver.getTitle().contains("Login"));		
	}
}
