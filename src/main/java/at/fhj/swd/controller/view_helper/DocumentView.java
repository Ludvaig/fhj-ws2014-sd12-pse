package at.fhj.swd.controller.view_helper;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;

import at.fhj.swd.model.entity.Document;
import at.fhj.swd.model.service.DocumentService;

/**
 * Document-ViewHelper.
 * 
 * @author Group1
 * */

@ManagedBean(name="dtDocumentView")
@ViewScoped
public class DocumentView implements Serializable {

	private static final long serialVersionUID = 6219023243007670413L;
	
    @ManagedProperty("#{documentService}")
    private DocumentService service;
    
    @PostConstruct
    public void init() {
    }
    
    public List<Document> getDocuments() {
    	return this.service.getGlobalDocuments();
    }
    
    public void handleFileUpload(FileUploadEvent event) throws IOException {
    	this.service.uploadGlobalDocument(event.getFile().getInputstream(), event.getFile().getFileName());
    }
    
    public DefaultStreamedContent getFile() throws IOException {
    	String name = this.getDocuments().get(0).getName();
    	return new DefaultStreamedContent(this.service.downloadGlobalDocument(name), name);
    }
    
    public void setService(DocumentService service) {
        this.service = service;
    }
}