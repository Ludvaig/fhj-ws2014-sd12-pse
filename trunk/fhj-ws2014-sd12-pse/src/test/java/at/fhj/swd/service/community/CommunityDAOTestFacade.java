package at.fhj.swd.service.community;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import at.fhj.swd.data.CommunityDAO;
import at.fhj.swd.data.entity.Community;
import at.fhj.swd.data.entity.User;

@Stateless
@Remote(CommunityDAO.class)
public class CommunityDAOTestFacade implements CommunityDAO {

	@EJB(beanName="CommunityDAOImpl")
	private CommunityDAO dao;
	
	@Override
	public List<Community> findSubscribedCommunitiesForSearchTextOfCurrentUser(String searchFieldText, User user) {
		return dao.findSubscribedCommunitiesForSearchTextOfCurrentUser(searchFieldText, user);
	}

	@Override
	public Community findCommunityById(long id) {
		return dao.findCommunityById(id);
	}

	@Override
	public void insertCommunity(Community community) {
		dao.insertCommunity(community);
	}

	@Override
	public List<Community> findAllCommunities() {
		return dao.findAllCommunities();
	}

	@Override
	public Community update(Community community) {
		return dao.update(community);
	}

}
