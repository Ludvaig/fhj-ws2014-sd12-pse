package at.fhj.swd.controller.view_helper;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import at.fhj.swd.model.entity.Community;
import at.fhj.swd.model.service.CommunityService;

/**
 * Community-ViewHelper.
 * 
 * @author Group4
 * */

@ManagedBean(name="dtCommunitiesAdminView")
@ViewScoped
public class CommunityAdminView implements Serializable{

	private static final long serialVersionUID = 6330672338108028518L;

	private List<Community> communities = null;
	
	@ManagedProperty("#{communityService}")
    private CommunityService service;
    
    
    @PostConstruct
    public void init() {
    	communities = service.getAllCommunities();
    	service.ensureUserIsLoggedIn();
    }
    
    public List<Community> getCommunities() {
    	return communities;
    }
    
    public void setService(CommunityService service) {
    	this.service = service;
    }
    
    public void setDeleteCommunity(int id) {
    	// TODO: Group3
    	// TODO: Delete community with id
    }
    
    public void setReleaseCommunity(int id) {
    	// TODO: Group3
    	// TODO: Release community with id
    }
    
    public void setCreateCommunity(String name) {
    	// TODO: Group3
    	// TODO: Create community
    }
    
    public void onCreateCommunity(SelectEvent object) {
		// TODO: Group3
    	// TODO: Navigate to create view
    }
}
