package at.fhj.swd.model.service;

import java.util.List;

import at.fhj.swd.model.entity.Community;

/** 
 * Service which handles all requests to community-entities.
 * 
 * @author Group4
 * */

public interface CommunityService {

	public List<Community> getAllSubscribedCommunitiesForUser(String username);
}
