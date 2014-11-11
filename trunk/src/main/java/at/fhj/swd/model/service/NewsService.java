/**
 * @author Gruppe 3, Viehberger, Tot, Mayer
 */

package at.fhj.swd.model.service;

import java.io.IOException;
import java.util.List;

import at.fhj.swd.model.entity.News;
import at.fhj.swd.model.entity.User;

public interface NewsService {

		public List<News> getAllNews();
		
		//News posten, löschen und updaten. Titel ist unique!	
		public void postNews(final String title, final String content) throws IOException;

		public void deleteNews(final String title);
		
		public void updateNews(final String name) throws IOException;
		
		
		/**
		 * checks if user is logged in.
		 * checkadmin ist gfladert, sry, thx!
		 * @param token
		 * @return
		 */
		public boolean getAdministrationAllowed(String token);
		
		public User getUserByToken(String token);

		boolean getUserAdministrationAllowed(String token);

}
