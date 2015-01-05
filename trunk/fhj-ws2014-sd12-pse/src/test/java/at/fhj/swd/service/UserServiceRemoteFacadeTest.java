package at.fhj.swd.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import at.fhj.swd.service.exceptions.UserLoginException;

public final class UserServiceRemoteFacadeTest {


	private UserService service;
	// The JNDI lookup name for a stateless session bean has the syntax of:
	// ejb:<appName>/<moduleName>/<distinctName>/<beanName>!<viewClassName>
	//
	// <appName> The application name is the name of the EAR that the EJB is deployed in
	// (without the .ear). If the EJB JAR is not deployed in an EAR then this is
	// blank. The app name can also be specified in the EAR's application.xml
	//
	// <moduleName> By the default the module name is the name of the EJB JAR file (without the
	// .jar suffix). The module name might be overridden in the ejb-jar.xml
	//
	// <distinctName> : WildFly allows each deployment to have an (optional) distinct name.
	// This example does not use this so leave it blank.
	//
	// <beanName> : The name of the session been to be invoked.
	//
	// <viewClassName>: The fully qualified classname of the remote interface. Must include
	// the whole package name.
	
	// "ejb:/wildfly-ejb-remote-server-side/CalculatorBean!" + RemoteCalculator.class.getName()
	// java:global/EJB-Calculator-RemoteTest/CalculatorTestBean!org.se.lab.Calculator
	//  ejb:/EJB-Calculator-RemoteTest/CalculatorTestBean!org.se.lab.Calculator
	
    @Before
    public void setUp() throws NamingException  
    {
    	final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        final Context context = new InitialContext(jndiProperties);
        
        final String jndiName = "ejb:" + "" 
        		+ "/" + "fhj-ws2014-sd12-pse" 
        		+ "/" + ""
        		+ "/" + "UserServiceTestFacade" 
        		+ "!" + UserService.class.getName();
   
        service =  (UserService) context.lookup(jndiName);
    }
    
    
    @Test
    public void registerUser_defect()
    {
		try {
			service.registerUser("anyUser", "anyPw");
			fail();
		} catch (Exception e) {
			assertEquals(UserLoginException.class, e.getCause().getClass());
		}
    }
	
}
