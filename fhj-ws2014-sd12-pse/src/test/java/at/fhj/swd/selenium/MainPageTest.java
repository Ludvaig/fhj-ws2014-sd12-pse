package at.fhj.swd.selenium;

import static org.junit.Assert.*;

import javax.naming.NamingException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;

import at.fhj.swd.remoteFacade.RemoteServiceLocator;
import at.fhj.swd.selenium.pageobjects.MainPage;
import at.fhj.swd.service.DocumentService;

public class MainPageTest extends AbstractTestSetup {

	private MainPage mainPage;
	
	@Before
	public void setUp() throws NamingException {
		DocumentService ds = RemoteServiceLocator.getDocumentService();
		ds.deleteGlobalDocuments();
	}
	
	@After
	public void teardown() {
		super.teardown();
	}
	
	@Test
	public void uploadAsAdminDownloadAsAdminAndUserDeleteAsAdmin() throws Exception {
		loginAdminPa();
		mainPage = indexPage.goToMainPage();
		String testFile = mainPage.uploadTestFile();
		mainPage.downloadTestFile(testFile);
		loginUser();
		mainPage = indexPage.goToMainPage();
		mainPage.downloadTestFile(testFile);
		loginAdminPa();
		mainPage = indexPage.goToMainPage();
		mainPage.deleteTestFile(testFile);
	}

	@Test
	public void tryToUploadAsUser() {
		loginUser();
		mainPage = indexPage.goToMainPage();
		assertFalse(mainPage.findUploadButton());
	}
	
	@Test(expected=NoSuchElementException.class)
	public void uploadAsAdminTryToDeleteAsUser() throws Exception {
		loginAdminPa();
		mainPage = indexPage.goToMainPage();
		String testFile = mainPage.uploadTestFile();
		loginUser();
		mainPage = indexPage.goToMainPage();
		mainPage.deleteTestFile(testFile);
	}
	
	@Test
	public void areNewsDisplayed4User() throws InterruptedException {
		loginUser();
		mainPage = indexPage.goToMainPage();
		mainPage.checkTestNews("news 1", "content 1");
	}

	@Test
	public void areNewsDisplayed4Admin() throws InterruptedException {
		loginAdminPa();
		mainPage = indexPage.goToMainPage();
		mainPage.checkTestNews("news 1", "content 1");
	}
}
