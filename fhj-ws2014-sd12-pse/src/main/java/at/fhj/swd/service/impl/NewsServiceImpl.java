package at.fhj.swd.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import at.fhj.swd.data.NewsDAO;
import at.fhj.swd.data.UserDAO;
import at.fhj.swd.data.entity.News;
import at.fhj.swd.data.entity.User;
import at.fhj.swd.presentation.helper.CookieHelper;
import at.fhj.swd.service.NewsService;

@Stateless
public class NewsServiceImpl implements NewsService {
	
	@Inject
	private NewsDAO newsDao;
	
	@Inject
	private UserDAO userDao;	

	@Override
	public List<News> getAllNews() {
		return newsDao.getAllNews();
	}

	@Override
	public void postNews(Long id, String title, String content, Date startdate, Boolean visible) throws IOException {
		
		if (ensureUserIsAdmin() || ensureUserIsPortalAdmin()) {
			News newNews = new News();
			newNews.setTitle(title);
			newNews.setContent(content);
			newNews.setStartdate(startdate);
			newNews.setVisible(visible);
			newsDao.createNewNews(newNews);	
		}
		else {
			throw new RuntimeException("operation not allowed");
		}
	}

	@Override
	public News updateNews(News news) {
		if (news == null)
			throw new IllegalArgumentException("News is null.");
		
		if (ensureUserIsAdmin() || ensureUserIsPortalAdmin()) {
			return newsDao.updateNews(news);
		}
		else {
			throw new RuntimeException("operation not allowed");
		}	
		
	}

	@Override
	public boolean ensureUserIsAdmin() {
		String token = CookieHelper.getAuthTokenValue();
		User user = userDao.findByToken(token);
		if(user == null)
			return false;
		return user.getUsername().endsWith("_a");
	}

	@Override
	public boolean ensureUserIsPortalAdmin() {
		String token = CookieHelper.getAuthTokenValue();
		User user = userDao.findByToken(token);
		if(user == null)
			return false;
		return user.getUsername().endsWith("_pa");
	}

	@Override
	public News findNewsById(long id) {
		try {
			return newsDao.getNewsById(id);			
		} catch(Exception e) {
			// should be DaoException in future releases.
			throw e;
		}
	}
}
