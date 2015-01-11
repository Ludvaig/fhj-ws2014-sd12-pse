package at.fhj.swd.service.impl;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.hibernate.validator.internal.util.logging.LoggerFactory;

import at.fhj.swd.data.NewsDAO;
import at.fhj.swd.data.entity.News;
import at.fhj.swd.data.exceptions.DataSourceLayerException;
import at.fhj.swd.service.NewsService;
import at.fhj.swd.service.exceptions.ServiceLayerException;

@Stateless
public class NewsServiceImpl implements NewsService {
	
	@Inject
	private NewsDAO newsDao;	
	private static final Logger logger = Logger.getLogger(NewsServiceImpl.class.getName());

	@Override
	public List<News> getAllNews() {
		logger.entering(getClass().getName(), "getAllNews");
		try {
			return newsDao.getAllNews();
		} catch(DataSourceLayerException e) {
			logger.log(Level.SEVERE, "Service failed to fetch all news");
			throw new ServiceLayerException("Service failed to fetch all news", e);		
		}
	}

	@Override
	public void postNews(Long id, String title, String content, Date startdate, Boolean visible) {
		logger.entering(getClass().getName(), "postNews");
		try {
			News newNews = new News();
			newNews.setTitle(title);
			newNews.setContent(content);
			newNews.setStartdate(startdate);
			newNews.setVisible(visible);
			newsDao.createNewNews(newNews);	
		} catch(DataSourceLayerException e) {
			logger.log(Level.SEVERE, "Service failed to post news");
			throw new ServiceLayerException("Service failed to post news", e);
		}
	}

	@Override
	public News updateNews(News news) {
		logger.entering(getClass().getName(), "updateNews");
		if (news == null)
			throw new IllegalArgumentException("news");
			logger.log(Level.SEVERE, "User provided illegal argument");
		try {
			return newsDao.updateNews(news);
		} catch(DataSourceLayerException e) {
			logger.log(Level.SEVERE, "Service failed to update news");
			throw new ServiceLayerException("Service failed to update news", e);
		}
	}

	@Override
	public News findNewsById(long id) {
		logger.entering(getClass().getName(), "findNewsById");
		try {
			return newsDao.getNewsById(id);			
		} catch(DataSourceLayerException e) {
			logger.log(Level.SEVERE, "Service failed to find news with id" + id);
			throw new ServiceLayerException("Service failed to find news with id " + id, e);
		}
	}
}
