package at.fhj.swd.model.service.Impl;

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
	
	@Override
	public List<Document> getGlobalDocuments() {
		List<Document> docs = new ArrayList<>();
		Document doc1 = new Document();
		doc1.setName("asdf.pdf");
		
		docs.add(doc1);
		
		return docs;
	}
}
