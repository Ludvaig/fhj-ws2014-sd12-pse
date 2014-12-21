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
import at.fhj.swd.domain.exceptions.InsufficientUserPriviledgesException;
import at.fhj.swd.presentation.helper.CookieHelper;
import at.fhj.swd.service.CommunityService;

/**
 * Community service implementation.
 * 
 * @author Group4, Michael Mayer
 * */

@Stateless
public class CommunityServiceImpl implements CommunityService {
	
	@Inject
	private Logger logger;
	
	@Inject
	private CommunityDAO communityDao;
	
    @Inject
	private UserDAO userDao;
	
	@Override
	public List<Community> getAllSubscribedCommunitiesForUser(String authUserToken) {
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::getAllSubscribedCommunitiesForUser()!");
		
		User user = userDao.loadUserByToken(authUserToken);
		
		List<Community> communities = null;
		if(user != null){
			communities = communityDao.findSubscribedCommunitiesForSearchTextOfCurrentUser("", user);
		}
		
		logger.log(Level.INFO, "Retrieved communities: " + communities);
		
		return communities;
	}
	
	public List<Community> getSubscribedCommunitiesForUser(String searchFieldText, String authUserToken) {
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::getAllSubscribedCommunitiesForUser()!");
		
		User user = userDao.loadUserByToken(authUserToken);
		
		logger.log(Level.INFO, "Loaded user [username='" + user.getUsername() + "']!");
		
		return communityDao.findSubscribedCommunitiesForSearchTextOfCurrentUser(searchFieldText, user);
	}
	
	public boolean isUserIsLoggedIn() {
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::isUserIsLoggedIn()!");
		
		String authToken = CookieHelper.getAuthTokenValue();
		User user = userDao.loadUserByToken(authToken);
		
		if(user == null) {
			return false;
		}
		
		logger.log(Level.INFO, "Found user [username='" + user.getUsername() + "']!");
		return true;
	}
	
	public Community getCommunityById(long id) {
		Community community = communityDao.findCommunityById(id);
		if (community != null) {
			return community;
		}
		return null;
	}

	@Override
	public List<Community> getAllCommunities() {
		return communityDao.findAllCommunities();
	}

	@Override
	public void createCommunity(String name, boolean visible) {
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::createCommunity()!");
		
		if(!ensureUserIsAdmin())
			throw new InsufficientUserPriviledgesException("User does not have admin rights!");
		
		try {
			Community community = new Community();
			community.setName(name);
			community.setVisible(visible);
			communityDao.insertCommunity(community);
			logger.log(Level.INFO, "Creating community [communityName = '" + community.getName() + "']!");
		} catch(Exception e) {
			logger.log(Level.SEVERE, "failed to create community", e);
			throw e;
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
			throw e;
		}
	}
	
	@Override
	public boolean ensureUserIsAdmin() {
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::ensureUserIsAdmin()!");
		
		String token = CookieHelper.getAuthTokenValue();
		User user = userDao.loadUserByToken(token);
		if(user == null)
			return false;
		
		logger.log(Level.INFO, "Loaded user [" + user + "]!");
		return user.getUsername().endsWith("_a");
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
