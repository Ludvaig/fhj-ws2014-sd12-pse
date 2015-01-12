package at.fhj.swd.presentation.viewHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	
	@Inject
	private transient Logger logger;

	private List<News> allNews = null;
	
	private News selectedNews = null;
	
	@Inject
	private FacesContext facesContext;

	@Inject
    private NewsService service;
    
    @PostConstruct
    public void init() {
		logger.log(Level.INFO, "Initiliazing " + this.getClass().getName() + " in @PostConstruct!");
    	try {
    		allNews = service.getAllNews();    		
    		if (allNews.size() != 0) {
    			selectedNews = allNews.get(0);
    		logger.log(Level.INFO, "Successfully initiliazed " + this.getClass().getName() + " in @PostConstruct!");
    		}
		} catch (Exception e) {
			allNews = new ArrayList<News>();
			String errorMessage = e.getLocalizedMessage();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, ""));
			logger.log(Level.INFO, "NOT Successfully initiliazed " + this.getClass().getName() + " in @PostConstruct!");
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
