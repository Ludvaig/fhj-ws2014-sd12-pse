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
		User user = userDao.loadUserByToken(authUserToken);
		
		List<Community> communities = null;
		if(user != null){
			communities = communityDao.findSubscribedCommunitiesForSearchTextOfCurrentUser("", user);
		}
		return communities;
	}
	
	public List<Community> getSubscribedCommunitiesForUser(String searchFieldText, String authUserToken){
		User user = userDao.loadUserByToken(authUserToken);
		return communityDao.findSubscribedCommunitiesForSearchTextOfCurrentUser(searchFieldText, user);
	}
	
	public boolean isUserIsLoggedIn() {
		String authToken = CookieHelper.getAuthTokenValue();
		User user = userDao.loadUserByToken(authToken);
		if(user == null) {
			return false;
		}
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
		if(!ensureUserIsAdmin())
			throw new RuntimeException("operation not allowed");
		
		try {
			Community community = new Community();
			community.setName(name);
			community.setVisible(visible);
			communityDao.insertCommunity(community);
		} catch(Exception e) {
			logger.log(Level.SEVERE, "failed to create community", e);
			throw e;
		}		
	}

	@Override
	public void releaseCommunity(long id, boolean release) {
		try {
			Community community = communityDao.findCommunityById(id);
			community.setVisible(release);
			communityDao.update(community);
		} catch(Exception e) {
			logger.log(Level.SEVERE, "failed to release community", e);
			throw e;
		}
	}
	
	public boolean ensureUserIsAdmin() {
		String token = CookieHelper.getAuthTokenValue();
		User user = userDao.loadUserByToken(token);
		if(user == null)
			return false;
		return user.getUsername().endsWith("_a");
	}
}
