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
import at.fhj.swd.data.entity.User;
import at.fhj.swd.presentation.helper.CookieHelper;
import at.fhj.swd.service.NewsService;
import at.fhj.swd.service.UserService;

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
	
	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject 
	private UserService userService;
	
	@Inject
    private NewsService newsService;
	
	private News selectedNews;
	private String createTitle;
	private String createContent;
	private List<News> news = null;
	private boolean userHasAccess;
    
    @PostConstruct
    public void init() {
    	logger.info("newsAdminView init()");
    	
    	// ensure access right.
    	ensureUserHasAccess();
    	
    	if(getUserHasAccess()) {    	
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
	
	public boolean getUserHasAccess() {
		return userHasAccess;
	}

	public void setUserHasAccess(boolean userHasAccess) {
		this.userHasAccess = userHasAccess;
	}
	
	/**
	 * Bean methods.
	 */
    public void create() {
    	logger.info("create() news called.");
    	
    	if(!getUserHasAccess()) {
    		facesContext.addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_WARN, "You are not signed in as Administrator or Portal Administrator.", null));
    		return;   		
    	}
    	
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
    	
    	if(!getUserHasAccess()) {
    		facesContext.addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_WARN, "You are not signed in as Administrator or Portal Administrator.", null));
    		return;   		
    	}
    	
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
    
    private void ensureUserHasAccess() {
		logger.log(Level.INFO, "Calling " + this.getClass().getName() + "::ensureUserIsAdmin()!");
		
		String token = CookieHelper.getAuthTokenValue();
		User user = userService.getRegisteredUser(token);
		if(user == null) {
			setUserHasAccess(false);
		} else {
			boolean hasAccess = user.getUsername().endsWith("_a") || // access is granted to admin
					user.getUsername().endsWith("_pa"); 			  // or portal admin.
			logger.log(Level.INFO, "Loaded user [" + user + "] hasAccess [" + hasAccess +"]");
			setUserHasAccess(hasAccess);
		}
    }
}
