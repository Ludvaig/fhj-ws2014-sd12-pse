package at.fhj.swd.controller.view_helper;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

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
	
	private List<Document> documents;
	
    @ManagedProperty("#{documentService}")
    private DocumentService service;
    
    @PostConstruct
    public void init() {
    	this.documents = service.getGlobalDocuments();
    }
    
    public List<Document> getDocuments() {
    	return this.documents;
    }
    
    public void setService(DocumentService service) {
        this.service = service;
    }    
}