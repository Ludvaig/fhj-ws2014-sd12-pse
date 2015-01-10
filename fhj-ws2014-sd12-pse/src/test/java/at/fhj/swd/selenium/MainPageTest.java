package at.fhj.swd.selenium;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

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
		mainPage.uploadTestFile();
		assertTrue(mainPage.downloadTestFile());
		loginUser();
		mainPage = tmpPage.goToMainPage();
		assertTrue(mainPage.downloadTestFile());
		loginAdmin();
		mainPage = tmpPage.goToMainPage();
		assertTrue(mainPage.deleteTestFile());
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
		mainPage.uploadTestFile();
		loginUser();
		mainPage = tmpPage.goToMainPage();
		assertFalse(mainPage.deleteTestFile());
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
