package at.fhj.swd.data;

import java.util.List;

import at.fhj.swd.data.entity.Topic;

public interface TopicDAO {
	
	public List<Topic> findTopicsByCommunityId(String communityId, String search);
	public void insert(Topic newTopic);
}
