package at.fhj.swd.service.impl;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import at.fhj.swd.data.entity.Community;

public class LazyCommunityImpl extends LazyDataModel<Community> {
	
	private static final long serialVersionUID = 1L;
	private List<Community> community;
	
	public LazyCommunityImpl(List<Community> community) {
		this.community = community;
	}
	
	@Override
	public List<Community> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters){
		
		return community;
	}
}
