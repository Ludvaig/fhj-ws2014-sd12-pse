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
		mainPage = tmpPage.goToMainPage();
	}
	
	@Test
	public void uploadAsAdminDownloadAsAdminAndUserDeleteAsAdmin() {
		loginAdmin();
		assertTrue(mainPage.uploadTestFile());
		assertTrue(mainPage.downloadTestFile());
		loginUser();
		assertTrue(mainPage.downloadTestFile());
		loginAdmin();
		assertTrue(mainPage.downloadTestFile());
		assertTrue(mainPage.deleteTestFile());
	}

	@Test
	public void tryToUploadAsUser() {
		loginUser();
		assertFalse(mainPage.uploadTestFile());
	}
	
	@Test
	public void uploadAsAdminTryToDeleteAsUser() {
		loginAdmin();
		mainPage.uploadTestFile();
		loginUser();
		assertFalse(mainPage.deleteTestFile());
	}
	
	@Test
	public void areNewsDisplayed() {
		loginUser();
		assertTrue(mainPage.checkTestNews());
		loginAdmin();
		assertTrue(mainPage.checkTestNews());
	}
}
