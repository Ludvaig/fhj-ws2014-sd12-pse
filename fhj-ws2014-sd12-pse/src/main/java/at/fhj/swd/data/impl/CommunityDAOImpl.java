package at.fhj.swd.data.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import at.fhj.swd.data.CommunityDAO;
import at.fhj.swd.data.entity.Community;
import at.fhj.swd.data.entity.User;
import at.fhj.swd.data.exceptions.DAOUpdateFailedException;

/**
 * Data Access Object for Community
 * 
 * @author Group3, Michael Mayer
 * */

public class CommunityDAOImpl implements CommunityDAO {

	@Inject
	private Logger logger;
	
	@Inject
	public EntityManager em;

	@Override
	public List<Community> findSubscribedCommunitiesForSearchTextOfCurrentUser(String searchFieldText, User user) {
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::findSubscribedCommunitiesForSearchTextOfCurrentUser()!");
		
		/** Load limited Community-Entry from Database (Pagination @see: LazyCommunityImpl) */
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
		
		logger.log(Level.INFO, "Successfully created communities: " + communities);
		
		return communities;
	}

	public Community findCommunityById(long id) {
		return em.createQuery("FROM Community com WHERE com.id = :id", Community.class).setParameter("id", Long.valueOf(id)).getSingleResult();
	}

	@Override
	public void insertCommunity(Community community) {
		em.persist(community);
	}

	@Override
	public List<Community> findAllCommunities() {
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
			throw new DAOUpdateFailedException("Updating community [" + community + "] failed!", e);
		}
	}
}
