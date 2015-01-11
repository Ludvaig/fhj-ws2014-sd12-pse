package at.fhj.swd.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * AdminPage
 * @author D.Hoesele, D.Frech
 */
public class AdminPage extends PageObjectBase {

	public AdminPage(WebDriver driver) {
		super(driver);
	}
	
	public AdminPage editNews(String newsTitle, String newsContent) {
		driver.findElement(By.cssSelector("a[href*='news_edit.jsf']")).click();
		WaitForNewPageToLoad(PageObjectBase.DEFAULT_TIMEOUT_SECONDS);
		
		driver.findElement(By.xpath(".//*[@id='news:title']")).clear();
		driver.findElement(By.xpath(".//*[@id='news:content']")).clear();
		driver.findElement(By.xpath(".//*[@id='news:title']")).sendKeys(newsTitle);
		driver.findElement(By.xpath(".//*[@id='news:content']")).sendKeys(newsContent);
		driver.findElement(By.xpath(".//*[@id='news:save']")).click();
		WaitForNewPageToLoad(PageObjectBase.DEFAULT_TIMEOUT_SECONDS);	
		
		return new AdminPage(driver);		
	}
	
	public AdminPage createCommunity(String communityName) {
		driver.findElement(By.id("newCommunityButton")).click();
		WaitForNewPageToLoad(PageObjectBase.DEFAULT_TIMEOUT_SECONDS);
		
		driver.findElement(By.xpath(".//*[@id='community:communityName']")).clear();
		driver.findElement(By.xpath(".//*[@id='community:communityName']")).sendKeys(communityName);
		driver.findElement(By.xpath(".//*[@id='community:save']")).click();
		WaitForNewPageToLoad(PageObjectBase.DEFAULT_TIMEOUT_SECONDS);	
		
		return new AdminPage(driver);		
	}	
	
}
