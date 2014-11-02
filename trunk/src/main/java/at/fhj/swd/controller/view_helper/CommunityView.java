package at.fhj.swd.controller.view_helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import at.fhj.swd.model.entity.AuthInfo;
import at.fhj.swd.model.entity.Community;
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

	private List<Community> subscribedCommunities = new ArrayList<Community>();
	
	@ManagedProperty("#{authInfo}")
	private AuthInfo authInfo;
	
    @ManagedProperty("#{communityService}")
    private CommunityService service;
	
    @PostConstruct
    public void init() {
    	subscribedCommunities = service.getAllSubscribedCommunitiesForUser(authInfo.getUsername());
    }
    
    public List<Community> getSubscribedCommunities() {
    	return subscribedCommunities;
    }
    
    public void setAuthInfo(AuthInfo authInfo) {
    	this.authInfo = authInfo;
    }
    
//    public AuthInfo getAuthInfo()
    
    public void setService(CommunityService service) {
    	this.service = service;
    }
}
