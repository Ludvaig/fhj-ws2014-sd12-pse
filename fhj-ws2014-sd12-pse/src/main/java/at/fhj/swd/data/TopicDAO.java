package at.fhj.swd.data;

import java.util.List;

import at.fhj.swd.data.entity.Topic;

public interface TopicDAO {
	
	public List<Topic> getTopicsByCommunityId(String communityId, String search);
	public void createNewTopic(Topic newTopic);
}
