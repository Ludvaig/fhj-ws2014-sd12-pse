package at.fhj.swd.model.service;

import java.util.List;

import at.fhj.swd.model.entity.Document;

/** 
 * Interface to service which handles all requests to documents.
 * 
 * @author Group1
 * */
public interface DocumentService {
	public List<Document> getGlobalDocuments();
}
