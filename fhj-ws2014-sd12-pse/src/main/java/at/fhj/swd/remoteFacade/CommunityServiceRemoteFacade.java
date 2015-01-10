package at.fhj.swd.remoteFacade;

import java.util.List;

import javax.ejb.Remote;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

import at.fhj.swd.data.CommunityDAO;
import at.fhj.swd.data.UserDAO;
import at.fhj.swd.data.entity.Community;
import at.fhj.swd.service.CommunityService;

@Any
@Remote(CommunityService.class)
public class CommunityServiceRemoteFacade implements CommunityService {

	@Inject
	private CommunityService communityService;
	
	@Override
	public List<Community> getAllCommunities() {
		return communityService.getAllCommunities();
	}

	@Override
	public List<Community> getAllSubscribedCommunitiesForUser(
			String authUserToken) {
		return communityService.getAllSubscribedCommunitiesForUser(authUserToken);
	}

	@Override
	public List<Community> getSubscribedCommunitiesForUser(
			String searchfieldText, String authUserToken) {
		return communityService.getSubscribedCommunitiesForUser(searchfieldText, authUserToken);
	}

	@Override
	public boolean isUserIsLoggedIn() {
		return communityService.isUserIsLoggedIn();
	}

	@Override
	public Community getCommunityById(long id) {
		return communityService.getCommunityById(id);
	}

	@Override
	public void createCommunity(String name, boolean visible) {
		communityService.createCommunity(name, visible);
	}

	@Override
	public void releaseCommunity(long id, boolean release) {
		communityService.releaseCommunity(id, release);
	}

	@Override
	public void setCommunityDAO(CommunityDAO communityDao) {
		communityService.setCommunityDAO(communityDao);
	}

	@Override
	public CommunityDAO getCommunityDAO() {
		return communityService.getCommunityDAO();
	}

	@Override
	public void setUserDAO(UserDAO userDao) {
		communityService.setUserDAO(userDao);
	}

	@Override
	public UserDAO getUserDAO() {
		return communityService.getUserDAO();
	}

}
