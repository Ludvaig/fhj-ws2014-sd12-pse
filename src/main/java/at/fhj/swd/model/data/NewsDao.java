package at.fhj.swd.model.data;

import java.util.List;
import at.fhj.swd.model.entity.News;

public interface NewsDao {

	public List<News> getAllVisibleNews();
	public News getNewsById(String id);
	void createNewNews(News newNews);

}
