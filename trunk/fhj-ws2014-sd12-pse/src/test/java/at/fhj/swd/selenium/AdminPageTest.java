package at.fhj.swd.selenium;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import at.fhj.swd.selenium.pageobjects.AdminPage;

/**
 * AdminPage-Tests
 * @author D.Hoesele
 */
public class AdminPageTest extends AbstractTestSetup{

	
	private AdminPage adminPage;
	
	@Before
	public void setup(){
		super.setup();
	}
	
	@Test
	public void testOpenAdminPageAsUser(){
		String expected = "You are not signed in as Administrator";
		
		loginUser();
		adminPage = indexPage.goToAdminPage();
		assertTrue(driver.getPageSource().contains(expected));
	}
	
	@Test
	public void tryToEditNewsAsUser() {
		String expected = "You are not signed in as Administrator";
		
		loginUser();
		adminPage = indexPage.goToAdminPage();
		adminPage = indexPage.goToAdminNewsPage();
		assertTrue(driver.getPageSource().contains(expected));
	}
	
	@Test
	public void testOpenAdminPageAsAdmin(){
		String expected = "admin_a";
		
		loginAdmin();
		adminPage = indexPage.goToAdminPage();
		assertTrue(driver.getPageSource().contains(expected));
	}
	
	@Test
	public void tryToEditNewsAsAdmin() {
		String newNewsTitle = "NewsTitleChanged";
		String newNewsContent = "NewsContentChanged";	
		
		loginAdmin();
		adminPage = indexPage.goToAdminPage();
		adminPage = indexPage.goToAdminNewsPage();
		adminPage = adminPage.editNews(newNewsTitle, newNewsContent);
		
		assertTrue(driver.getPageSource().contains(newNewsTitle));
	}
	
	@After
	public void teardown(){
		String resetNewsTitle = "NewsTitle";
		String resetNewsContent = "NewsContent";
		
		loginAdmin();
		adminPage = indexPage.goToAdminPage();
		adminPage = indexPage.goToAdminNewsPage();
		adminPage = adminPage.editNews(resetNewsTitle, resetNewsContent);
		
		driver.quit();
	}
}
