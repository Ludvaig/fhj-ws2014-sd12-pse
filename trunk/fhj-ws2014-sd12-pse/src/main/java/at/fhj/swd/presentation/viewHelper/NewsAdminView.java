package at.fhj.swd.presentation.viewHelper;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import at.fhj.swd.data.entity.News;
import at.fhj.swd.service.NewsService;

/**
 * News-ViewHelper.
 * 
 * @author Group3
 * */

@ManagedBean(name="dtNewsAdminView")
@ViewScoped
public class NewsAdminView implements Serializable{

	private static final long serialVersionUID = 6330672338108028518L;
	
	@Inject
	private Logger logger;
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
    private NewsService newsService;
	
	private News selectedNews;
	private String createTitle;
	private String createContent;
	private List<News> news = null;
    
    @PostConstruct
    public void init() {
    	logger.info("newsAdminView init()");
    	
    	Map<String, String> parameters = facesContext.getExternalContext().getRequestParameterMap();
		String newsId = parameters.get("newsId");
		if(newsId != null && !newsId.equals("")) {
			// load selected news.
			logger.info("loading selected news due to request parameter");
			loadSelectedNews(newsId);
		}
		else {
			// load all news.
			logger.info("loading all news due to news list request");
    		news = newsService.getAllNews();
		}
    }
    
    public List<News> getNews() {
    	return news;
    }
    
    public News getSelectedNews() {
    	return selectedNews;
    }
    
    public void setSelectedNews(News news) {
    	this.selectedNews = news;
    }
    
    public void setDeleteCommunity(int id) {
    	// TODO: Group3
    	// TODO: Delete community with id
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
	
	/**
	 * Bean methods.
	 */
    public void create() {
    	logger.info("create() news called.");
    	try {
    		newsService.postNews(null, createTitle, createContent, new Date(), false);
    		
    		// redirection.
    		ExternalContext ec = facesContext.getExternalContext();
    	    ec.redirect(ec.getRequestContextPath() + "/admin/news_list.jsf");
    	} catch(Exception e) {
    		facesContext.addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to create news", null));
    	}
    }
    
    public void save() {
    	logger.info("save() selected news called");
    	try {
    		newsService.updateNews(selectedNews);
    		
    		// redirection.
    		ExternalContext ec = facesContext.getExternalContext();
    	    ec.redirect(ec.getRequestContextPath() + "/admin/news_list.jsf");
    	} catch(Exception e) {
    		facesContext.addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed to save news.", null));
    	}
    }
    
    private void loadSelectedNews(String newsId) {
    	try {
    		Long id = Long.parseLong(newsId);
    		selectedNews = newsService.findNewsById(id.longValue());
    	} catch(NumberFormatException e) {
    		logger.log(Level.SEVERE, "error parsing newsId", e);
    		
    	}
    }
}
