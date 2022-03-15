package com.sinensia.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sinensia.model.Category;
import com.sinensia.model.Product;

public class HibernateSessionFactory {
	private static final Logger logger = Logger.getLogger(HibernateSessionFactory.class.getName());

	public SessionFactory getSessionFactory() {

		try {
			Configuration config = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Category.class)
					.addAnnotatedClass(Product.class);

			SessionFactory sessionFactory = config.buildSessionFactory();
			return sessionFactory;

		} catch (Exception e) {
			logger.log(Level.SEVERE, "Could not locate Session Factory", e);
			throw new IllegalStateException("Could not locate Session Factory");

		}
	}
}
