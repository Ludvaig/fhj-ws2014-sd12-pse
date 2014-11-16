package at.fhj.swd.model.service.Impl;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import at.fhj.swd.controller.Helpers.CookieHelper;
import at.fhj.swd.model.data.CommunityDao;
import at.fhj.swd.model.data.UserDAO;
import at.fhj.swd.model.entity.Community;
import at.fhj.swd.model.entity.User;
import at.fhj.swd.model.service.CommunityService;

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
	private CommunityDao communityDao;
	
    @Inject
	private UserDAO userDao;
	
	@Override
	public List<Community> getAllSubscribedCommunitiesForUser(String authUserToken) {
		User user = userDao.loadUserByToken(authUserToken);
		
		List<Community> communities = null;
		if(user != null){
			communities = communityDao.getSubscribedCommunitiesForSearchTextOfCurrentUser("", user);
		}
		return communities;
	}
	
	public List<Community> getSubscribedCommunitiesForUser(String searchFieldText, String authUserToken){
		User user = userDao.loadUserByToken(authUserToken);
		return communityDao.getSubscribedCommunitiesForSearchTextOfCurrentUser(searchFieldText, user);
	}

	private void redirectToUserLogin(String redirectionTarget) {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		try {
			ec.redirect(redirectionTarget + ".jsf");
		} catch (IOException ex) {
			Logger.getAnonymousLogger().log(java.util.logging.Level.SEVERE, "We are very sorry for this inconvenience! Simply act as if this never happened.");
		}
	}
	
	public void ensureUserIsLoggedIn() {
		String authToken = CookieHelper.getAuthTokenValue();
		User user = userDao.loadUserByToken(authToken);
		if(user == null) {
			redirectToUserLogin("login");
		}
	}
	
	public Community getCommunityById(long id) {
		Community community = communityDao.getCommunityById(id);
		if (community != null) {
			return community;
		} else {
			redirectToUserLogin("communities");
			return null; // This can never happen because we redirect the user to the communities-side!
		}
	}

	@Override
	public List<Community> getAllCommunities() {
		return communityDao.getAllCommunities();
	}

	@Override
	public void createCommunity(String name, boolean visible) {
		try {
			Community community = new Community();
			community.setName(name);
			community.setVisible(visible);
			communityDao.createCommunity(community);
		} catch(Exception e) {
			logger.log(Level.SEVERE, "failed to create community", e);
			throw e;
		}		
	}

	@Override
	public void releaseCommunity(long id, boolean release) {
		try {
			Community community = communityDao.getCommunityById(id);
			community.setVisible(release);
			communityDao.update(community);
		} catch(Exception e) {
			logger.log(Level.SEVERE, "failed to release community", e);
			throw e;
		}
	}
}
