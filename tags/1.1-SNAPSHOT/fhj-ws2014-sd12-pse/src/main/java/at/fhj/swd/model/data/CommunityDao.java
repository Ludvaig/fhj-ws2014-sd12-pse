package at.fhj.swd.model.data;

import java.util.List;

import at.fhj.swd.model.entity.Community;
import at.fhj.swd.model.entity.User;

public interface CommunityDao {

	public List<Community> getSubscribedCommunitiesForSearchTextOfCurrentUser(String searchFieldText, User user);
	public Community getCommunityById(long id);
	void createCommunity(Community community);
	public List<Community> getAllCommunities();
    Community update(Community community);
}
