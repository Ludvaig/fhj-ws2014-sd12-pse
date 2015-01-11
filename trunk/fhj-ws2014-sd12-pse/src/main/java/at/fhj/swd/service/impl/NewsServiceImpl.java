package at.fhj.swd.service.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import at.fhj.swd.data.NewsDAO;
import at.fhj.swd.data.entity.News;
import at.fhj.swd.data.exceptions.DataSourceLayerException;
import at.fhj.swd.service.NewsService;
import at.fhj.swd.service.exceptions.ServiceLayerException;

@Stateless
public class NewsServiceImpl implements NewsService {
	
	@Inject
	private NewsDAO newsDao;	

	@Override
	public List<News> getAllNews() {
		try {
			return newsDao.getAllNews();
		} catch(DataSourceLayerException e) {
			throw new ServiceLayerException("service failed to get all news", e);
		}
	}

	@Override
	public void postNews(Long id, String title, String content, Date startdate, Boolean visible) {
		try {
			News newNews = new News();
			newNews.setTitle(title);
			newNews.setContent(content);
			newNews.setStartdate(startdate);
			newNews.setVisible(visible);
			newsDao.createNewNews(newNews);	
		} catch(DataSourceLayerException e) {
			throw new ServiceLayerException("service failed to post news", e);
		}
	}

	@Override
	public News updateNews(News news) {
		if (news == null)
			throw new IllegalArgumentException("news");
		
		try {
			return newsDao.updateNews(news);
		} catch(DataSourceLayerException e) {
			throw new ServiceLayerException("service failed to update news", e);
		}
	}

	@Override
	public News findNewsById(long id) {
		try {
			return newsDao.getNewsById(id);			
		} catch(DataSourceLayerException e) {
			throw new ServiceLayerException("service failed to find news with id " + id, e);
		}
	}
}
