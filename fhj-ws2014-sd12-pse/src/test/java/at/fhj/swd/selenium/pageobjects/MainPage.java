package at.fhj.swd.selenium.pageobjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page object of index.jsf for testing.
 * @author Haberl
 *
 */
public class MainPage extends PageObjectBase {
	private String testFile;
	

	public MainPage(WebDriver driver) {
		super(driver);
	}
	
	public boolean findUploadButton() {		
		return !driver.findElements(By.id("documentViewer:button_input")).isEmpty();
	}
	
	public void uploadTestFile() throws Exception {
		File temp = File.createTempFile("temp-file-name", ".txt"); 
		testFile = temp.getName();
		WebElement upload = driver.findElement(By.id("documentViewer:button_input"));
		upload.click();
		StringSelection ss = new StringSelection(temp.getAbsolutePath());
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		
		Thread.sleep(2000);
		
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		
		Thread.sleep(2000);
		
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		Thread.sleep(4000);
		
		getTestFileLink();
	}
	
	public WebElement getTestFileLink() {
	      return driver.findElement(By.linkText(testFile));
	}

	public boolean downloadTestFile() {
		if(testFile == null) {
			throw new IllegalStateException("The test file needs to be created first");
		}

		getTestFileLink();
		return false;
	}

	public boolean deleteTestFile() {
		if(testFile == null) {
			throw new IllegalStateException("The test file needs to be created first");
		}
		//TODO return if succeeded else false
		return false;
	}

	public boolean checkTestNews() {
		//TODO return if succeeded else false
		return false;
	}
}
