package at.fhj.swd.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends PageObjectBase{

	public LoginPage(WebDriver driver) {
		super(driver, "Login");
	}

	public TmpPage loginUser()  {
		
		driver.findElement(By.xpath(".//*[@id='login:username']")).clear();
		driver.findElement(By.xpath(".//*[@id='login:password']")).clear();
		driver.findElement(By.xpath(".//*[@id='login:username']")).sendKeys("Herbert");
		driver.findElement(By.xpath(".//*[@id='login:password']")).sendKeys("vergessen");
		driver.findElement(By.xpath(".//*[@id='login:login']")).click(); 
		
		// note: click does not wait for page to load, so wait
		WaitForNewPageToLoad(PageObjectBase.DEFAULT_TIMEOUT_SECONDS);	
		
		return new TmpPage(driver);
	}
	
}
