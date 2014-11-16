package at.fhj.swd.model.service.Impl;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import at.fhj.swd.model.data.CommunityDao;
import at.fhj.swd.model.data.TopicDAO;
import at.fhj.swd.model.entity.Topic;
import at.fhj.swd.model.service.TopicService;

@ManagedBean(name="topicService")
@ViewScoped
public class TopicServiceImpl implements TopicService{

	@Inject
	private TopicDAO topicDao;
	
	@Inject
	private CommunityDao communityDao;
	
	@Override
	public List<Topic> getExistingTopics(String communityId, String search) {
		return topicDao.getTopicsByCommunityId(communityId, search);
	}

	@Override
	public void createNewTopic(String communityId, String newTopicName, String topicText) {
		Topic topic = new Topic();
		topic.setCommunity(communityDao.getCommunityById(Long.valueOf(communityId).longValue()));
		topic.setName(newTopicName);
		topic.setText(topicText);
		topicDao.createNewTopic(topic);
		
	}
	
}
