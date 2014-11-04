package at.fhj.swd.controller.view_helper;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import at.fhj.swd.controller.Helpers.CookieHelper;
import at.fhj.swd.model.data.CommunityDao;
import at.fhj.swd.model.data.UserDAO;
import at.fhj.swd.model.entity.Community;
import at.fhj.swd.model.entity.User;
import at.fhj.swd.model.service.CommunityService;

/**
 * Community-ViewHelper.
 * 
 * @author Group4
 * */

@ManagedBean(name="dtCommunitiesView")
@ViewScoped
public class CommunityView implements Serializable {

	private static final long serialVersionUID = 6330672338108028518L;

	private List<Community> subscribedCommunities = null;
	
	private String searchFieldText = "";
	
    @ManagedProperty("#{communityService}")
    private CommunityService service;
	
    @Inject
	private UserDAO userDao;
    
    @Inject
    private CommunityDao communityDao;
    
    private User user;
    
    
    @PostConstruct
    public void init() {
    	user = userDao.loadUserByToken(CookieHelper.getAuthTokenValue());
    	subscribedCommunities = service.getAllSubscribedCommunitiesForUser(user.getUsername());
    }
    
    public List<Community> getSubscribedCommunities() {
    	return subscribedCommunities;
    }
    
    public String getSearchFieldText() {
    	return searchFieldText;
    }
    
    public void setSearchFieldText(String searchFieldText) {
    	this.searchFieldText = searchFieldText;
    }
    
    public void setService(CommunityService service) {
    	this.service = service;
    }
    
    public String search() {
    	subscribedCommunities = communityDao.getSubscribedCommunitiesForSearchTextOfCurrentUser(searchFieldText, user);
    	return "communities";
    }
}
