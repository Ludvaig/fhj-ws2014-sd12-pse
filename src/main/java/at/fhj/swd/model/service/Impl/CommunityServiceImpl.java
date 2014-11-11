package at.fhj.swd.model.service.Impl;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import at.fhj.swd.model.data.CommunityDao;
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
	
	@Override
	public List<Community> getAllSubscribedCommunitiesForUser(User user) {
		List<Community> communities = communityDao.getSubscribedCommunitiesForSearchTextOfCurrentUser("", user);
		return communities;
	}
	
	public List<Community> getSubscribedCommunitiesForUser(String searchFieldText, User user){
		return communityDao.getSubscribedCommunitiesForSearchTextOfCurrentUser(searchFieldText, user);
	}

}
