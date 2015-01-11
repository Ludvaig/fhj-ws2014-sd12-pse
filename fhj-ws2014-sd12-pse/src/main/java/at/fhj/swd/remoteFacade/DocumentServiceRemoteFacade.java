package at.fhj.swd.remoteFacade;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import at.fhj.swd.data.entity.Document;
import at.fhj.swd.data.entity.User;
import at.fhj.swd.service.DocumentService;

@Stateless
@Remote(DocumentService.class)
public class DocumentServiceRemoteFacade implements DocumentService {

	@EJB(beanName="DocumentServiceImpl")
	private DocumentService service;

	@Override
	public List<Document> getGlobalDocuments() {
		return service.getGlobalDocuments();
	}

	@Override
	public void uploadGlobalDocument(InputStream source, String name)
			throws IOException {
		service.uploadGlobalDocument(source, name);
	}

	@Override
	public void deleteGlobalDocument(String name) {
		service.deleteGlobalDocument(name);
	}

	@Override
	public InputStream downloadGlobalDocument(String name) throws IOException {
		return service.downloadGlobalDocument(name);
	}

	@Override
	public List<Document> getCommunityDocuments(String community) {
		return service.getCommunityDocuments(community);
	}

	@Override
	public void uploadCommunityDocument(String community, InputStream source,
			String name) throws IOException {
		service.uploadCommunityDocument(community, source, name);
	}

	@Override
	public InputStream downloadCommunityDocument(String community, String name)
			throws IOException {
		return service.downloadCommunityDocument(community, name);
	}

	@Override
	public void deleteCommunityDocument(String community, String name) {
		service.deleteCommunityDocument(community, name);
	}

	@Override
	public List<Document> getUserDocuments(String user) {
		return service.getUserDocuments(user);
	}

	@Override
	public void uploadUserDocument(String user, InputStream source, String name)
			throws IOException {
		service.uploadUserDocument(user, source, name);
	}

	@Override
	public InputStream downloadUserDocument(String user, String name)
			throws IOException {
		return service.downloadUserDocument(user, name);
	}

	@Override
	public void deleteUserDocument(String user, String name) {
		service.deleteUserDocument(user, name);
	}

	@Override
	public boolean getAdministrationAllowed(String token) {
		return service.getAdministrationAllowed(token);
	}

	@Override
	public User getUserByToken(String token) {
		return service.getUserByToken(token);
	}

	@Override
	public boolean getUserAdministrationAllowed(String token) {
		return service.getUserAdministrationAllowed(token);
	}

	@Override
	public void deleteGlobalDocuments() {
		service.deleteGlobalDocuments();
	}
}
