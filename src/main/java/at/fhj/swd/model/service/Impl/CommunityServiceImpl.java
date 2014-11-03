package at.fhj.swd.model.service.Impl;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import at.fhj.swd.model.data.UserDAO;
import at.fhj.swd.model.entity.Community;
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
	private UserDAO userDao;
	
	@Override
	public List<Community> getAllSubscribedCommunitiesForUser(String username) {
		try{
			return userDao.loadUserByName(username).getSubscribedCommunities();
		}catch(NullPointerException e){
			System.out.println("NullPointerException: User konnte nicht geladen werden.");
		}
		return null;
	}
}
