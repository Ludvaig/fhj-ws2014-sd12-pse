package at.fhj.swd.presentation.viewHelper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import at.fhj.swd.data.entity.Community;
import at.fhj.swd.data.entity.Topic;
import at.fhj.swd.service.CommunityService;
import at.fhj.swd.service.TopicService;

@ManagedBean(name="dtTopicView")
@ViewScoped
public class TopicView implements Serializable{

	private static final long serialVersionUID = 5308381287611038438L;
	
	private List<Topic> existingTopics = null;
	private String searchFieldText = null;
	private String createFieldText = null;
	private String createTopicText = null;

	private String communityId = null;
	
	@Inject
	private Logger logger;
	
	@Inject
	TopicService service;
	
	@Inject
	CommunityService comService;
	
	private String communityName = null;
	
	@PostConstruct
	public void init(){
		logger.log(Level.INFO, "Initiliazing " + this.getClass().getName() + " in @PostConstruct!");
		
		getCommunityId();	
		if(communityId != null && !communityId.equals("")) {
			Community community = null;
			try {
				community = comService.getCommunityById(Long.valueOf(communityId).longValue());
			} catch (Exception e) {
				RedirectionTargetHelper.redirectTo(RedirectionTarget.COMMUNITIES);
			}
			logger.log(Level.INFO, "Retrieved community [communityName = '" + community.getName() + "']!");
			
			this.existingTopics = service.getExistingTopics(communityId, "");
			this.communityName = community.getName();
		}
	}

	private void getCommunityId(){
		Map<String,String> params = 
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap(); 
		this.communityId = params.get("id");
	}
	
	
	public List<Topic> getExistingTopics() {
		return existingTopics;
	}

	public void setExistingTopics(List<Topic> existingTopics) {
		this.existingTopics = existingTopics;
	}
	
	public String getCreateTopicText() {
		return createTopicText;
	}

	public void setCreateTopicText(String createTopicText) {
		this.createTopicText = createTopicText;
	}
	
	public String getSearchFieldText() {
		return searchFieldText;
	}

	public void setSearchFieldText(String searchFieldText) {
		this.searchFieldText = searchFieldText;
	}
	
	public String getCreateFieldText() {
		return createFieldText;
	}

	public void setCreateFieldText(String createFieldText) {
		this.createFieldText = createFieldText;
	}
	
	public void search(){
		this.existingTopics = service.getExistingTopics(communityId, searchFieldText);
	}
	
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	
	public String getCommunityName() {
		return this.communityName;
	}
	
	public void createNewTopic() {
		service.createNewTopic(communityId, createFieldText, createTopicText);
		this.existingTopics = service.getExistingTopics(communityId, "");
	}
}
