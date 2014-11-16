package at.fhj.swd.controller.view_helper;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.NotSupportedException;

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
	private Logger logger;
	
	@Inject
    private CommunityService communityService;
	
	@Inject
	private FacesContext facesContext;
	
	private String createCommunity;
	private long releaseCommunity;
    
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
    
    public long getReleaseCommunity() {
    	return releaseCommunity;
    }
    
    public void setReleaseCommunity(long id) {
    	this.releaseCommunity = id;
    }
    
    public String getCreateCommunity() {
    	return this.createCommunity;
    }
    
    public void setCreateCommunity(String name) {
    	this.createCommunity = name;
    }
    
    public void create() {
    	System.out.println("create");
    	try {
    		communityService.createCommunity(createCommunity, false);
    		
    		// redirection.
    		ExternalContext ec = facesContext.getExternalContext();
    		logger.info("path: " + ec.getRequestContextPath() + "/admin/community_list.jsf");
    	    ec.redirect(ec.getRequestContextPath() + "/admin/community_list.jsf");
    	} catch(Exception e) {
    		if(e.getMessage().equals("operation not allowed")) {
    			facesContext.addMessage(null, 
    					new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create community. You are not signed in as Administrator.", null));
    		} else {
    			facesContext.addMessage(null, 
    					new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create community", null));
    		}
    	}
    }
    
    public void release() {
    	try {
    		Long communityId = new Long(releaseCommunity);
    		Community community = communityService.getCommunityById(communityId.longValue());
    		communityService.releaseCommunity(communityId, !community.isVisible());
    		
    		// redirection.
    		ExternalContext ec = facesContext.getExternalContext();
    		logger.info("path: " + ec.getRequestContextPath() + "/admin/community_list.jsf");
    	    ec.redirect(ec.getRequestContextPath() + "/admin/community_list.jsf");
    	} catch(Exception e) {
    		facesContext.addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to release community", null));    		
    	}
    }
}
