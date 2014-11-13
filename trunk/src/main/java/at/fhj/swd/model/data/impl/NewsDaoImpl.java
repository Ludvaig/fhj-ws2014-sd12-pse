package at.fhj.swd.model.data.impl;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import at.fhj.swd.model.data.NewsDao;
import at.fhj.swd.model.entity.News;

public class NewsDaoImpl implements NewsDao {

	@Inject
	public EntityManager em;

	@Override
	public List<News> getAllVisibleNews() {
		
		// Load all visible news from database (Pagination @see: LazyCommunityImpl)
		List<News> news = em
				.createQuery(
						"SELECT news FROM News news"
						+ " WHERE news.visible = true "
						+ " ORDER BY news.startdate",
						News.class)
							.getResultList();
		
		return news;
	}

	@Override
	public News getNewsById(String id) {
		return em.createQuery("FROM News news WHERE news.id = :id", News.class).setParameter("id", Long.valueOf(id)).getSingleResult();
	}
}
