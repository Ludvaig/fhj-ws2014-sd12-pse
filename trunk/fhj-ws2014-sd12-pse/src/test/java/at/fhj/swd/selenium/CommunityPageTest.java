package at.fhj.swd.selenium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import at.fhj.swd.selenium.pageobjects.CommunityPage;


public class CommunityPageTest extends AbstractTestSetup{

	private CommunityPage communityPage;
	
	@Before
	public void setup(){
		super.setup();
		communityPage = tmpPage.goToCommunityPage();
	}
	
	@Test
	public void openCommunityPage(){
		String expected = "Communities";
		Assert.assertEquals(expected, communityPage.GetTextOfxPathElement(".//*[@id='communitySearch']/h1"));
	}
	
	@Test
	public void searchCommunities() throws InterruptedException{
		communityPage.searchForCommunity("Eor");
		String expected = "George";
		Assert.assertEquals(expected, communityPage.GetTextOfxPathElement(".//*[@id='communitySearch:communities_data']/tr[1]/td"));
	}
	
	@Test
	public void searchCommunitiesNotExists() throws InterruptedException{
		communityPage.searchForCommunity("ASDF");
		String expected = "No records found.";
		Assert.assertEquals(expected, communityPage.GetTextOfxPathElement(".//*[@id='communitySearch:communities_data']/tr[1]/td"));
	}
}
