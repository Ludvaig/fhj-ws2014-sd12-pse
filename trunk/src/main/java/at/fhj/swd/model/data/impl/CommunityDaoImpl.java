package at.fhj.swd.model.data.impl;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import at.fhj.swd.model.data.CommunityDao;
import at.fhj.swd.model.entity.Community;
import at.fhj.swd.model.entity.User;

/**
 * Data Access Object for Community
 * 
 * @author Group3, Michael Mayer
 * */

public class CommunityDaoImpl implements CommunityDao {

	@Inject
	public EntityManager em;

	@Override
	public List<Community> getSubscribedCommunitiesForSearchTextOfCurrentUser(
		String searchFieldText, User user) {
		
		// Load limited Community-Entry from Database (Pagination @see: LazyCommunityImpl)
		List<Community> communities = em
				.createQuery(
						"SELECT com FROM Community com"
						+ " INNER JOIN com.users u"
						+ " WHERE u.id = :user_id "
						+ " AND upper(com.name) LIKE upper(:com_name) "
						+ " ORDER BY com.name",
						Community.class)
							.setParameter("user_id", user.getId())
							.setParameter("com_name", "%" + searchFieldText + "%")
							.getResultList();
		
		return communities;
	}

	public Community getCommunityById(long id) {
		return em.createQuery("FROM Community com WHERE com.id = :id", Community.class).setParameter("id", Long.valueOf(id)).getSingleResult();
	}

	@Override
	public void createCommunity(Community community) {
		em.persist(community);
	}

	@Override
	public List<Community> getAllCommunities() {
		// Load all news from database (Pagination @see: LazyCommunityImpl)
		List<Community> communities = em
				.createQuery(
						"SELECT com FROM Community com",
						Community.class)
							.getResultList();
		
		return communities;		
	}

	@Override
	public Community update(Community community) {
		try {
			em.merge(community);
			return community;
		} catch(Exception e) {
			// should be DAOException in future releases.
			throw e;
		}
	}
}
