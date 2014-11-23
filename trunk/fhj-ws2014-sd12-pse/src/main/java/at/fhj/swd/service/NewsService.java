/**
 * @author Gruppe 3, Viehberger, Tot, Mayer
 */

package at.fhj.swd.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import at.fhj.swd.data.entity.News;

public interface NewsService {

		List<News> getAllNews();
		
		News findNewsById(long id);
		
		//News posten, löschen und updaten. Titel ist unique!	
		void postNews(Long id, String title, String content, Date startdate, Boolean visible) throws IOException;
		
		News updateNews(News news);		
		
		/**
		 * checks if user is logged in.
		 * checkadmin ist gfladert, sry, thx!
		 * @param token
		 * @return
		 */
		boolean ensureUserIsAdmin();
		
		/**
		 * check if User is Portaladmin
		 * */
		boolean ensureUserIsPortalAdmin();		

}
