package at.fhj.swd.data.entity;



import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NewsTest {

	private News news;
	private Date date;
	
	@Before
	public void setup(){
		 news = new News();
		 news.setId((long) 1);
		 news.setTitle("newsTitle");
	}
	
	@Test
	public void testGetId() {
		long id = 1L;
		news.setId(id);
		assertEquals(id,(long) news.getId());
	}
	
	@Test
	public void testSetId(){
		long id = 1000L;
		news.setId(id);
		assertEquals(id, (long) news.getId());
	}
	
	@Test
	public void testVisible(){
		boolean visible = true;
		news.setVisible(visible);;
		assertEquals(visible, news.isVisible());
	}
	
	
	@Test
	public void testSetTitle(){
		String title = "yay";
		news.setTitle(title);
		assertEquals(title, news.getTitle());
	}
	
	@Test
	public void testGetTitle(){
		String title = "yay";
		news.setTitle(title);
		assertEquals(title, news.getTitle());
	}
	
	@Test
	public void testGetContent(){
		String content = "yay_again";
		news.setContent(content);
		assertEquals(content, news.getContent());
	}
	
	@Test
	public void testSetContent(){
		String content = "yay_again";
		news.setContent(content);
		assertEquals(content, news.getContent());
	}
	
	@Test
	public void testSetDate(){
		date = new Date();
		news.setStartdate(date);
		assertEquals(date, news.getStartdate());
	}
	
	@Test
	public void testGetDate(){
		date = new Date();
		news.setStartdate(date);
		assertEquals(date, news.getStartdate());
	}
	
	@Test
	public void testSetUserList(){
		List<User> users = new ArrayList<User>();
		User user = new User();
		User user2 = new User();
		users.add(user);
		users.add(user2);
		news.setUser(users);
		assertEquals(users, news.getUser());
	}
	
	@Test
	public void testGetUserList(){
		List<User> users = new ArrayList<User>();
		User user = new User();
		User user2 = new User();
		users.add(user);
		users.add(user2);
		news.setUser(users);
		assertEquals(users, news.getUser());
	}
	
	@Test
	public void testHashCode()
	{
		News n = new News();
		n.setId(news.getId());
		n.setTitle(news.getTitle());
		Assert.assertTrue(news.hashCode() == n.hashCode());
	}
	
	@Test
	public void testEquality(){
		News n = new News();
		assertTrue(n.equals(n));
	}
	
	@Test
	public void testEqualityNull(){
		news = new News();
		assertFalse(news.equals(null));
	}
	
	@Test
	public void testEquals_WrongType()
	{
		Assert.assertFalse(news.equals(""));
	}
	
	@Test
	public void testEqualityTitle(){
		News n = new News();
		n.setId(news.getId());
		n.setTitle("title");
		assertFalse(news.equals(n));
	}
	
	@Test
	public void testEqualityTitle2(){
		News n = new News();
		n.setId(news.getId());
		n.setTitle(null);
		assertFalse(n.equals(news));
	}
	
	@Test
	public void testEqualityFinal(){
		News n = new News();
		n.setId(news.getId());
		n.setTitle(news.getTitle());
		assertFalse(n.equals(news));
	}

	@Test
	public void testEquals_WrongId()
	{
		News q = new News();
		q.setId((long) 2);
		q.setTitle("title");
		Assert.assertFalse(news.equals(q));
	}
	
	
	@Test
	public void testToString()
	{
		News q = new News();
		q.setTitle("title");
		q.setId((long) 1);
		q.setContent("blabla");
		q.setStartdate(date);
		String expected = "News [id=" + 1 + ", title=" + "title" + ", content=" + "blabla"
				+ ", startdate=" + q.getStartdate() + "]";
		Assert.assertEquals(expected, q.toString());
	}

	
	
	
	
	
	

}
