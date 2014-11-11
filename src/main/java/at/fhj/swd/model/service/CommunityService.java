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

	public List<Community> getAllSubscribedCommunitiesForUser(String token);
	public List <Community> getSubscribedCommunitiesForUser(String searchfieldText, String token);
	
}
