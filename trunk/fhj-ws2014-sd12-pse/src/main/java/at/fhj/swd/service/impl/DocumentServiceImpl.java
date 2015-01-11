package at.fhj.swd.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhj.swd.data.entity.Document;
import at.fhj.swd.data.entity.User;
import at.fhj.swd.service.DocumentService;
import at.fhj.swd.service.UserService;

/** 
 * Service which handles all requests to documents. All documents
 * are stored on directly on disk in a sub folder depending on
 * context (global, community, user).
 * 
 * @author Group1
 * */
@Stateless
public class DocumentServiceImpl implements DocumentService {
	
	private static String globalContext = "global";
	
	private static String communityContext = "community";
	
	private static String userContext = "user";
	
	@EJB(beanName="UserServiceImpl")
	private UserService userService;
	
	@Override
	public List<Document> getGlobalDocuments() {
		return this.getDocuments(this.getGlobalPath());
	}

	@Override
	public void uploadGlobalDocument(InputStream source, final String name) throws IOException {
		this.uploadDocument(source, name, this.getGlobalPath());
	}
	
	@Override
	public InputStream downloadGlobalDocument(final String name) throws IOException {
		return this.downloadDocument(name, this.getGlobalPath());
	}	
	
	@Override
	public void deleteGlobalDocument(final String name) {
		this.deleteDocument(name, this.getGlobalPath());
	}

	@Override
	public List<Document> getCommunityDocuments(final String community) {
		return this.getDocuments(this.getCommunityPath(community));
	}

	@Override
	public void uploadCommunityDocument(final String community, InputStream source, final String name) throws IOException {
		this.uploadDocument(source, name, this.getCommunityPath(community));
	}

	@Override
	public InputStream downloadCommunityDocument(final String community, final String name) throws IOException {
		return this.downloadDocument(name, this.getCommunityPath(community));
	}	
	
	@Override
	public void deleteCommunityDocument(final String community, final String name) {
		this.deleteDocument(name, this.getCommunityPath(community));
	}

	@Override
	public List<Document> getUserDocuments(final String user) {
		return this.getDocuments(this.getUserPath(user));		
	}

	@Override
	public void uploadUserDocument(final String user, InputStream source, final String name) throws IOException {
		this.uploadDocument(source, name, this.getUserPath(user));
	}

	@Override
	public InputStream downloadUserDocument(final String user, final String name) throws IOException {
		return this.downloadDocument(name, this.getUserPath(user));
	}	
	
	@Override
	public void deleteUserDocument(final String user, final String name) {
		this.deleteDocument(name, this.getUserPath(user));	
	}	
	
	private List<Document> getDocuments(final String path) {
		this.ensurePath(path);
		
		final File folder = new File(path);
		List<Document> docs = new ArrayList<>();
		
		for (final File fileEntry : folder.listFiles()) {
			if (!fileEntry.isDirectory()) {
				Document doc = new Document();
				doc.setName(fileEntry.getName());
				docs.add(doc);
		    }
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
	
	private String concatPath(final String prefix, final String postfix) {
		return new File(prefix, postfix).toString();
	}
	
	private void ensurePath(final String path) {
		new File(path).mkdirs();
	}

	private void deleteDocument(final String name, final String path) {
		//TODO: do we have to check the admin rights?
		this.ensurePath(path);
		new File(this.concatPath(path, name)).delete();
	}	
	
	private void uploadDocument(InputStream source, final String name, final String path) throws IOException {
		//TODO: do we have to check the admin rights?
		this.ensurePath(path);
		
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
	
	public InputStream downloadDocument(final String name, final String path) throws IOException {
		return new FileInputStream(this.concatPath(path, name));
	}

	@Override
	public boolean getAdministrationAllowed(String token) {
		if(token == null) {
			return false;
		}
		User user = userService.getRegisteredUser(token);
		if(user == null) {
			return false;
		}
		return userService.userIsPortalAdmin(user);
	}
	
	@Override
	public boolean getUserAdministrationAllowed(String token) {
		if(token == null) {
			return false;
		}
		User user = userService.getRegisteredUser(token);
		if(user == null) {
			return false;
		}
		return true;
	}

	@Override
	public User getUserByToken(String token) {
		if(token == null) {
			return null;
		}
		User user = userService.getRegisteredUser(token);
		return user;
	}

	@Override
	public void deleteGlobalDocuments() {
		List<Document> documents = this.getGlobalDocuments();
		for (Document document : documents) {
			this.deleteGlobalDocument(document.getName());
		}
	}		
}
