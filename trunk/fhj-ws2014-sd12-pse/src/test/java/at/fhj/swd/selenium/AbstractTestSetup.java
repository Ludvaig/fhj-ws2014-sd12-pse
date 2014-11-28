package at.fhj.swd.selenium;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import at.fhj.swd.selenium.pageobjects.LoginPage;
import at.fhj.swd.selenium.pageobjects.TmpPage;

public class AbstractTestSetup {

	WebDriver driver;   
	LoginPage loginPage;
	TmpPage tmpPage;
	
	@Before
	public void setup(){
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://127.0.0.1:8080/fhj-ws2014-sd12-pse/login.jsf");
		
		loginPage = new LoginPage(driver);
		tmpPage = loginPage.loginUser();
	}
	
	@After
	public void teardown(){
		driver.quit();
	}
}
