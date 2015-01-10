package at.fhj.swd.presentation.viewHelper;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.NotSupportedException;

import at.fhj.swd.data.entity.Community;
import at.fhj.swd.data.entity.User;
import at.fhj.swd.presentation.helper.CookieHelper;
import at.fhj.swd.service.CommunityService;
import at.fhj.swd.service.UserService;
import at.fhj.swd.service.exceptions.ServiceLayerException;

/**
 * Community Administration - ViewHelper.
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
	
	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private UserService userService;
	
	@Inject
	private FacesContext facesContext;
	
	private String createCommunity;
	private long releaseCommunity;
	private boolean userHasAccess;

	@PostConstruct
    public void init() {
    	// ensure administrator.
		ensureUserHasAccess();
    	
    	if(getUserHasAccess()) {
    		communities = communityService.getAllCommunities();
    	}
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
    
	public boolean getUserHasAccess() {
		return userHasAccess;
	}

	public void setUserHasAccess(boolean userHasAccess) {
		this.userHasAccess = userHasAccess;
	}
    
    public void create() {
    	System.out.println("create");
    	
    	if(!getUserHasAccess()) {
    		facesContext.addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_WARN, "You are not signed in as Administrator.", null));
    		setCreateCommunity(null);
    	}
    	else {
        	try {
        		// create community.
        		communityService.createCommunity(createCommunity, false);
        		
        		// redirection.
        		ExternalContext ec = facesContext.getExternalContext();
        		logger.info("path: " + ec.getRequestContextPath() + "/admin/community_list.jsf");
        	    ec.redirect(ec.getRequestContextPath() + "/admin/community_list.jsf");
        	} catch(ServiceLayerException e) {
    			facesContext.addMessage(null, 
    					new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create community.", null));
        	} catch(IOException e) {
        		facesContext.addMessage(null, 
    					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Redirection failed", null));
        	}
    	}
    }
    
    public void release() {
    	
    	if(!getUserHasAccess()) {
    		facesContext.addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_WARN, "You are not signed in as Administrator.", null));
    		setCreateCommunity(null);
    		return;
    	}
    	
    	try {
    		Long communityId = new Long(releaseCommunity);
    		Community community = communityService.getCommunityById(communityId.longValue());
    		communityService.releaseCommunity(communityId, !community.isVisible());
    		
    		// redirection.
    		ExternalContext ec = facesContext.getExternalContext();
    		logger.info("path: " + ec.getRequestContextPath() + "/admin/community_list.jsf");
    	    ec.redirect(ec.getRequestContextPath() + "/admin/community_list.jsf");
    	} catch(ServiceLayerException e) {
    		facesContext.addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to release community.", null));    		
    	} catch(IOException e) {
    		facesContext.addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Redirection failed", null));
    	}
    }
    
    private void ensureUserHasAccess() {
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::ensureUserIsAdmin()!");
		
		String token = CookieHelper.getAuthTokenValue();
		User user = userService.getRegisteredUser(token);
		if(user == null) {
			setUserHasAccess(false);
		} else {
			boolean hasAccess = user.getUsername().endsWith("_a");
			logger.log(Level.INFO, "Loaded user [" + user + "] hasAccess [" + hasAccess +"]");
			setUserHasAccess(hasAccess);
		}
    }
}
