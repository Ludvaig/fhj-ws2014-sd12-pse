package at.fhj.swd.selenium;

import org.junit.Before;
import org.junit.Test;

import at.fhj.swd.selenium.pageobjects.MainPage;

public class MainPageTest extends AbstractTestSetup {

	private MainPage mainPage;


	@Before
	public void setup(){
		super.setup();
		loginAdmin();
		mainPage = tmpPage.goToMainPage();
	}
	
	@Test
	public void dummy() {
		loginUser();
	}
}
