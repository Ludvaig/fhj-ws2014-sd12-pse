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
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxProfile;

import static org.junit.Assert.*;

/**
 * Page object of index.jsf for testing.
 * @author Haberl
 *
 */
public class MainPage extends PageObjectBase {	

	private final String tmpDir;
	
	public MainPage(WebDriver driver) {
		super(driver);
		tmpDir = System.getProperty("java.io.tmpdir");
	}
	
	public boolean findUploadButton() {		
		return !driver.findElements(By.id("documentViewer:button_input")).isEmpty();
	}
	
	public String uploadTestFile() throws Exception {
		File temp = File.createTempFile("temp-file-name", ".txt"); 
		String testFile = temp.getName();
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
		
		getTestFileLink(testFile);
		
		temp.delete();
		assertFalse(temp.exists());
		
		return testFile;
	}
	
	public WebElement getTestFileLink(String testFile) {
		if(testFile == null) {
			throw new IllegalStateException("The test file needs to be created first");
		}
		List<WebElement> elements = driver.findElements(By.linkText(testFile));
	    return elements.isEmpty() ? null : elements.get(0);
	}

	public void downloadTestFile(String testFile) throws Exception {
		WebElement fileLink = getTestFileLink(testFile);
		
		fileLink.click();		
		
		Thread.sleep(2000);
		
		File downloadedFile = new File(tmpDir, testFile);

		assertTrue(downloadedFile.exists());
		downloadedFile.delete();
		assertFalse(downloadedFile.exists());
	}

	public void deleteTestFile(String testFile) throws Exception {		
		WebElement fileLink = getTestFileLink(testFile);
		
		WebElement row = fileLink.findElement(By.xpath(".."));
		WebElement deleteButton = row.findElement(By.linkText("Delete"));
		
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", deleteButton);
		
		Thread.sleep(2000);

		assertNull("Document must be deleted!", getTestFileLink(testFile));
	}

	public boolean checkTestNews() {
		//TODO return if succeeded else false
		return false;
	}
}
