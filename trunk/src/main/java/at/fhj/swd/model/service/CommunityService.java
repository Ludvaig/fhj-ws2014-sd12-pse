package at.fhj.swd.model.service;

import java.util.List;

import at.fhj.swd.model.entity.Community;
import at.fhj.swd.model.entity.User;

/** 
 * Service which handles all requests to community-entities.
 * 
 * @author Group4
 * */

public interface CommunityService {

	public List<Community> getAllSubscribedCommunitiesForUser(String authUserToken);
	public List <Community> getSubscribedCommunitiesForUser(String searchfieldText, User user);
	public void ensureUserIsLoggedIn();
	public Community getCommunityById(String id);
}
