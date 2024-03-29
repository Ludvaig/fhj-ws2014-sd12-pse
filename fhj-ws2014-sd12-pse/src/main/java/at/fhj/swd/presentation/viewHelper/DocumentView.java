package at.fhj.swd.presentation.viewHelper;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;

import at.fhj.swd.data.entity.Document;
import at.fhj.swd.data.entity.User;
import at.fhj.swd.presentation.helper.CookieHelper;
import at.fhj.swd.service.DocumentService;


/**
 * Document-ViewHelper.
 * 
 * @author Group1
 * */

@ManagedBean(name="dtDocumentView")
@ViewScoped
public class DocumentView implements Serializable {

	private static final long serialVersionUID = 6219023243007670413L;
	
	@Inject
	protected transient Logger logger;
	
	protected String selectedDocument;
	
	private String selectedUserDocument;
	
	@EJB(beanName="DocumentServiceImpl")
	protected transient DocumentService service;
    
    @PostConstruct
    public void init() {
		logger.log(Level.INFO, "Initiliazing " + this.getClass().getName() + " in @PostConstruct!");
    }
    
    // Global ------------------------
    public List<Document> getDocuments() {
		logger.log(Level.INFO, "Retrieving Document List.");
    	return this.service.getGlobalDocuments();
    }
    
    public void handleFileUpload(FileUploadEvent event) throws IOException {
		logger.log(Level.INFO, "Uploading Global-Doc " + event.getFile().getFileName() + " into Documents.");
    	this.service.uploadGlobalDocument(event.getFile().getInputstream(), event.getFile().getFileName());
    }
    
	public void setSelectedDocument(String name) {
    	this.selectedDocument = name;
    }
    
    public String getSelectedDocument() {
    	return this.selectedDocument;
    }
    
    public void setDeleteDocument(String name) {
		logger.log(Level.INFO, "Deleting Global-Doc " + name + " from Documents.");
    	this.service.deleteGlobalDocument(name);
    }
    
	public DefaultStreamedContent getDownload() throws IOException {
    	String name = this.getSelectedDocument();
    	logger.log(Level.INFO, "Downloading Global-Doc " + name + " from Documents.");
    	return new DefaultStreamedContent(this.service.downloadGlobalDocument(name), null, name);
    }
	// -----------------------------
	
	
	// User ------------------------
    public List<Document> getUserDocuments() {
    	String username = getLoggedInUsername();
	    if (username != null) {
			logger.log(Level.INFO, "Retrieving Document List for User " + username + ".");
	    	return this.service.getUserDocuments(username);
    	} else {
    		return null;
    	}
    }
    
    public void handleUserFileUpload(FileUploadEvent event) throws IOException {
    	String username = getLoggedInUsername();
	    if (username != null) {
			logger.log(Level.INFO, "Uploading User-Doc " + event.getFile().getFileName() + " into Documents for User " + username + ".");
	    	this.service.uploadUserDocument(username, event.getFile().getInputstream(), event.getFile().getFileName());
	    }
    }
     
    public void setSelectedUserDocument(String name) {
    	this.selectedUserDocument = name;
    }
    
    public String getSelectedUserDocument() {
    	return this.selectedUserDocument;
    }
    
    public void setDeleteUserDocument(String name) {
    	String username = getLoggedInUsername();
	    if (username != null) {
			logger.log(Level.INFO, "Deleting User-Doc " + name + " from " + username + "'s Documents.");
       		this.service.deleteUserDocument(username, name);
    	}
    }
    
    public DefaultStreamedContent getUserDownload() throws IOException {
    	String username = getLoggedInUsername();
	    if (username != null) {
	    	String name = this.getSelectedUserDocument();
	    	if (name != null) {
				logger.log(Level.INFO, "Downloading User-Doc " + name + " from " + username + "'s Documents.");
	    		return new DefaultStreamedContent(this.service.downloadUserDocument(username, name), null, name);
	    	} else {
	    		return null;
	    	}
	    } else {
	    	return null;
	    }
    }
    // -----------------------------
    
    
    public boolean getAdministrationAllowed() {
    	return service.getAdministrationAllowed(CookieHelper.getAuthTokenValue());
    }
    
    // User related things ------------------------
    public boolean getUserAdministrationAllowed() {
    	return service.getUserAdministrationAllowed(CookieHelper.getAuthTokenValue());
    }
    
    public void setService(DocumentService service) {
        this.service = service;
    }
    
    private String getLoggedInUsername() {
    	User user = service.getUserByToken(CookieHelper.getAuthTokenValue());
    	if (user != null) {
	    	String username = user.getUsername();
	    	if (username != null) {
	    		return username;
	    	} else {
	    		return null;
	    	}
    	} else {
    		return null;
    	}
    }
    // --------------------------------------------
}
