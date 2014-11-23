package at.fhj.swd.data;

import java.util.List;

import at.fhj.swd.data.entity.Community;
import at.fhj.swd.data.entity.User;

public interface CommunityDAO {

	public List<Community> getSubscribedCommunitiesForSearchTextOfCurrentUser(String searchFieldText, User user);
	public Community getCommunityById(long id);
	void createCommunity(Community community);
	public List<Community> getAllCommunities();
    Community update(Community community);
}
