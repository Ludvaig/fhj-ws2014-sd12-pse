package at.fhj.swd.controller.view_helper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import at.fhj.swd.model.entity.Topic;
import at.fhj.swd.model.service.CommunityService;
import at.fhj.swd.model.service.TopicService;

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
	TopicService service;
	
	@Inject
	CommunityService comService;
	
	private String communityName = null;
	
	@PostConstruct
	public void init(){
		getCommunityId();
		this.existingTopics = service.getExistingTopics(communityId, "");	
		this.communityName = comService.getCommunityById(communityId).getName();
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
	
	public String search(){
		this.existingTopics = service.getExistingTopics(communityId, searchFieldText);
		return "topic";
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
