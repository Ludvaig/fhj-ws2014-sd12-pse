package at.fhj.swd.presentation.viewHelper;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.SelectEvent;

import at.fhj.swd.data.entity.Community;
import at.fhj.swd.presentation.helper.CookieHelper;
import at.fhj.swd.service.CommunityService;

/**
 * Community-ViewHelper.
 * 
 * @author Group4
 * */

@ManagedBean(name="dtCommunitiesView")
@ViewScoped
public class CommunityView implements Serializable{

	private static final long serialVersionUID = 6330672338108028518L;

	@Inject
	private Logger logger;
	
	private List<Community> subscribedCommunities = null;
	
	private String searchFieldText = "";
	
	private Community selectedCommunity = null;

	@Inject
    private CommunityService service;
    
    
    @PostConstruct
    public void init() {
    	logger.log(Level.INFO, "Initiliazing " + this.getClass().getName() + " in @PostConstruct!");
    	
    	subscribedCommunities = service.getAllSubscribedCommunitiesForUser(CookieHelper.getAuthTokenValue());
    	if (!service.isUserIsLoggedIn()) {
    		RedirectionTargetHelper.redirectTo(RedirectionTarget.LOGIN);
    	}
    	
    	logger.log(Level.INFO, "Successfully nitiliazed " + this.getClass().getName() + " in @PostConstruct!");
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
    
    public Community getSelectedCommunity() {
		return selectedCommunity;
	}

	public void setSelectedCommunity(Community selectedCommunity) {
		this.selectedCommunity = selectedCommunity;
	}
    
    public String search() {
    	subscribedCommunities = service.getSubscribedCommunitiesForUser(searchFieldText, CookieHelper.getAuthTokenValue());
    	return "communities";
    }
    
    public void onCommunitySelected(SelectEvent object){
    	logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::onCommunitySelected()!");
    	
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("topic.jsf?id=" + selectedCommunity.getId());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    	logger.log(Level.INFO, "Called " + this.getClass().getName() + "::onCommunitySelected()!");
    }
}