package at.fhj.swd.data.dao.community;

import java.util.ArrayList;
import java.util.List;

import at.fhj.swd.data.CommunityDAO;
import at.fhj.swd.data.entity.Community;
import at.fhj.swd.data.entity.User;

/** 
 * Test implementation for component testing for CommunityDAO.
 * */
public class CommunityDAOAlternativeImpl implements CommunityDAO {

	private static List<Community> communities = new ArrayList<Community>();
	
	@Override
	public List<Community> findSubscribedCommunitiesForSearchTextOfCurrentUser(String searchFieldText, User user) {
		List<Community> subscribedCommunitiesForUserWithSearchText = new ArrayList<Community>();
		
		for (Community com : communities) {
			if (com.getName().contains(searchFieldText)) {
				subscribedCommunitiesForUserWithSearchText.add(com);
			}
		}
		
		return subscribedCommunitiesForUserWithSearchText;
	}

	@Override
	public Community findCommunityById(long id) {
		for (Community com : communities) {
			if (com.getId() ==  id)
				return com;
		}
		
		return null;
	}

	@Override
	public void insertCommunity(Community community) {
		communities.add(community);
	}

	@Override
	public List<Community> findAllCommunities() {
		return communities;
	}

	@Override
	public void update(Community community) {
		for (Community com : communities) {
			if (com.equals(community)) {
				communities.remove(com);
				communities.add(community);
			}
		}
	}
}
