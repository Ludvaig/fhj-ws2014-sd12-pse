package at.fhj.swd.selenium.pageobjects;

import org.openqa.selenium.WebDriver;

/**
 * Page object of index.jsf for testing.
 * @author Haberl
 *
 */
public class MainPage extends PageObjectBase {

	public MainPage(WebDriver driver) {
		super(driver);
	}
	
	public boolean uploadTestFile() {
		//TODO return if succeeded else false
		return false;
	}

	public boolean downloadTestFile() {
		//TODO return if succeeded else false
		return false;
	}

	public boolean deleteTestFile() {
		//TODO return if succeeded else false
		return false;
	}

	public boolean checkTestNews() {
		//TODO return if succeeded else false
		return false;
	}
}
