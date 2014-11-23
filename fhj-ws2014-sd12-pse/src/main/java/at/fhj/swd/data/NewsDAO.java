package at.fhj.swd.data;

/**
 * 
 * Gruppe 3: 
 */

import java.util.List;

import at.fhj.swd.data.entity.News;

public interface NewsDAO {

	public List<News> getAllVisibleNews();
	public News getNewsById(long id);
	void createNewNews(News newNews);
	News updateNews(News news);
	List<News> getAllNews();

}
