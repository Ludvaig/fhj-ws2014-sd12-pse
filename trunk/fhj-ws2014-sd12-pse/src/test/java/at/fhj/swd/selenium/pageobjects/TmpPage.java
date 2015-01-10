package at.fhj.swd.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TmpPage extends PageObjectBase{

	public TmpPage(WebDriver driver) {
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
		driver.findElement(By.cssSelector("a[href*='#']")).click();
		driver.findElement(By.cssSelector("a[href*='index.jsf']")).click();
		
		WaitForNewPageToLoad(PageObjectBase.DEFAULT_TIMEOUT_SECONDS);
		
		return new MainPage(driver);
	}
	
}
