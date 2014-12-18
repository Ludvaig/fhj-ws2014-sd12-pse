package at.fhj.swd.service.community;

import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import at.fhj.swd.data.CommunityDAO;
import at.fhj.swd.service.CommunityService;
import at.fhj.swd.service.impl.CommunityServiceImpl;

public class CommunityServiceTest {

	private CommunityDAO communityDao;
	private CommunityService service;
    
	private static Hashtable<String, String> jndiProperties;
	private static String jndiName;
	private static Context context;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws NamingException {
		jndiProperties = new Hashtable<String, String>();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		context = new InitialContext(jndiProperties);
		
		StringBuilder builder = new StringBuilder();
		jndiName = builder.append("fhj-ws2014-sd12-pse").append("/").append("CommunityDAOTestFacade").append("/").append(CommunityDAO.class.getName()).toString();
		
		logger.log(Level.INFO, "Created jndi-Lookup for CommunityDAO: " + jndiName);
	}
	
	@Before
	public void setUp() throws NamingException {
		communityDao = (CommunityDAO) CommunityServiceTest.context.lookup(jndiName);
		
		service = new CommunityServiceImpl();
		service.setCommunityDAO(communityDao);
		
		// TODO: Create test-data which is used for all further test-cases (create needed db-tables + insert test data)
	}

	@After
	public void tearDown() {
		// TODO: Cleanup test-data (drop tables)
	}
	
	// TODO: implement tests here!
}
