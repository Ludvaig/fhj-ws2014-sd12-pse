package at.fhj.swd.service.community;

import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.omg.CORBA.OMGVMCID;

import at.fhj.swd.data.CommunityDAO;
import at.fhj.swd.data.UserDAO;
import at.fhj.swd.data.entity.Community;
import at.fhj.swd.service.CommunityService;
import at.fhj.swd.service.impl.CommunityServiceImpl;

public class CommunityServiceTest {

	private CommunityDAO communityDao;
	private UserDAO userDao;
	private transient CommunityService service;
    
	private static Hashtable<String, String> jndiProperties;
	private static String jndiNameForCommunity;
	private static String jndiNameForUser;
	private static Context context;
	private static Logger logger = Logger.getLogger(CommunityServiceTest.class.getName());
	
	@BeforeClass
	public static void setUpBeforeClass() throws NamingException {
		jndiProperties = new Hashtable<String, String>();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		context = new InitialContext(jndiProperties);
		
		/* Create JNDI for Communities. */
		StringBuilder communityBuilder = new StringBuilder();
		jndiNameForCommunity = communityBuilder.append("fhj-ws2014-sd12-pse").append("/").append("CommunityDAOTestFacade").append("/").append(CommunityDAO.class.getName()).toString();
		logger.log(Level.INFO, "Created jndi-Lookup for CommunityDAO: " + jndiNameForCommunity);
		
		/* Create JNDI for Communities. */
		StringBuilder userBuilder = new StringBuilder();
		jndiNameForUser = userBuilder.append("fhj-ws2014-sd12-pse").append("/").append("UserDAOTestFacade").append("/").append(UserDAO.class.getName()).toString();
		logger.log(Level.INFO, "Created jndi-Lookup for UserDAO: " + jndiNameForUser);
	}
	
	@Before
	public void setUp() throws NamingException {
		communityDao = (CommunityDAO) CommunityServiceTest.context.lookup(jndiNameForCommunity);
		userDao = (UserDAO) CommunityServiceTest.context.lookup(jndiNameForUser);
		
		service = new CommunityServiceImpl();
		service.setCommunityDAO(communityDao);
		service.setUserDAO(userDao);
		
		// TODO: Create test-data which is used for all further test-cases (create needed db-tables + insert test data)
	}

	@After
	public void tearDown() {
		// TODO: Cleanup test-data (drop tables)
	}
	
	@Test
	public void getAllCommunities() { // TODO
		/* DB-setup:
		 * - create communities1,2,3
		 * */
		
		
		List<Community> allCommunities = service.getAllCommunities();
	}
	
	@Ignore
	public void getAllSubscribedCommunitiesForUser() { // TODO
		/* DB-setup:
		 * - create user Herbert
		 * - create communities1,2,3
		 * - subscribe Herbert for communities1,2,3
		 * */
		
		Community community1 = null;
		
		List<Community> communitiesOfHerbert = service.getAllSubscribedCommunitiesForUser("Herbert");
		
		Assert.assertTrue(community1.equals(communitiesOfHerbert.get(0)));
	}
	
	@Ignore
	public void getSubscribedCommunitiesForUser(String searchfieldText, String authUserToken) { // TODO
		/* DB-setup:
		 * - create user Herbert
		 * - create communities1,2,3 (dienst, reise, dienstreise)
		 * - subscribe Herbert for communities1,2,3
		 * */
		
		List<Community> communitiesOfHerbert = service.getSubscribedCommunitiesForUser("dienst", "Herbert");
		
		// outcome: communities1,3 -> ok, community2 -> false
	}
	
	@Ignore
	public void isUserIsLoggedIn() {
		/* DB-setup:
		 * - 
		 * */
		
		// TODO
	}
	
	/* 
	public Community getCommunityById(long id);
	void createCommunity(String name, boolean visible);
	void releaseCommunity(long id, boolean release);
	public boolean ensureUserIsAdmin()
	 * */
}
