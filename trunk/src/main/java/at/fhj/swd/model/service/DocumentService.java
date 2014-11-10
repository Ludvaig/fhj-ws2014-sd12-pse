package at.fhj.swd.model.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import at.fhj.swd.model.entity.Document;
import at.fhj.swd.model.entity.User;

/** 
 * Interface to service which handles all requests to documents.
 * 
 * @author Group1
 * */
public interface DocumentService {
	public List<Document> getGlobalDocuments();
	
	//Global	
	public void uploadGlobalDocument(InputStream source, final String name) throws IOException;

	public void deleteGlobalDocument(final String name);
	
	public InputStream downloadGlobalDocument(final String name) throws IOException;
	
	//Community	
	public List<Document> getCommunityDocuments(final String community);

	public void uploadCommunityDocument(final String community, InputStream source, final String name) throws IOException;

	public InputStream downloadCommunityDocument(final String community, final String name) throws IOException;
	
	public void deleteCommunityDocument(final String community, final String name);
	
	//User
	public List<Document> getUserDocuments(final String user);
		
	public void uploadUserDocument(final String user, InputStream source, final String name) throws IOException;	

	public InputStream downloadUserDocument(final String user, final String name) throws IOException;
	
	public void deleteUserDocument(final String user, final String name);
	
	/**
	 * checks if user is logged in.
	 * 
	 * @param token
	 * @return
	 */
	public boolean getAdministrationAllowed(String token);
	
	public User getUserByToken(String token);

	boolean getUserAdministrationAllowed(String token);
}
