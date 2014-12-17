package at.fhj.swd.service.community;

import org.junit.After;
import org.junit.Before;

import at.fhj.swd.data.CommunityDAO;
import at.fhj.swd.service.CommunityService;
import at.fhj.swd.service.impl.CommunityServiceImpl;

public class CommunityServiceTest {

	private CommunityDAO communityDao;
	private CommunityService service;

	@Before
	public void setUp() {
		// TODO: dao -> remote-object
		
		service = new CommunityServiceImpl();
		service.setCommunityDAO(communityDao);
	}

	@After
	public void tearDown() {
		
	}
}
