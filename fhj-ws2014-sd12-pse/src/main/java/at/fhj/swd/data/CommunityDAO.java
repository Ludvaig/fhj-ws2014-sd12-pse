package at.fhj.swd.data;

import java.util.List;

import at.fhj.swd.data.entity.Community;
import at.fhj.swd.data.entity.User;

public interface CommunityDAO {

	public List<Community> findSubscribedCommunitiesForSearchTextOfCurrentUser(String searchFieldText, User user);
	public Community findCommunityById(long id);
	void insertCommunity(Community community);
	public List<Community> findAllCommunities();
    Community update(Community community);
}
