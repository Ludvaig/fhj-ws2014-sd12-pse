package at.fhj.swd.presentation.viewHelper;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;

import at.fhj.swd.data.entity.Document;
import at.fhj.swd.data.entity.User;
import at.fhj.swd.presentation.helper.CookieHelper;
import at.fhj.swd.service.DocumentService;
import at.fhj.swd.service.UserService;


/**
 * Document-ViewHelper.
 * 
 * @author Group1
 * */

@ManagedBean(name="dtDocumentView")
@ViewScoped
public class DocumentView implements Serializable {

	private static final long serialVersionUID = 6219023243007670413L;
	
	private String selectedDocument;
	
	//TODO: Is this necessary see also getUserSelectedDocument
	private String selectedUserDocument;
	
    @ManagedProperty("#{documentService}")
    private DocumentService service;
    
    @PostConstruct
    public void init() {
    }
    
    // Global ------------------------
    public List<Document> getDocuments() {
    	return this.service.getGlobalDocuments();
    }
    
    //TODO: Upload not working. Before security script everything was working fine. Needs to be checked!
    public void handleFileUpload(FileUploadEvent event) throws IOException {
    	this.service.uploadGlobalDocument(event.getFile().getInputstream(), event.getFile().getFileName());
    }
    
	public void setSelectedDocument(String name) {
    	this.selectedDocument = name;
    }
    
    public String getSelectedDocument() {
    	return this.selectedDocument;
    }
    
    public void setDeleteDocument(String name) {
    	this.service.deleteGlobalDocument(name);
    }
    
	public DefaultStreamedContent getDownload() throws IOException {
    	String name = this.getSelectedDocument();
    	return new DefaultStreamedContent(this.service.downloadGlobalDocument(name), null, name);
    }
	// -----------------------------
	
	
	// User ------------------------
    public List<Document> getUserDocuments() {
    	String username = getLoggedInUsername();
	    if (username != null) {
	    	return this.service.getUserDocuments(username);
    	} else {
    		return null;
    	}
    }
    
    public void handleUserFileUpload(FileUploadEvent event) throws IOException {
    	String username = getLoggedInUsername();
	    if (username != null) {
	    	this.service.uploadUserDocument(username, event.getFile().getInputstream(), event.getFile().getFileName());
	    }
    }
    
    //TODO: Is this necessary 
    public void setSelectedUserDocument(String name) {
    	this.selectedUserDocument = name;
    }
    
    //TODO: Is this necessary 
    public String getSelectedUserDocument() {
    	return this.selectedUserDocument;
    }
    
    public void setDeleteUserDocument(String name) {
    	String username = getLoggedInUsername();
	    if (username != null) {
       		this.service.deleteUserDocument(username, name);
    	}
    }
    
    public DefaultStreamedContent getUserDownload() throws IOException {
    	String username = getLoggedInUsername();
	    if (username != null) {
	    	String name = this.getSelectedUserDocument();
	    	if (name != null) {
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