package at.fhj.swd.controller.view_helper;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.NotSupportedException;

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
	
	@Inject
    private CommunityService communityService;
	
	@Inject
	private FacesContext facesContext;
	
	private String communityName;
    
    @PostConstruct
    public void init() {
    	communities = communityService.getAllCommunities();
    	//communityService.ensureUserIsLoggedIn();
    }
    
    public List<Community> getCommunities() {
    	return communities;
    }
    
    public void setDeleteCommunity(int id) throws NotSupportedException {
    	throw new NotSupportedException();
    }
    
    public void setReleaseCommunity(int id) {
    	try {
    		Long communityId = new Long(id);
    		communityService.releaseCommunity(communityId, true);
    		
    		// redirection.
    		ExternalContext ec = facesContext.getExternalContext();
    	    ec.redirect(ec.getRequestContextPath() + "/fhj-ws2014-sd12-pse/admin/community_list.jsf");
    	} catch(Exception e) {
    		
    	}
    }
    
    public void setCreateCommunity(String name) {
    	this.communityName = name;
    }
    
    public void onCreateCommunity(SelectEvent object) {
    	try {
    		communityService.createCommunity(null, communityName, false);
    	} catch(Exception e) {
    		
    	}
    }
}
