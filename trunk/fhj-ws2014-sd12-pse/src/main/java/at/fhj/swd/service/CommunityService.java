package at.fhj.swd.service;

import java.util.List;

import at.fhj.swd.data.CommunityDAO;
import at.fhj.swd.data.UserDAO;
import at.fhj.swd.data.entity.Community;

/** 
 * Service which handles all requests to community-entities.
 * 
 * @author Group4, Michael Mayer
 * */

public interface CommunityService {
	public List<Community> getAllCommunities();
	public List<Community> getAllSubscribedCommunitiesForUser(String authUserToken);
	public List <Community> getSubscribedCommunitiesForUser(String searchfieldText, String authUserToken);
	public boolean isUserIsLoggedIn();
	public Community getCommunityById(long id);
	void createCommunity(String name, boolean visible);
	void releaseCommunity(long id, boolean release);
	public boolean ensureUserIsAdmin();
	public void setCommunityDAO(CommunityDAO communityDao);
	public CommunityDAO getCommunityDAO();
	public void setUserDAO(UserDAO userDao);
	public UserDAO getUserDAO();
}