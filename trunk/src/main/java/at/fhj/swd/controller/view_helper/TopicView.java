package at.fhj.swd.controller.view_helper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import at.fhj.swd.model.entity.Topic;
import at.fhj.swd.model.service.TopicService;

@ManagedBean(name="dtTopicView")
public class TopicView implements Serializable{

	private static final long serialVersionUID = 5308381287611038438L;
	
	private List<Topic> existingTopics = null;
	private String searchFieldText = null;
	
	@Inject
	TopicService service;
	
	@PostConstruct
	public void init(){
		Map<String,String> params = 
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap(); 
		String id = params.get("id");
		this.existingTopics = service.getExistingTopics(id, "");
	}

	public List<Topic> getExistingTopics() {
		return existingTopics;
	}

	public void setExistingTopics(List<Topic> existingTopics) {
		this.existingTopics = existingTopics;
	}
	
	public String getSearchFieldText() {
		return searchFieldText;
	}

	public void setSearchFieldText(String searchFieldText) {
		this.searchFieldText = searchFieldText;
	}
	
	public String search(){
		this.existingTopics = service.getExistingTopics("1", searchFieldText);
		return "topic";
	}
	
}
