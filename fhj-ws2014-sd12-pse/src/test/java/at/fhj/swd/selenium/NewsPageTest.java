package at.fhj.swd.selenium;

import static org.junit.Assert.assertTrue;

/** author: Julia Viehberger
 * 
 */

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import at.fhj.swd.selenium.pageobjects.AdminPage;


public class NewsPageTest extends AbstractTestSetup{

	private AdminPage adminPage;
	String expected;
	
	@Before
	public void setup(){
		super.setup();

	}
	
	@Test
	public void tryToCreateNewsAsAdmin(){
		String newNewsTitle = "NewNews";
		String newNewsContent = "NewNewsContent";	
		
		loginAdmin();
		adminPage = indexPage.goToAdminPage();
		adminPage = indexPage.goToAdminNewsPage();
		adminPage = adminPage.createNews(newNewsTitle, newNewsContent);
		
		assertTrue(driver.getPageSource().contains(newNewsTitle));

	}
	@Test
	public void openAdminNewsPage(){
		loginAdmin();
		adminPage = indexPage.goToAdminPage();

		adminPage = indexPage.goToAdminNewsPage();
		String expected = "News";
		Assert.assertEquals(expected, adminPage.GetTextOfxPathElement(".//*[@id='newstitle']/h1"));
	}
	
	@Test
	public void tryToEditNewsAsAdmin() {
		String newNewsTitle = "NewsTitleChanged!!";
		String newNewsContent = "NewsContentChanged";	
		
		loginAdmin();
		adminPage = indexPage.goToAdminPage();
		adminPage = indexPage.goToAdminNewsPage();
		adminPage = adminPage.editNews(newNewsTitle, newNewsContent);
		
		assertTrue(driver.getPageSource().contains(newNewsTitle));
	}
	


}
