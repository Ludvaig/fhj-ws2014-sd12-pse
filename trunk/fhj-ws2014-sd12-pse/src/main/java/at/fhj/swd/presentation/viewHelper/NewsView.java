package at.fhj.swd.presentation.viewHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import at.fhj.swd.data.entity.News;
import at.fhj.swd.service.NewsService;

/**
 * News-ViewHelper.
 * 
 * @author Kamper
 * */

@ManagedBean(name="dtNewsView")
@ViewScoped
public class NewsView implements Serializable{

	private static final long serialVersionUID = 6330672338108028518L;

	private List<News> allNews = null;
	
	private News selectedNews = null;
	
	@Inject
	private FacesContext facesContext;

	@EJB
    private NewsService service;
    
    @PostConstruct
    public void init() {
    	try {
    		allNews = service.getAllNews();    		
    		if (allNews.size() != 0) {
    			selectedNews = allNews.get(0);
    		}
		} catch (Exception e) {
			allNews = new ArrayList<News>();
			String errorMessage = e.getLocalizedMessage();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, ""));
		}
    }
    
    public List<News> getAllNews() {
    	return service.getAllNews();
    }
    
    public void setService(NewsService service) {
    	this.service = service;
    }
    
    public News getSelectedNews() {
		return selectedNews;
	}

	public void setSelectedNews(News selectedNews) {
		this.selectedNews = selectedNews;
	}
}
