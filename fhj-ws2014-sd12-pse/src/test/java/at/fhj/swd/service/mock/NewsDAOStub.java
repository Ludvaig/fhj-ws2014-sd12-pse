package at.fhj.swd.service.mock;

import java.util.ArrayList;
import java.util.List;

import at.fhj.swd.data.NewsDAO;
import at.fhj.swd.data.entity.News;
import at.fhj.swd.data.exceptions.DataSourceLayerException;

public class NewsDAOStub implements NewsDAO {
	
	private List<News> newsList = new ArrayList<News>();

	@Override
	public List<News> getAllVisibleNews() {
		List<News> nl = new ArrayList<News>();
		for (News news : newsList){
			if(news.isVisible())
				nl.add(news);
		}	
		return nl;
	}

	@Override
	public News getNewsById(long id) {
		for (News news : newsList){
			if(news.getId() == id)
				return news;
		}
		throw new DataSourceLayerException("failed to get news by id");	
	}

	@Override
	public void createNewNews(News newNews) {
		newNews = new News();
		newsList.add(newNews);
	}

	@Override
	public News updateNews(News news) {
		newsList.add(news);
		return news;
	}

	@Override
	public List<News> getAllNews() {
		// TODO Auto-generated method stub
		return newsList;
	}

}
