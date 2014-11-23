package at.fhj.swd.model.data.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import at.fhj.swd.model.data.TopicDAO;
import at.fhj.swd.model.entity.Topic;
@Stateless
public class TopicDAOImpl implements TopicDAO{

	@Inject
	EntityManager em;
	
	
	@Override
	public List<Topic> getTopicsByCommunityId(String communityId, String search) {
		List<Topic> topic = em
				.createQuery(
						"SELECT topic FROM Topic topic"
						+ " WHERE COMMUNITY_ID = :communityId "
						+ " AND upper(name) LIKE upper(:search)"
						+ " ORDER by date desc",
						Topic.class)
							.setParameter("communityId", communityId)
							.setParameter("search", "%" + search + "%")
							.getResultList();
		return topic;
	}

	@Override
	public void createNewTopic(Topic newTopic) {
		em.persist(newTopic);
	}

}
