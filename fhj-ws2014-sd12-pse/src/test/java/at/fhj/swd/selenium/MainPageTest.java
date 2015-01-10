package at.fhj.swd.selenium;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import at.fhj.swd.selenium.pageobjects.MainPage;

public class MainPageTest extends AbstractTestSetup {

	private MainPage mainPage;


	@Before
	public void setup(){
		super.setup();
		deleteAllDocuments();
		deleteAllNews();
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

	public void tryToUploadAsUser() {
		loginUser();
		mainPage = tmpPage.goToMainPage();
		mainPage.findUploadButton();
	}
	
	@Test
	public void uploadAsAdminTryToDeleteAsUser() throws Exception {
		loginAdmin();
		mainPage = tmpPage.goToMainPage();
		String testFile = mainPage.uploadTestFile();
		loginUser();
		mainPage = tmpPage.goToMainPage();
		mainPage.deleteTestFile(testFile);
	}
	
	@Test
	public void areNewsDisplayed() {
		createTestNews();
		loginUser();
		mainPage = tmpPage.goToMainPage();
		assertTrue(mainPage.checkTestNews());
		loginAdmin();
		mainPage = tmpPage.goToMainPage();
		assertTrue(mainPage.checkTestNews());
	}
	
	private void deleteAllDocuments() {
		//TODO
	}
	
	private void deleteAllNews() {
		//TODO
	}
	
	private void createTestNews() {
		//TODO
	}
}
