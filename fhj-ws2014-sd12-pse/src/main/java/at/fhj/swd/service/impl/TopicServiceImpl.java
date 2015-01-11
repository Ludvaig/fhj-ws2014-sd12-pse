package at.fhj.swd.service.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import at.fhj.swd.data.CommunityDAO;
import at.fhj.swd.data.TopicDAO;
import at.fhj.swd.data.entity.Topic;
import at.fhj.swd.service.TopicService;

@Stateless
public class TopicServiceImpl implements TopicService{

	@Inject
	private transient Logger logger;
	
	@Inject
	private TopicDAO topicDao;
	
	@Inject
	private CommunityDAO communityDao;
	
	@Override
	public List<Topic> getExistingTopics(String communityId, String search) {
		return topicDao.findTopicsByCommunityId(communityId, search);
	}

	@Override
	public void createNewTopic(Long communityId, String newTopicName, String topicText) {
		logger.log(Level.INFO, "Called " + this.getClass().getName() + "::createNewTopic()!");
		
		Topic topic = new Topic();
		topic.setCommunity(communityDao.findCommunityById(communityId));
		topic.setName(newTopicName);
		topic.setText(topicText);
		topicDao.insert(topic);
		
		logger.log(Level.INFO, "Created new topic [" + topic + "]!");
	}
	
}
