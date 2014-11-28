package at.fhj.swd.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CommunityPage extends PageObjectBase{

	public CommunityPage(WebDriver driver) {
		super(driver);
	}
	
	public void searchForCommunity(String text) throws InterruptedException {
//		final WebElement firstRow = driver.findElement(By.xpath(".//*[@id='communitySearch:communities_data']/tr[1]/td"));
		driver.findElement(By.xpath(".//*[@id='communitySearch:j_idt14']")).clear();
		driver.findElement(By.xpath(".//*[@id='communitySearch:j_idt14']")).sendKeys(text);
		driver.findElement(By.xpath(".//*[@id='communitySearch:communitySearch']")).click();
		
		// Wait 1 second until the ajax call is finished
		Thread.sleep(1000);
		
//		TODO: Thread is bad, tried another method but failed.
//		WebDriverWait wait = new WebDriverWait(driver, 1);
//		wait.until(new ExpectedCondition<Boolean>() {
//            public Boolean apply(WebDriver d) {
//            	System.out.println("TEST");
//            	WebElement newFirstRow = d.findElement(By.xpath(".//*[@id='communitySearch:communities_data']/tr[1]/td"));
//            	System.out.println(firstRow.getText() + " " + newFirstRow.getText());
//            	return !firstRow.getText().equals(newFirstRow.getText());
//            }
//        });
	}
	
	public TopicPage goToTopicPage(){
		driver.findElement(By.xpath(".//*[@id='communitySearch:communities_data']/tr[1]/td")).click();
		
		WaitForNewPageToLoad(PageObjectBase.DEFAULT_TIMEOUT_SECONDS);
		
		return new TopicPage(driver);
	}
	
}
