package at.fhj.swd.data.entity;

import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TopicTest {

	private Topic topic;
	
	@Before
	public void setUp() {
		topic = new Topic();
	}
	
	@Test
	public void testGetName() {
		String name = "New Topic";
		topic.setName(name);
		Assert.assertEquals(name, topic.getName());
	}
	
	@Test
	public void testSetName() {
		String name = "my topic";
		topic.setName(name);
		Assert.assertEquals(name, topic.getName());
	}
	
	@Test
	public void testGetText() {
		String text = "some topic text";
		topic.setText(text);
		Assert.assertEquals(text, topic.getText());
	}
	
	@Test
	public void testSetText() {
		String text = "some other topic text";
		topic.setText(text);
		Assert.assertEquals(text, topic.getText());
	}
	
	@Test
	public void testGetCommunity() {
		Community community = new Community();
		topic.setCommunity(community);
		Assert.assertEquals(community, topic.getCommunity());
	}
	
	@Test
	public void testSetCommunity() {
		Community community = new Community();
		topic.setCommunity(community);
		Assert.assertEquals(community, topic.getCommunity());
	}
	
	@Test
	public void testEqualsSameObjectReference() {
		Assert.assertTrue(topic.equals(topic));
	}
	
	@Test 
	public void testEqualsNullObject() {
		Topic nullTopic = null;
		Assert.assertEquals(false, topic.equals(nullTopic));
	}
	
	@Test
	public void testEqualsDifferentType() {
		Community wrongTopicType = new Community();
		Assert.assertEquals(false, topic.equals(wrongTopicType));
	}
	
	@Test
	public void testEqualsId() throws ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Long sameId = new Long(22L);
		
		Field id = Topic.class.getDeclaredField("id");
		id.setAccessible(true);
		
		id.setLong(topic, sameId);
		
		Topic anotherTopicWithSameId = new Topic();
		id.setLong(anotherTopicWithSameId, sameId);
		
		Assert.assertEquals(true, topic.equals(anotherTopicWithSameId));
	}
	
	@Test 
	public void testHashCode() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field id = Topic.class.getDeclaredField("id");
		id.setAccessible(true);
		id.setLong(topic, 25L);
		Long idValueAsBoxedLong = Long.valueOf(id.getLong(topic));
		int expectedHashCode = idValueAsBoxedLong.hashCode();
		
		Assert.assertEquals(expectedHashCode, topic.hashCode());
	}
	
	@Test
	public void testToString() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Long sameId = new Long(22L);
		String text = "topicText";
		String name = "topicName";
		
		Field id = Topic.class.getDeclaredField("id");
		id.setAccessible(true);
		
		id.setLong(topic, sameId);
		topic.setText(text);
		topic.setName(name);
		
		String expectedToString = new StringBuilder().append(topic.getClass().getName()).append(": Id='22', name='").append(name).append("', text='").append(text).append("'!").toString();
		Assert.assertEquals(expectedToString, topic.toString());
	}
}
