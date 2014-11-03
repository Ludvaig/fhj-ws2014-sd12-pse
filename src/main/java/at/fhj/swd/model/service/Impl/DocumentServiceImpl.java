package at.fhj.swd.model.service.Impl;

import java.io.File;
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

	static String documentHome = "/home/student";
	
	static String globalContext = "global";
	
	@Override
	public List<Document> getGlobalDocuments() {
		return this.getDocuments(globalContext);
	}
	
	private List<Document> getDocuments(final String pathExtension) {
		final File folder = new File(documentHome, pathExtension);
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
}
