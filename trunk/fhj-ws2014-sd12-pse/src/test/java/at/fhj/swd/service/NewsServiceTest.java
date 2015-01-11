package at.fhj.swd.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import at.fhj.swd.data.NewsDAO;
import at.fhj.swd.data.entity.News;
import at.fhj.swd.service.mock.NewsDAOStub;

public class NewsServiceTest {

	private NewsDAO dao;
	private News news;
	private Date date;
	
	@Before
	public void setUp() throws Exception {
		dao = new NewsDAOStub();
		news = new News();
		date = new Date();
	}

	@Test
	public void testGetAllNews() {
		dao.updateNews(news);
		assertEquals(dao.getAllNews().size(), 1);
	}

	@Test
	public void testPostNews() {
		news.setContent("blabla");
		news.setId((long)1);
		news.setStartdate(date);
		news.setTitle("bla");
		news.setVisible(true);
		dao.updateNews(news);
		assertEquals(dao.getNewsById(1), news);
	}

	@Test
	public void testUpdateNews() {
		News n2 = new News();
		n2.setId((long) 2);
		dao.updateNews(n2);
		assertEquals(dao.getNewsById((long) 2), n2);
	}

	@Test
	public void testFindNewsById() {
		news.setId((long) 3);
		dao.updateNews(news);
		assertEquals(news, dao.getNewsById((long) 3));
		
	}

}
