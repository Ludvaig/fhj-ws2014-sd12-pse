package at.fhj.swd.model.service.Impl;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import at.fhj.swd.model.data.CommunityDao;
import at.fhj.swd.model.data.UserDAO;
import at.fhj.swd.model.entity.Community;
import at.fhj.swd.model.entity.User;
import at.fhj.swd.model.service.CommunityService;

/**
 * Community-ViewHelper.
 * 
 * @author Group4
 * */

@ManagedBean(name = "communityService")
@ViewScoped
public class CommunityServiceImpl implements CommunityService {
	
	@Inject
	private CommunityDao communityDao;
	
    @Inject
	private UserDAO userDao;
    
    private User user;
	
	@Override
	public List<Community> getAllSubscribedCommunitiesForUser(String authUserToken) {
		user = userDao.loadUserByToken(authUserToken);
		
		List<Community> communities = null;
		if(user != null){
			communities = communityDao.getSubscribedCommunitiesForSearchTextOfCurrentUser("", user);
		}
		return communities;
	}
	
	public List<Community> getSubscribedCommunitiesForUser(String searchFieldText, String authUserToken){
		user = userDao.loadUserByToken(authUserToken);
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
		if(user == null) {
			redirectToUserLogin("login");
		}
	}
	
	public Community getCommunityById(String id) {
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
		return this.getAllCommunities();
	}

	@Override
	public void createCommunity(Long id, String name, boolean visible) {
		Community community = new Community();
		community.setId(id);
		community.setName(name);
		community.setVisible(visible);
		communityDao.createCommunity(community);
		
	}
}
