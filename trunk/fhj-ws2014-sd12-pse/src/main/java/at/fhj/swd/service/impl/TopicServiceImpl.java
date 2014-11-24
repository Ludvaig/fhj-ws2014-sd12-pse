package at.fhj.swd.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import at.fhj.swd.data.CommunityDAO;
import at.fhj.swd.data.TopicDAO;
import at.fhj.swd.data.entity.Topic;
import at.fhj.swd.service.TopicService;

//@ManagedBean(name="topicService")
//@ViewScoped
@Stateless
public class TopicServiceImpl implements TopicService{

	@Inject
	private TopicDAO topicDao;
	
	@Inject
	private CommunityDAO communityDao;
	
	@Override
	public List<Topic> getExistingTopics(String communityId, String search) {
		return topicDao.findTopicsByCommunityId(communityId, search);
	}

	@Override
	public void createNewTopic(String communityId, String newTopicName, String topicText) {
		Topic topic = new Topic();
		topic.setCommunity(communityDao.findCommunityById(Long.valueOf(communityId).longValue()));
		topic.setName(newTopicName);
		topic.setText(topicText);
		topicDao.insert(topic);
		
	}
	
}
