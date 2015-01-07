/**
 * @author Gruppe 3, Viehberger, Tot, Mayer
 */

package at.fhj.swd.service;

import java.util.Date;
import java.util.List;

import at.fhj.swd.data.entity.News;

//News posten, löschen und updaten. Titel ist unique!
public interface NewsService {

		List<News> getAllNews();
		
		News findNewsById(long id);
		
		void postNews(Long id, String title, String content, Date startdate, Boolean visible);
		
		News updateNews(News news);
}
