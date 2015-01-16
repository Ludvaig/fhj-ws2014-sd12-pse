package at.fhj.swd.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IndexPage extends PageObjectBase{

	public IndexPage(WebDriver driver) {
		super(driver, "Index");
	}
	
	public CommunityPage goToCommunityPage(){
		driver.findElement(By.cssSelector("a[href*='communities.jsf']")).click();
		
		WaitForNewPageToLoad(PageObjectBase.DEFAULT_TIMEOUT_SECONDS);
		
		return new CommunityPage(driver);
	}
	

	public UserPage goToUserPage() {
		driver.findElement(By.cssSelector("a[href*='#']")).click();
		driver.findElement(By.cssSelector("a[href*='usersite.jsf']")).click();
		
		WaitForNewPageToLoad(PageObjectBase.DEFAULT_TIMEOUT_SECONDS);
		
		return new UserPage(driver);
	}
	

	public MainPage goToMainPage() {		
		return new MainPage(driver);
	}


	/**
	 * @author Daniel Hoesele
	 * @return AdminPage
	 */

	public AdminPage goToAdminPage() {
		driver.findElement(By.cssSelector("a[href*='#']")).click();
		driver.findElement(By.cssSelector("a[href*='adminsite.jsf']")).click();
		
		WaitForNewPageToLoad(PageObjectBase.DEFAULT_TIMEOUT_SECONDS);
		return new AdminPage(driver);
	}
	
	public AdminPage goToAdminNewsPage() {
		driver.findElement(By.cssSelector("a[href*='news_list.jsf']")).click();
		
		WaitForNewPageToLoad(PageObjectBase.DEFAULT_TIMEOUT_SECONDS);
		return new AdminPage(driver);
	}
	
	public AdminPage goToAdminCommunitiesPage() {
		driver.findElement(By.cssSelector("a[href*='community_list.jsf']")).click();
		
		WaitForNewPageToLoad(PageObjectBase.DEFAULT_TIMEOUT_SECONDS);
		return new AdminPage(driver);
	}	
	
}
