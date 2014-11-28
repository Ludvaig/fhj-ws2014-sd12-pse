package at.fhj.swd.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TmpPage extends PageObjectBase{

	public TmpPage(WebDriver driver) {
		super(driver, "Index");
	}
	
	public CommunityPage goToCommunityPage(){
		driver.findElement(By.xpath("html/body/table/tbody/tr[2]/td/a")).click();
		
		WaitForNewPageToLoad(PageObjectBase.DEFAULT_TIMEOUT_SECONDS);
		
		return new CommunityPage(driver);
	}
	
}
