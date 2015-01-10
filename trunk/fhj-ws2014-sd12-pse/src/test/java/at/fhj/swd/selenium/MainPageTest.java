package at.fhj.swd.selenium;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;

import at.fhj.swd.selenium.pageobjects.MainPage;

public class MainPageTest extends AbstractTestSetup {

	private MainPage mainPage;
	
	@After
	public void teardown() {
		super.teardown();
	}
	
	@Test
	public void uploadAsAdminDownloadAsAdminAndUserDeleteAsAdmin() throws Exception {
		loginAdmin();
		mainPage = tmpPage.goToMainPage();
		String testFile = mainPage.uploadTestFile();
		mainPage.downloadTestFile(testFile);
		loginUser();
		mainPage = tmpPage.goToMainPage();
		mainPage.downloadTestFile(testFile);
		loginAdmin();
		mainPage = tmpPage.goToMainPage();
		mainPage.deleteTestFile(testFile);
	}

	@Test
	public void tryToUploadAsUser() {
		loginUser();
		mainPage = tmpPage.goToMainPage();
		assertFalse(mainPage.findUploadButton());
	}
	
	@Test(expected=NoSuchElementException.class)
	public void uploadAsAdminTryToDeleteAsUser() throws Exception {
		loginAdmin();
		mainPage = tmpPage.goToMainPage();
		String testFile = mainPage.uploadTestFile();
		loginUser();
		mainPage = tmpPage.goToMainPage();
		mainPage.deleteTestFile(testFile);
	}
	
	@Test
	public void areNewsDisplayed4User() throws InterruptedException {
		loginUser();
		mainPage = tmpPage.goToMainPage();
		mainPage.checkTestNews("news 1");
	}

	@Test
	public void areNewsDisplayed4Admin() throws InterruptedException {
		loginAdmin();
		mainPage = tmpPage.goToMainPage();
		mainPage.checkTestNews("news 1");
	}
}
