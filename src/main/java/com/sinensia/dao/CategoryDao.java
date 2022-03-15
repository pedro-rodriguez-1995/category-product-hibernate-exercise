package com.sinensia.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.sinensia.model.Category;
import com.sinensia.model.Product;

public class CategoryDao {
	private static final java.util.logging.Logger logger = Logger.getLogger(CategoryDao.class.getName());

	private final SessionFactory sessionFactory = new HibernateSessionFactory().getSessionFactory();

	public boolean saveOrUpdate(Category instance) {
		logger.log(Level.INFO, "attaching dirty Category instance");
		Session sessionObj = sessionFactory.getCurrentSession();
		Transaction tx = sessionObj.beginTransaction();
		try {

			sessionObj.saveOrUpdate(instance);

			for (Product product : instance.getProducts()) {
				product.setCategory(instance);
				sessionObj.saveOrUpdate(product);
			}

			tx.commit();

			logger.log(Level.INFO, "attach successful");

		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			tx.rollback();
			throw re;
		} finally {
			sessionObj.close();
		}
		return true;
	}

	public Integer save(Category instance) {

		logger.log(Level.INFO, "attaching dirty Category instance");
		Session sessionObj = sessionFactory.getCurrentSession();
		Transaction tx = sessionObj.beginTransaction();
		Integer idCategory = 0;
		try {

			idCategory = (Integer) sessionObj.save(instance);

			for (Product product : instance.getProducts()) {
				product.setCategory(instance);
				sessionObj.save(product);
			}

			tx.commit();

			logger.log(Level.INFO, "attach successful");

		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			tx.rollback();
			throw re;
		} finally {
			sessionObj.close();
		}
		return idCategory;

	}

	public Category merge(Integer id) {
		logger.log(Level.INFO, "merging Category instance");
		try {
			Session sessionObj = sessionFactory.getCurrentSession();
			Transaction tx = sessionObj.beginTransaction();
			Category instance = (Category) sessionObj.get("com.sinensia.model.Category", id);
			instance.setCategoryName("Merged");
			Category result = (Category) sessionObj.merge(instance);
			tx.commit();
			sessionObj.close();
			logger.log(Level.INFO, "merge successful");
			return result;

		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "merge failed", re);
			throw re;
		}

	}

	public List<Category> findAll() {
		logger.log(Level.INFO, "finding Category instance by example");
		try {
			Session sessionObj = sessionFactory.getCurrentSession();
			sessionObj.beginTransaction();
			Query categoriesQuery = sessionObj.createQuery("FROM Category");

			@SuppressWarnings("unchecked")
			List<Category> categoriesList = categoriesQuery.getResultList();
			sessionObj.close();

			return categoriesList;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "find by example failed", re);
			throw re;
		}

	}

	public Category findById(java.lang.Integer id) {
		logger.log(Level.INFO, "getting Category instance with id: " + id);
		try {
			Session sessionObj = sessionFactory.getCurrentSession();
			Transaction tx = sessionObj.beginTransaction();

			Category instance = (Category) sessionObj.get("com.sinensia.model.Category", id);

			if (instance == null) {
				logger.log(Level.INFO, "get successful no instance found");
			} else {
				logger.log(Level.INFO, "get successful  instance found");
			}
			tx.commit();
			sessionObj.close();

			return instance;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "find by example failed", re);
			throw re;
		}

	}

	public boolean delete(java.lang.Integer id) {
		logger.log(Level.INFO, "deleting Category instance");
		Session sessionObj = sessionFactory.getCurrentSession();
		Transaction tx = sessionObj.beginTransaction();
		try {

			Category instance = (Category) sessionObj.get("com.sinensia.model.Category", id);

			for (Product product : instance.getProducts()) {
				sessionFactory.getCurrentSession().delete(product);
			}
			sessionFactory.getCurrentSession().delete(instance);
			tx.commit();

			logger.log(Level.INFO, "delete successful");

		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "delete failed", re);
			tx.rollback();
			throw re;
		} finally {
			sessionObj.close();
		}
		return true;
	}

}
