package at.fhj.swd.selenium;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import at.fhj.swd.selenium.pageobjects.LoginPage;
import at.fhj.swd.selenium.pageobjects.TmpPage;

public class AbstractTestSetup {

	WebDriver driver;   
	LoginPage loginPage;
	TmpPage tmpPage;
	
	@Before
	public void setup(){
		driver = new FirefoxDriver(createProfileForAutoTextFileToTempDirDownload());
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	protected void loginAdmin() {
		driver.get("http://127.0.0.1:8080/fhj-ws2014-sd12-pse/login.jsf");
		loginPage = new LoginPage(driver);
		tmpPage = loginPage.loginAdmin();
	}
	
	protected void loginAdminPa() {
		driver.get("http://127.0.0.1:8080/fhj-ws2014-sd12-pse/login.jsf");
		loginPage = new LoginPage(driver);
		tmpPage = loginPage.loginAdminPa();
	}
	
	protected void loginUser() {
		driver.get("http://127.0.0.1:8080/fhj-ws2014-sd12-pse/login.jsf");
		loginPage = new LoginPage(driver);
		tmpPage = loginPage.loginUser();
	}
	
	protected FirefoxProfile createProfileForAutoTextFileToTempDirDownload() {
		String tmpDir = System.getProperty("java.io.tmpdir");
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("browser.download.folderList", 2);
		profile.setPreference("browser.helperApps.alwaysAsk.force", false);
		profile.setPreference("browser.download.manager.showWhenStarting",false);
		profile.setPreference("browser.download.dir", tmpDir);
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/plain");
		return profile;
	}
		
	@After
	public void teardown(){
		driver.quit();
	}
}
