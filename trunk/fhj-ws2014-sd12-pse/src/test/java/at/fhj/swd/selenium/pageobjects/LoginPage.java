package at.fhj.swd.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * LoginPage pageobject for testing.
 * @author I. Palade
 *
 */
public class LoginPage extends PageObjectBase{

	public LoginPage(WebDriver driver) {
		super(driver, "Login");
	}

	public IndexPage loginUser()  {
		return loginUser("Herbert", "vergessen");
	}
	
	/**
	 * @author Daniel Hoesele
	 * @return logged in as admin
	 */
	public IndexPage loginAdmin() {
		return loginUser("admin_a", "1");
	}
	
	public IndexPage loginAdminPa() {
		return loginUser("padmin_pa", "1");
	}
	
	
	public IndexPage loginUser(String user, String password){
		driver.findElement(By.xpath(".//*[@id='login:username']")).clear();
		driver.findElement(By.xpath(".//*[@id='login:password']")).clear();
		driver.findElement(By.xpath(".//*[@id='login:username']")).sendKeys(user);
		driver.findElement(By.xpath(".//*[@id='login:password']")).sendKeys(password);
		driver.findElement(By.xpath(".//*[@id='login:login']")).click(); 

		// note: click does not wait for page to load, so wait
		WaitForNewPageToLoad(PageObjectBase.DEFAULT_TIMEOUT_SECONDS);	
		
		return new IndexPage(driver);		
	}
	
}
