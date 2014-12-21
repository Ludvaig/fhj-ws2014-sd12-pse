package at.fhj.swd.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import at.fhj.swd.data.TopicDAO;
import at.fhj.swd.data.entity.Topic;

@Stateless
@Remote(TopicDAO.class)
public class TopicDAOTestFacade implements TopicDAO {

	@EJB(beanName="TopicDAOImpl")
	private TopicDAO dao;
	
	@Override
	public List<Topic> findTopicsByCommunityId(String communityId, String search) {
		return dao.findTopicsByCommunityId(communityId, search);
	}

	@Override
	public void insert(Topic newTopic) {
		dao.insert(newTopic);
	}
}
