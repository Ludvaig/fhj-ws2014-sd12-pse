package at.fhj.swd.model.service;

import java.util.List;

import at.fhj.swd.model.entity.Community;

/** 
 * Service which handles all requests to community-entities.
 * 
 * @author Group4
 * */

public interface CommunityService {
	public List<Community> getAllCommunities();
	public List<Community> getAllSubscribedCommunitiesForUser(String authUserToken);
	public List <Community> getSubscribedCommunitiesForUser(String searchfieldText, String authUserToken);
	public void ensureUserIsLoggedIn();
	public Community getCommunityById(String id);
	void createCommunity(Long id, String name, boolean visible);
}
