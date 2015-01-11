package at.fhj.swd.remoteFacade;

import javax.naming.NamingException;

import at.fhj.swd.service.UserService;
import at.fhj.swd.service.DocumentService;

public class RemoteServiceLocator {
	public static UserService getUserService() throws NamingException {
		return (UserService)RemoteFactory.create("UserServiceRemoteFacade", UserService.class.getName());	
	}
	
	public static DocumentService getDocumentService() throws NamingException {
		return (DocumentService)RemoteFactory.create("DocumentServiceRemoteFacade", DocumentService.class.getName());	
	}	
}
