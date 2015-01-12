package at.fhj.swd.presentation.viewHelper;

import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;

import at.fhj.swd.data.entity.Document;
import at.fhj.swd.presentation.helper.CookieHelper;


/**
 * Document/Admin-ViewHelper.
 * 
 * @author Group3
 * */

@ManagedBean(name="dtDocumentAdminView")
@ViewScoped
public class DocumentAdminView extends DocumentView {
	public List<Document> getDocuments() {
    	return this.service.getAdminDocuments();
    }
	
	public void handleFileUpload(FileUploadEvent event) throws IOException {
    	this.service.uploadAdminDocument(event.getFile().getInputstream(), event.getFile().getFileName());
    }

    public void setDeleteDocument(String name) {
    	this.service.deleteAdminDocument(name);
    }
    
	public DefaultStreamedContent getDownload() throws IOException {
    	String name = this.getSelectedDocument();
    	return new DefaultStreamedContent(this.service.downloadAdminDocument(name), null, name);
    }
	
	public boolean getAdministrationAllowed() {
    	return service.getIsAdministrator(CookieHelper.getAuthTokenValue());
    }
}