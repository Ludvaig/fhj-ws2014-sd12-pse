package at.fhj.swd.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CommunityPage extends PageObjectBase{

	public CommunityPage(WebDriver driver) {
		super(driver);
	}
	
	public void searchForCommunity(String text) throws InterruptedException {
		driver.findElement(By.xpath(".//*[@id='communitySearch:j_idt12']")).clear();
		driver.findElement(By.xpath(".//*[@id='communitySearch:j_idt12']")).sendKeys(text);
		driver.findElement(By.xpath(".//*[@id='communitySearch:communitySearch']")).click();
		
		// Wait 1 second until the ajax call is finished
		Thread.sleep(1000);
	}
	
}
