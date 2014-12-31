package at.fhj.swd.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class UserPage extends PageObjectBase {

	public UserPage(WebDriver driver) {
		super(driver);
	}

	public void changePhoneNumber(String phonenumber) throws InterruptedException {
		driver.findElement(By.xpath(".//*[@id='userForm:phoneNumber']")).clear();
		driver.findElement(By.xpath(".//*[@id='userForm:phoneNumber']")).sendKeys(phonenumber);
		driver.findElement(By.xpath(".//*[@id='saveUserdata']")).click();
		
		// Wait 1 second until the ajax call is finished
		Thread.sleep(1000);
		
		driver.navigate().refresh();
	}
	
}
