package at.fhj.swd.data.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import at.fhj.swd.data.TopicDAO;
import at.fhj.swd.data.entity.Topic;

@Stateless
public class TopicDAOImpl implements TopicDAO{

	@Inject
	private Logger logger;
	
	@Inject
	EntityManager em;	
	
	@Override
	public List<Topic> findTopicsByCommunityId(String communityId, String search) {
		logger.log(Level.INFO, "Called " + this.getClass().getName() + "::findTopicsByCommunityId()!");
		
		List<Topic> topics = em
				.createQuery(
						"SELECT topic FROM Topic topic"
						+ " WHERE COMMUNITY_ID = :communityId "
						+ " AND upper(name) LIKE upper(:search)"
						+ " ORDER by date desc",
						Topic.class)
							.setParameter("communityId", communityId)
							.setParameter("search", "%" + search + "%")
							.getResultList();
		
		logger.log(Level.INFO, "Created topics: " + topics);
		
		return topics;
	}

	@Override
	public void insert(Topic newTopic) {
		em.persist(newTopic);
	}
}