package at.fhj.swd.controller.view_helper;

import java.io.Serializable;
import java.net.CookieHandler;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;

import at.fhj.swd.controller.Helpers.CookieHelper;
import at.fhj.swd.model.data.UserDAO;
import at.fhj.swd.model.data.impl.UserDAOImpl;
import at.fhj.swd.model.entity.AuthInfo;
import at.fhj.swd.model.entity.Community;
import at.fhj.swd.model.entity.User;
import at.fhj.swd.model.service.CommunityService;

/**
 * Community-ViewHelper.
 * 
 * @author Group4
 * */

@ManagedBean(name="dtCommunitiesView")
@RequestScoped
public class CommunityView implements Serializable {

	private static final long serialVersionUID = 6330672338108028518L;

	private List<Community> subscribedCommunities = new ArrayList<Community>();
	
    @ManagedProperty("#{communityService}")
    private CommunityService service;
	
    @Inject
	private UserDAO userDao;
    
    @PostConstruct
    public void init() {
    	User user = userDao.loadUserByToken(CookieHelper.getAuthTokenValue());
    	subscribedCommunities = service.getAllSubscribedCommunitiesForUser(user.getUsername());
    }
    
    public List<Community> getSubscribedCommunities() {
    	return subscribedCommunities;
    }
    
    public void setService(CommunityService service) {
    	this.service = service;
    }
}
