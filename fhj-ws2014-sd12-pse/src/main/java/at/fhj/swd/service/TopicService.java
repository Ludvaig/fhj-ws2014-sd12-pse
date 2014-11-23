package at.fhj.swd.service;

import java.util.List;

import at.fhj.swd.data.entity.Topic;

public interface TopicService {
	
	public List<Topic> getExistingTopics(String communityId, String search);
	public void createNewTopic(String communityId, String newTopicName, String topicText);

}
