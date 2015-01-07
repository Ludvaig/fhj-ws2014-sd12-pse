package at.fhj.swd.remoteFacade;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

import at.fhj.swd.data.entity.News;
import at.fhj.swd.service.NewsService;

@Any
@Remote(NewsService.class)
public class NewsServiceRemoteFacade implements NewsService {

	@Inject
	private NewsService newsService;
	
	@Override
	public List<News> getAllNews() {
		return newsService.getAllNews();
	}

	@Override
	public News findNewsById(long id) {
		return newsService.findNewsById(id);
	}

	@Override
	public void postNews(Long id, String title, String content, Date startdate,
			Boolean visible) {
		newsService.postNews(id, title, content, startdate, visible);
	}

	@Override
	public News updateNews(News news) {
		return newsService.updateNews(news);
	}

}
