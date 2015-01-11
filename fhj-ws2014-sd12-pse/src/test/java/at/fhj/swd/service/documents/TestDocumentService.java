package at.fhj.swd.service.documents;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import at.fhj.swd.data.entity.Document;
import at.fhj.swd.service.DocumentService;
import at.fhj.swd.service.impl.DocumentServiceImpl;

public class TestDocumentService {
	private static DocumentService service;
	private static String TEST_FILE_NAME = "DocumentTest.txt";
	private static String TEST_COMMUNITY_NAME = "TestCom"; 
	private static String TEST_USER_NAME = "TestUser"; 
	private static String text;
	private static InputStream stream;
	
	@Before
	public void setup()
	{
		service = new DocumentServiceImpl();
		
		text = "This is a test text for the JUnit test suite of the document service!";
		stream = new ByteArrayInputStream(text.getBytes());
		
		//Delete files of last tests (if they are there) 
		
	}
	
	@After
	public void teardown()
	{
		try 
		{
			stream.close();
		} catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}

	@Test
	public void testGlobalUpload() {
		try {
			//Upload file
			service.uploadGlobalDocument(stream, TEST_FILE_NAME);
			
			//Download file 
			byte[] textBytes = text.getBytes(); 
			byte[] textCompBytes = new byte[1024]; 
			
			InputStream streamComp = service.downloadGlobalDocument(TEST_FILE_NAME);
			int lenText = textBytes.length; 
			int lenTextComp = streamComp.read(textCompBytes);
			
			assertEquals(lenText, lenTextComp);
			
			for (int i = 0; i < lenText; i++)
				if (textBytes[i] != textCompBytes[i])
					fail("It is not the same file");
			
			List<Document> listOfDocs = service.getGlobalDocuments(); 
			boolean docExists = false; 
			for (Document doc : listOfDocs)
				if (doc.getName().equals(TEST_FILE_NAME))
				{
					docExists = true; 
					break; 
				}
			
			if (!docExists) 
				fail("Document not in list of documents!"); 
			
			//Remove file
			service.deleteGlobalDocument(TEST_FILE_NAME);
			
		} catch (IOException ex) {
			fail("Unexpected error: "+ex.getMessage());
		}
	}
	
	@Test
	public void testCommunityDocuments()
	{
		try {
			//Upload file
			service.uploadCommunityDocument(TEST_COMMUNITY_NAME, stream, TEST_FILE_NAME);
			
			//Download file 
			byte[] textBytes = text.getBytes(); 
			byte[] textCompBytes = new byte[1024]; 
			
			InputStream streamComp = service.downloadCommunityDocument(TEST_COMMUNITY_NAME, TEST_FILE_NAME);
			int lenText = textBytes.length; 
			int lenTextComp = streamComp.read(textCompBytes);
			
			assertEquals(lenText, lenTextComp);
			
			for (int i = 0; i < lenText; i++)
				if (textBytes[i] != textCompBytes[i])
					fail("It is not the same file");
			
			List<Document> listOfDocs = service.getCommunityDocuments(TEST_COMMUNITY_NAME);
			boolean docExists = false; 
			for (Document doc : listOfDocs)
				if (doc.getName().equals(TEST_FILE_NAME))
				{
					docExists = true; 
					break; 
				}
			
			if (!docExists) 
				fail("Document not in list of documents!"); 
			
			//Remove file
			service.deleteCommunityDocument(TEST_COMMUNITY_NAME, TEST_FILE_NAME);
			
		} catch (IOException ex) {
			fail("Unexpected error: "+ex.getMessage());
		}
	}
	
	@Test
	public void testUserDocuments()
	{
		try {
			//Upload file
			service.uploadUserDocument(TEST_USER_NAME, stream, TEST_FILE_NAME);
			
			//Download file 
			byte[] textBytes = text.getBytes(); 
			byte[] textCompBytes = new byte[1024]; 
			
			InputStream streamComp = service.downloadUserDocument(TEST_USER_NAME, TEST_FILE_NAME);
			int lenText = textBytes.length; 
			int lenTextComp = streamComp.read(textCompBytes);
			
			assertEquals(lenText, lenTextComp);
			
			for (int i = 0; i < lenText; i++)
				if (textBytes[i] != textCompBytes[i])
					fail("It is not the same file");
			
			List<Document> listOfDocs = service.getUserDocuments(TEST_USER_NAME);
			boolean docExists = false; 
			for (Document doc : listOfDocs)
				if (doc.getName().equals(TEST_FILE_NAME))
				{
					docExists = true; 
					break; 
				}
			
			if (!docExists) 
				fail("Document not in list of documents!"); 
			
			//Remove file
			service.deleteUserDocument(TEST_USER_NAME, TEST_FILE_NAME);
			
		} catch (IOException ex) {
			fail("Unexpected error: "+ex.getMessage());
		}
	}	
}
/*
	public List<Document> getGlobalDocuments() {
	public void uploadGlobalDocument(InputStream source, final String name) throws IOException {
	public InputStream downloadGlobalDocument(final String name) throws IOException {
	public void deleteGlobalDocument(final String name) {
	public List<Document> getCommunityDocuments(final String community) {
	public void uploadCommunityDocument(final String community, InputStream source, final String name) throws IOException {
	public InputStream downloadCommunityDocument(final String community, final String name) throws IOException {
	@Override
	public void deleteCommunityDocument(final String community, final String name) {
	public List<Document> getUserDocuments(final String user) {
	public void uploadUserDocument(final String user, InputStream source, final String name) throws IOException {
	public InputStream downloadUserDocument(final String user, final String name) throws IOException {
	public void deleteUserDocument(final String user, final String name) {
	private List<Document> getDocuments(final String path) {
	private String getDocumentHome() {
	private String getGlobalPath() {
	private String getCommunityPath(final String community) {
	private String concatPath(final String prefix, final String postfix) {
	private void deleteDocument(final String name, final String path) {

	private void uploadDocument(InputStream source, final String name, final String path) throws IOException {
	public InputStream downloadDocument(final String name, final String path) throws IOException {
	public boolean getUserAdministrationAllowed(String token) {
	public User getUserByToken(String token) {
 */