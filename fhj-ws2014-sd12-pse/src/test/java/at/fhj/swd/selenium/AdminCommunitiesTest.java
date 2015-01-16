package at.fhj.swd.selenium;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.text.SimpleDateFormat;
import java.util.Date;
import at.fhj.swd.selenium.pageobjects.AdminPage;

/**
 * AdminCommunities-Tests
 * @author Daniel Frech
 */
public class AdminCommunitiesTest extends AbstractTestSetup{

	
	private AdminPage adminPage;
	private String communityName;
	
	@Before
	public void setup(){
		super.setup();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyddMMHHmmss");
		Date now = new Date(); 

		String communityName = "SeleniumTest_Community_" + df.format(now);		
	}
	
	@Test
	public void testCreateNewCommunity() {

	loginAdmin();
	adminPage = indexPage.goToAdminPage();
	adminPage = indexPage.goToAdminCommunitiesPage();
	adminPage = adminPage.createCommunity(communityName);
	
	assertTrue(driver.getPageSource().contains(communityName));
	
	}
	
	@After
	public void teardown(){
		
		loginAdmin();
		adminPage = indexPage.goToAdminPage();
		adminPage = indexPage.goToAdminNewsPage();
		
		driver.quit();
	}
}
