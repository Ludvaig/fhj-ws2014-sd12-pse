package at.fhj.swd.service.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import at.fhj.swd.data.CommunityDAO;
import at.fhj.swd.data.UserDAO;
import at.fhj.swd.data.entity.Community;
import at.fhj.swd.data.entity.User;
import at.fhj.swd.service.CommunityService;
import at.fhj.swd.service.exceptions.ServiceLayerException;

/**
 * Community service implementation.
 * 
 * @author Group4, Group3
 * */

@Stateless
public class CommunityServiceImpl implements CommunityService {
	
	@Inject
	private transient Logger logger;
	
	@Inject
	private CommunityDAO communityDao;
	
    @Inject
	private UserDAO userDao;
	
	@Override
	public List<Community> getAllSubscribedCommunitiesForUser(String authUserToken) {
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::getAllSubscribedCommunitiesForUser()!");
		
		try {
			User user = userDao.findByToken(authUserToken);
			List<Community> communities = null;
			if(user != null){
				communities = communityDao.findSubscribedCommunitiesForSearchTextOfCurrentUser("", user);
			}		
			logger.log(Level.INFO, "Retrieved communities: " + communities);
			return communities;
		} catch (Exception e) {
			throw new ServiceLayerException("service failed to get all subscribed communities for user token " + authUserToken, e);
		}
	}
	
	public List<Community> getSubscribedCommunitiesForUser(String searchFieldText, String authUserToken) {
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::getAllSubscribedCommunitiesForUser()!");
		
		try {
			User user = userDao.findByToken(authUserToken);
			
			logger.log(Level.INFO, "Loaded user [username='" + user.getUsername() + "']!");
			
			return communityDao.findSubscribedCommunitiesForSearchTextOfCurrentUser(searchFieldText, user);
		}
		catch(Exception e) {
			logger.log(Level.SEVERE, "failed to get subscribed communities for user", e);
			throw new ServiceLayerException("service failed to get subscribed communities for user", e);
		}
	}
	
	public Community getCommunityById(long id) {
		
		try {
			Community community = communityDao.findCommunityById(id);
			if (community != null) {
				return community;
			}
			return null;
		}
		catch(Exception e) {
			throw new ServiceLayerException("service failed to get community by id " + id, e);
		}
	}

	@Override
	public List<Community> getAllCommunities() {
		try {
			return communityDao.findAllCommunities();
		} catch(Exception e) {
			throw new ServiceLayerException("service failed to get all communities", e);
		}
	}

	@Override
	public void createCommunity(String name, boolean visible) {
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::createCommunity()!");
		
		try {
			Community community = new Community();
			community.setName(name);
			community.setVisible(visible);
			communityDao.insertCommunity(community);
			logger.log(Level.INFO, "Creating community [communityName = '" + community.getName() + "']!");
		} catch(Exception e) {
			logger.log(Level.SEVERE, "failed to create community", e);
			throw new ServiceLayerException("service failed to create community", e);
		}		
	}

	@Override
	public void releaseCommunity(long id, boolean release) {
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::releaseCommunity()!");
		
		try {
			Community community = communityDao.findCommunityById(id);
			community.setVisible(release);
			communityDao.update(community);
			logger.log(Level.INFO, "Released community [" + community + "]!");
		} catch(Exception e) {
			logger.log(Level.SEVERE, "failed to release community", e);
			throw new ServiceLayerException("service failed to release community", e);
		}
	}
	
	public void setCommunityDAO(CommunityDAO communityDao) {
		this.communityDao = communityDao;
	}
	
	public CommunityDAO getCommunityDAO() {
		return this.communityDao;
	}
	
	public void setUserDAO(UserDAO userDao) {
		this.userDao = userDao;
	}
	
	public UserDAO getUserDAO() {
		return this.userDao;
	}
}
