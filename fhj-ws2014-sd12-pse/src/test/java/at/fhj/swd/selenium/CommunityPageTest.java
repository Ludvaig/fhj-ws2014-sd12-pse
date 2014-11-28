package at.fhj.swd.selenium;

import org.junit.Assert;
import org.junit.Test;

import at.fhj.swd.selenium.pageobjects.CommunityPage;


public class CommunityPageTest extends AbstractTestSetup{

	@Test
	public void openCommunityPage(){
		CommunityPage communityPage = tmpPage.goToCommunityPage();
		
		String expected = "Communities";
		Assert.assertEquals(expected, communityPage.GetTextOfxPathElement(".//*[@id='communitySearch']/h1"));
	}
}
