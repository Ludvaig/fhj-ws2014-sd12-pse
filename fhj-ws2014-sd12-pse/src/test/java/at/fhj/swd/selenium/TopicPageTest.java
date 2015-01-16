package at.fhj.swd.selenium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import at.fhj.swd.selenium.pageobjects.CommunityPage;
import at.fhj.swd.selenium.pageobjects.TopicPage;

public class TopicPageTest extends AbstractTestSetup{
	
	private CommunityPage communityPage;
	private TopicPage topicPage;
	
	@Before
	public void setup(){
		super.setup();
		loginUser();
		communityPage = indexPage.goToCommunityPage();
		topicPage = communityPage.goToTopicPage();
	}
	
	@Test
	public void openTopicPage(){
		String expected = "Topic(s) of Community: Der Rest";
		Assert.assertEquals(expected, topicPage.GetTextOfxPathElement(".//*[@id='topicSearch']/h1"));
	}
	
}
