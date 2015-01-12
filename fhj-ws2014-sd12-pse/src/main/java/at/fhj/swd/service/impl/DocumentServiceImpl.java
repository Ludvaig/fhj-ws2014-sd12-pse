package at.fhj.swd.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhj.swd.data.entity.Document;
import at.fhj.swd.data.entity.User;
import at.fhj.swd.service.DocumentService;
import at.fhj.swd.service.UserService;
import at.fhj.swd.service.exceptions.ServiceLayerException;

/** 
 * Service which handles all requests to documents. All documents
 * are stored on directly on disk in a sub folder depending on
 * context (global, community, user).
 * 
 * @author Group1
 * edited Group3
 * */
@Stateless
public class DocumentServiceImpl implements DocumentService {
	
	@Inject
	private transient Logger logger;
	
	private static String globalContext = "global";
	
	private static String communityContext = "community";
	
	private static String userContext = "user";
	
	private static String adminContext = "admin";
	
	@EJB(beanName="UserServiceImpl")
	private UserService userService;
	
	@Override
	public List<Document> getGlobalDocuments() {
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::getGlobalDocuments()!");
		return this.getDocuments(this.getGlobalPath());
	}

	@Override
	public void uploadGlobalDocument(InputStream source, final String name) throws IOException {
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::uploadGlobalDocument()!");
		this.uploadDocument(source, name, this.getGlobalPath());
	}
	
	@Override
	public InputStream downloadGlobalDocument(final String name) throws IOException {
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::downloadGlobalDocument()!");
		return this.downloadDocument(name, this.getGlobalPath());
	}	
	
	@Override
	public void deleteGlobalDocument(final String name) {
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::deleteGlobalDocument()!");
		this.deleteDocument(name, this.getGlobalPath());
	}

	@Override
	public List<Document> getCommunityDocuments(final String community) {
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::getCommunityDocuments()!");
		return this.getDocuments(this.getCommunityPath(community));
	}

	@Override
	public void uploadCommunityDocument(final String community, InputStream source, final String name) throws IOException {
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::uploadCommunityDocument()!");
		this.uploadDocument(source, name, this.getCommunityPath(community));
	}

	@Override
	public InputStream downloadCommunityDocument(final String community, final String name) throws IOException {
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::downloadCommunityDocument()!");
		return this.downloadDocument(name, this.getCommunityPath(community));
	}	
	
	@Override
	public void deleteCommunityDocument(final String community, final String name) {
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::deleteCommunityDocument()!");
		this.deleteDocument(name, this.getCommunityPath(community));
	}

	@Override
	public List<Document> getUserDocuments(final String user) {
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::getUserDocuments()!");
		return this.getDocuments(this.getUserPath(user));		
	}

	@Override
	public void uploadUserDocument(final String user, InputStream source, final String name) throws IOException {
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::uploadUserDocument()!");
		this.uploadDocument(source, name, this.getUserPath(user));
	}

	@Override
	public InputStream downloadUserDocument(final String user, final String name) throws IOException {
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::downloadUserDocument()!");
		return this.downloadDocument(name, this.getUserPath(user));
	}	
	
	@Override
	public void deleteUserDocument(final String user, final String name) {
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::deleteUserDocument()!");
		this.deleteDocument(name, this.getUserPath(user));	
	}	
	
	@Override
	public List<Document> getAdminDocuments() {
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::getAdminDocuments()!");
		return this.getDocuments(this.getAdminPath());
	}

	@Override
	public void uploadAdminDocument(InputStream source, String name) throws IOException {
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::uploadAdminDocument()!");
		this.uploadDocument(source, name, this.getAdminPath());	
	}

	@Override
	public InputStream downloadAdminDocument(String name) throws IOException {
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::downloadAdminDocument()!");
		return this.downloadDocument(name, this.getAdminPath());
	}

	@Override
	public void deleteAdminDocument(String name) {
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::deleteAdminDocument()!");
		this.deleteDocument(name, this.getAdminPath());
	}		
	
