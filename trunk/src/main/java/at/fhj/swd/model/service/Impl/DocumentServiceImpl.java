package at.fhj.swd.model.service.Impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import at.fhj.swd.model.entity.Document;
import at.fhj.swd.model.service.DocumentService;

/** 
 * Service which handles all requests to documents.
 * 
 * @author Group1
 * */
@ManagedBean(name = "documentService")
@ApplicationScoped
public class DocumentServiceImpl implements DocumentService {

	private static String documentHome = "/tmp/doclib";
	
	private static String globalContext = "global";
	
	private static String communityContext = "community";
	
	private static String userContext = "user";
	
	@Override
	public List<Document> getGlobalDocuments() {
		return this.getDocuments(this.getGlobalPath());
	}

	@Override
	public void uploadGlobalDocument(InputStream source, String name) throws IOException {
		this.uploadDocument(source, name, this.getGlobalPath());
	}
	
	@Override
	public void deleteGlobalDocument(String name) {
		this.deleteDocument(name, this.getGlobalPath());
	}

	@Override
	public List<Document> getCommunityDocuments(String community) {
		return this.getDocuments(this.getCommunityPath(community));
	}

	@Override
	public void uploadCommunityDocument(final String community, InputStream source, String name) throws IOException {
		this.uploadDocument(source, name, this.getCommunityPath(community));
	}

	@Override
	public void deleteCommunityDocument(String community, String name) {
		this.deleteDocument(name, this.getCommunityPath(community));
	}

	@Override
	public List<Document> getUserDocuments(String user) {
		return this.getDocuments(this.getUserPath(user));		
	}

	@Override
	public void uploadUserDocument(final String user, InputStream source, String name) throws IOException {
		this.uploadDocument(source, name, this.getUserPath(user));
	}

	@Override
	public void deleteUserDocument(String user, String name) {
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

	private String getGlobalPath() {
		return this.concatPath(documentHome, globalContext);
	}	

	private String getCommunityPath(final String community) {
		return this.concatPath(this.concatPath(documentHome, communityContext), community);
	}	
	
	private String getUserPath(final String user) {
		return this.concatPath(this.concatPath(documentHome, userContext), user);
	}
	
	private String concatPath(final String prefix, final String postfix) {
		return new File(prefix, postfix).toString();
	}
	
	private void ensurePath(final String path) {
		new File(path).mkdirs();
	}

	private void deleteDocument(final String name, final String path) {
		this.ensurePath(path);
	}	
	
	private void uploadDocument(InputStream source, final String name, final String path) throws IOException {
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
}
