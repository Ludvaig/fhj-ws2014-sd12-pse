package at.fhj.swd.model.data;

import java.util.List;

import at.fhj.swd.model.entity.Topic;

public interface TopicDAO {
	
	public List<Topic> getTopicsByCommunityId(String communityId, String search);
	public void createNewTopic(Topic newTopic);
}
