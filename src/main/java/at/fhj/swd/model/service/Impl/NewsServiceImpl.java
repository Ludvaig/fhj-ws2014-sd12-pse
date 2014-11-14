package at.fhj.swd.model.service.Impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;

import at.fhj.swd.model.data.NewsDao;
import at.fhj.swd.model.data.UserDAO;
import at.fhj.swd.model.entity.News;
import at.fhj.swd.model.entity.User;
import at.fhj.swd.model.service.NewsService;

public class NewsServiceImpl implements NewsService{
	
	private NewsDao newsdao;
	
	private UserDAO userdao;
	
	private User user;
	
	private News news;
	

	@Override
	public List<News> getAllNews() {
		return this.getAllNews();
	}

	@Override
	public void postNews(Long id, String title, String content, Date startdate, Boolean visible) throws IOException {
		
		if (UserIsAdmin(user) == false)
			throw new IllegalArgumentException("Du bist kein Admin. Nur Admins dürfen NEws posten");
			News newNews = new News();
			newNews.setId(id);
			newNews.setTitle(title);
			newNews.setContent(content);
			newNews.setStartdate(startdate);
			newNews.setVisible(visible);
			newsdao.createNewNews(newNews);
			
	}

	

	@Override
	public News updateNews(News news) {
		if (UserIsAdmin(user) || UserIsPortalAdmin(user)== false )
			throw new IllegalArgumentException("Du bist kein Admin.");
			if (news == null)
				throw new IllegalArgumentException("News is null.");
			return newsdao.updateNews(news);		
	}
	


	@Override
	@PermitAll()
	public boolean UserIsAdmin(User user) {
		return user.getUsername().endsWith("_a");
	}

	@Override
	@PermitAll()
	public boolean UserIsPortalAdmin(User user) {
		return user.getUsername().endsWith("_pa");
	}

	@Override
	public List<News> getNewsbyID(Long id) {
		// TODO Auto-generated method stub
		return this.getNewsbyID(id);
	}



}
