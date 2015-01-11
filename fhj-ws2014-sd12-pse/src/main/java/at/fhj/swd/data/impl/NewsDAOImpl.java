package at.fhj.swd.data.impl;

/**
 * Group 3:
 * 
 * Update Julia: UpdateUser-Methode
 */

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import at.fhj.swd.data.NewsDAO;
import at.fhj.swd.data.entity.News;
import at.fhj.swd.data.exceptions.DataSourceLayerException;

public class NewsDAOImpl implements NewsDAO {

	@Inject
	public EntityManager em;

	@Override
	public List<News> getAllVisibleNews() {
		
		// Load all visible news from database (Pagination @see: LazyCommunityImpl)
		try {
			List<News> news = em
					.createQuery(
							"SELECT news FROM News news"
							+ " WHERE news.visible = true "
							+ " ORDER BY news.startdate",
							News.class)
								.getResultList();
			
			return news;	
		} catch (Exception e) {
			throw new DataSourceLayerException("failed to get all visible news", e);
		}
	}
	
	@Override
	public List<News> getAllNews() {
		// Load all news from database (Pagination @see: LazyCommunityImpl)
		try {
			List<News> news = em
					.createQuery(
						"SELECT news FROM News news"
						+ " ORDER BY news.startdate",
						News.class)
					.getResultList();		
			return news;
		} catch (Exception e) {
			throw new DataSourceLayerException("failed to get all news", e);
		}
	}

	@Override
	public News getNewsById(long id) {
		try {
			return em.createQuery("FROM News news WHERE news.id = :id", News.class)
					.setParameter("id", Long.valueOf(id))
					.getSingleResult();
		} catch (Exception e) {
			throw new DataSourceLayerException("failed to get news by id", e);
		}
	}
	
	@Override
	public void createNewNews(News newNews) {
		if(newNews == null) {
			throw new IllegalArgumentException("news");
		}	
		try {
			em.persist(newNews);
		} catch (Exception e) {
			throw new DataSourceLayerException("failed to create news", e);
		}		
	}

	@Override
	public News updateNews(News news) {
		if(news == null) {
			throw new IllegalArgumentException("news");
		}		
		try {
			return em.merge(news);
		} catch (Exception e) {
			throw new DataSourceLayerException("failed to get all visible news", e);
		}		
	}
}
