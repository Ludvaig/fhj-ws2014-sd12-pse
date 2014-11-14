/**
 * @author Gruppe 3, Viehberger, Tot, Mayer
 */

package at.fhj.swd.model.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import at.fhj.swd.model.entity.News;
import at.fhj.swd.model.entity.User;

public interface NewsService {

		public List<News> getAllNews();
		public List <News> getNewsbyID(Long id);
		
		//News posten, löschen und updaten. Titel ist unique!	

		void postNews(Long id, String title, String content, Date startdate,
				Boolean visible) throws IOException;
		
		News updateNews(News news);
		
		
		/**
		 * checks if user is logged in.
		 * checkadmin ist gfladert, sry, thx!
		 * @param token
		 * @return
		 */
		boolean UserIsAdmin(User user);
		
		/**
		 * check if User is Portaladmin
		 * */
		boolean UserIsPortalAdmin(User user);


		

}
