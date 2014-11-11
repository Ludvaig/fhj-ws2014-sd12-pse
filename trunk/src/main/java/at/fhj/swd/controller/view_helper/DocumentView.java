package at.fhj.swd.controller.view_helper;

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

import at.fhj.swd.controller.Helpers.CookieHelper;
import at.fhj.swd.model.entity.Document;
import at.fhj.swd.model.service.DocumentService;
import at.fhj.swd.model.service.UserService;


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
    	String username = service.getUserByToken(CookieHelper.getAuthTokenValue()).getUsername();
	    return this.service.getUserDocuments(username);
    }
    
    //TODO: Upload not working. Before security script everything was working fine. Needs to be checked!
    //Also tried to use HandleUserFileUpload as function name ==> also not working
    public void setHandleUserFileUpload(FileUploadEvent event) throws IOException {
    	String username = service.getUserByToken(CookieHelper.getAuthTokenValue()).getUsername();
    	this.service.uploadUserDocument(username, event.getFile().getInputstream(), event.getFile().getFileName());
    }
    
    public void setSelectedUserDocument(String name) {
    	this.selectedUserDocument = name;
    }
    
    public String getSelectedUserDocument() {
    	return this.selectedUserDocument;
    }
    
    public void setDeleteUserDocument(String name) {
    	String username = service.getUserByToken(CookieHelper.getAuthTokenValue()).getUsername();
    	if(username != null) {
       		this.service.deleteUserDocument(username, name);
    	}
    }
    
    public DefaultStreamedContent getUserDownload() throws IOException {
    	String username = service.getUserByToken(CookieHelper.getAuthTokenValue()).getUsername();
    	String name = this.getSelectedDocument();
    	return new DefaultStreamedContent(this.service.downloadUserDocument(username, name), null, name);
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
    // --------------------------------------------
}