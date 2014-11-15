package at.fhj.swd.controller.view_helper;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import at.fhj.swd.model.entity.News;
import at.fhj.swd.model.service.NewsService;

/**
 * Community-ViewHelper.
 * 
 * @author Group4
 * */

@ManagedBean(name="dtNewsAdminView")
@ViewScoped
public class NewsAdminView implements Serializable{

	private static final long serialVersionUID = 6330672338108028518L;

	private List<News> news = null;
	
	
	@Inject
    private NewsService service;
	
	private News selectedNews;
	private String createTitle;
	private String createContent;
    
    
    @PostConstruct
    public void init() {
//    	communities = service.getAllCommunities();
//    	service.ensureUserIsLoggedIn();
    }
    
    public List<News> getNews() {
    	return news;
    }
    
    public News getSelectedNews() {
    	return selectedNews;
    }
    
    public void setSelectedNews(int id) {
    	//TODO
    }
    
    public void setService(NewsService service) {
    	this.service = service;
    }
    public void save() {
    	//TODO
    }
    
    public void setDeleteCommunity(int id) {
    	// TODO: Group3
    	// TODO: Delete community with id
    }
    
    public void create() {
    	// TODO: Group3
    	// TODO: Create community
    	System.out.println("Create called");
    }

	public String getCreateTitle() {
		return createTitle;
	}

	public void setCreateTitle(String createTitle) {
		this.createTitle = createTitle;
	}

	public String getCreateContent() {
		return createContent;
	}

	public void setCreateContent(String createContent) {
		this.createContent = createContent;
	}
   
}
