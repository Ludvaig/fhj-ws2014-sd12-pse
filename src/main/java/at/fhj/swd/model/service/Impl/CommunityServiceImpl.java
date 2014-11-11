package at.fhj.swd.model.service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import at.fhj.swd.controller.Helpers.CookieHelper;
import at.fhj.swd.model.data.CommunityDao;
import at.fhj.swd.model.entity.Community;
import at.fhj.swd.model.entity.User;
import at.fhj.swd.model.service.CommunityService;
import at.fhj.swd.model.service.UserService;

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
	private UserService userService;
	
	@Override
	public List<Community> getAllSubscribedCommunitiesForUser(String token) {
    	User user = userService.getRegisteredUser(token);
    	if(user == null) {
    		return new ArrayList<>();
    	}
		List<Community> communities = communityDao.getSubscribedCommunitiesForSearchTextOfCurrentUser("", user);
		return communities;
	}
	
	public List<Community> getSubscribedCommunitiesForUser(String searchFieldText, String token){
    	User user = userService.getRegisteredUser(token);
    	if(user == null) {
    		return new ArrayList<>();
    	}
		return communityDao.getSubscribedCommunitiesForSearchTextOfCurrentUser(searchFieldText, user);
	}

}
