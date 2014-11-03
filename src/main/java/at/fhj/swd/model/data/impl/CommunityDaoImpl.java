package at.fhj.swd.model.data.impl;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import at.fhj.swd.model.data.CommunityDao;
import at.fhj.swd.model.entity.Community;
import at.fhj.swd.model.entity.User;

public class CommunityDaoImpl implements CommunityDao {

	@Inject
	public EntityManager em;

	@Override
	public List<Community> getSubscribedCommunitiesForSearchTextOfCurrentUser(
			String searchFieldText, User user) {

		List<Community> communities = em
				.createQuery(
						"select community_id from user_has_community uhc inner join community com on uhc.community_id = com.id where uhc.user_id=:user_id and com.name like '%:com_name%'",
						Community.class).setParameter("user_id", user.getId())
				.setParameter("com_name", searchFieldText).getResultList();

		return communities;
	}

}
