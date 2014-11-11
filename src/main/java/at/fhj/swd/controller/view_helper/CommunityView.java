package at.fhj.swd.controller.view_helper;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.SelectEvent;

import at.fhj.swd.controller.Helpers.CookieHelper;
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
public class CommunityView implements Serializable{

	private static final long serialVersionUID = 6330672338108028518L;

	private List<Community> subscribedCommunities = null;
	
	private String searchFieldText = "";
	
	private Community selectedCommunity = null;

	@ManagedProperty("#{communityService}")
    private CommunityService service;
	
    @Inject
	private UserDAO userDao;
    
    private User user;
    
    @PostConstruct
    public void init() {
    	user = userDao.loadUserByToken(CookieHelper.getAuthTokenValue());
    	subscribedCommunities = service.getAllSubscribedCommunitiesForUser(user);
    	if(user == null){
    		FacesContext fc = FacesContext.getCurrentInstance();
    		ExternalContext ec = fc.getExternalContext();
    		try {
    			ec.redirect("login.jsf");
    		} catch (IOException ex) {
    		        
    		}
    	}
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
    	subscribedCommunities = service.getSubscribedCommunitiesForUser(searchFieldText, user);
    	return "communities";
    }
    
    public void onCommunitySelected(SelectEvent object){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("topic.jsf?id=" + selectedCommunity.getId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