	private List<Document> getDocuments(final String path) {
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::getDocuments()!");
		this.ensurePath(path);
		
		final File folder = new File(path);
		List<Document> docs = new ArrayList<>();
		try	{
			for (final File fileEntry : folder.listFiles()) {
				if (!fileEntry.isDirectory()) {
					Document doc = new Document();
					doc.setName(fileEntry.getName());
					docs.add(doc);
					logger.log(Level.INFO, "Added document [" + doc + "]!");
			    }
			}
		}
		catch(DataSourceLayerException e){
			logger.log(Level.SEVERE, "Service failed to fetch all documents");
			throw new ServiceLayerException("Service failed to fetch all documents", e);	
		}
		
		return docs;
	}

	private String getDocumentHome() {
		return this.concatPath(System.getProperty("jboss.server.data.dir"), "docs");
	}
	
	private String getGlobalPath() {
		return this.concatPath(this.getDocumentHome(), globalContext);
	}	

	private String getCommunityPath(final String community) {
		return this.concatPath(this.concatPath(this.getDocumentHome(), communityContext), community);
	}	
	
	private String getUserPath(final String user) {
		return this.concatPath(this.concatPath(this.getDocumentHome(), userContext), user);
	}
	
	private String getAdminPath() {
		return this.concatPath(this.getDocumentHome(), adminContext);
	}
	
	private String concatPath(final String prefix, final String postfix) {
		return new File(prefix, postfix).toString();
	}
	
	private void ensurePath(final String path) {
		new File(path).mkdirs();
	}

	private void deleteDocument(final String name, final String path) {
		//TODO: do we have to check the admin rights?
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::deleteDocument()!");
		this.ensurePath(path);
		new File(this.concatPath(path, name)).delete();
	}	
	
	private void uploadDocument(InputStream source, final String name, final String path) throws IOException {
		//TODO: do we have to check the admin rights?
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::uploadDocument()!");
		this.ensurePath(path);
		try{
			OutputStream out = new FileOutputStream(new File(path, name));
			int read = 0;
			byte[] bytes = new byte[1024]; 
			while ((read = source.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			source.close();
			out.flush();
			out.close();
		}
		catch(DataSourceLayerException e){
			logger.log(Level.SEVERE, "Service failed to upload document");
			throw new ServiceLayerException("Service failed to upload document", e);
		}
	}
	
	public InputStream downloadDocument(final String name, final String path) throws IOException {
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::downloadDocument()!");
		try{
		return new FileInputStream(this.concatPath(path, name));
		}
		catch(DataSourceLayerException e){
			logger.log(Level.SEVERE, "Service failed to download document");
			throw new ServiceLayerException("Service failed to download document", e);
		}
	}

	@Override
	public boolean getAdministrationAllowed(String token) {
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::getAdministrationAllowed()!");
		if(token == null) {
			logger.log(Level.INFO, "No valid token.");
			return false;
		}
		User user = userService.getRegisteredUser(token);
		if(user == null) {
			logger.log(Level.INFO, "No valid user.");
			return false;
		}
		logger.log(Level.INFO, "User is PortalAdmin.");
		return userService.userIsPortalAdmin(user);
	}
	
	@Override
	public boolean getUserAdministrationAllowed(String token) {
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::getUserAdministrationAllowed()!");
		if(token == null) {
			logger.log(Level.INFO, "No valid token.");
			return false;
		}
		User user = userService.getRegisteredUser(token);
		if(user == null) {
			logger.log(Level.INFO, "No valid user.");
			return false;
		}
		logger.log(Level.INFO, "User Administration allowed.");
		return true;
	}

	@Override
	public User getUserByToken(String token) {
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::getUserByToken()!");
		if(token == null) {
			logger.log(Level.INFO, "No valid token.");
			return null;
		}
		User user = userService.getRegisteredUser(token);
		logger.log(Level.INFO, "User is registered.");
		return user;
	}

	@Override
	public void deleteGlobalDocuments() {
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::deleteGlobalDocuments()!");
		List<Document> documents = this.getGlobalDocuments();
		for (Document document : documents) {
			this.deleteGlobalDocument(document.getName());
		}
	}
	
	@Override
	public boolean getIsAdministrator(String token) {
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::getIsAdministrator()!");
		if(token == null) {
			logger.log(Level.INFO, "No valid token.");
			return false;
		}
		User user = userService.getRegisteredUser(token);
		if(user == null) {
			logger.log(Level.INFO, "No valid user.");
			return false;
		}
		logger.log(Level.INFO, "User is Admin.");
		return userService.userIsAdmin(user);
	}
}
