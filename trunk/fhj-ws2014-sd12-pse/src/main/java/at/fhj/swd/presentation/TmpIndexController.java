package at.fhj.swd.presentation;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlOutputLink;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.w3c.dom.html.HTMLDivElement;
import org.w3c.dom.html.HTMLLinkElement;

import at.fhj.swd.data.entity.User;
import at.fhj.swd.presentation.helper.CookieHelper;
import at.fhj.swd.service.UserService;

/***
 * 
 * @author Steven Hagelmüller 
 * 
 * Controller for the Logout page. Uses the 'UserService' class.
 * 
 */
@ManagedBean(name = "tmpIndexController")
@SessionScoped
public class TmpIndexController {
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private UserService userService;

	private HtmlPanelGrid links;
	private HtmlOutputText user;
	
	public HtmlOutputText getUser() {
		return user;
	}

	public void setUser(HtmlOutputText user) {
		this.user = user;
	}

	public HtmlPanelGrid getLinks() {
		return links;
	}

	public void setLinks(HtmlPanelGrid links) {
		this.links = links;
	}

	public void checkUser() throws IOException {
		String token = CookieHelper.getAuthTokenValue();
		User user = null;
		if(token != null) {
			user = userService.getRegisteredUser(token);
		}
		
		if(user == null) {
			String url = ((HttpServletRequest)facesContext.getExternalContext().getRequest()).getContextPath();
			facesContext.getExternalContext().redirect(url + "/login.jsf");
		}
	}
	
	public void createLinks() throws IOException {
		user.setValue("You are not logged in");
		String token = CookieHelper.getAuthTokenValue();
		if(token != null) {
			User usr =  userService.getRegisteredUser(token);
			if(usr != null) {
				user.setValue("You are logged in as " + usr.getUsername());
			}
		}
	  
		
	  String rootFilePath = facesContext.getExternalContext().getRealPath("/");
	  
	  File folder = new File(rootFilePath);
	  File[] listOfFiles = folder.listFiles(); 
	 
	  links.getChildren().clear();
	  
	  for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {
			  String file = listOfFiles[i].getName();
		      if (file.endsWith(".xhtml")) {
		    	  HtmlOutputLink link = new HtmlOutputLink();
		    	  link.setValue(file.replace(".xhtml","") + ".jsf");
		    	  HtmlOutputText text = new HtmlOutputText();
		    	  text.setValue(file.replace(".xhtml","") + ".jsf");
		    	  link.getChildren().add(text);
		    	  
		    	  links.getChildren().add(link);
		      }
		   }
	  }
	}
}
