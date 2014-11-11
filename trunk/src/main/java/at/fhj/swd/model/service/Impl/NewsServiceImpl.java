package at.fhj.swd.model.service.Impl;

import java.io.IOException;
import java.util.List;

import at.fhj.swd.model.entity.News;
import at.fhj.swd.model.entity.User;
import at.fhj.swd.model.service.NewsService;

public class NewsServiceImpl implements NewsService{

	@Override
	public List<News> getAllNews() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void postNews(String title, String content) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteNews(String title) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateNews(String name) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getAdministrationAllowed(String token) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUserByToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getUserAdministrationAllowed(String token) {
		// TODO Auto-generated method stub
		return false;
	}

}
