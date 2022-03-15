package com.sinensia.dao;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sinensia.model.Category;
import com.sinensia.model.Product;

public class CategoryDaoTest {
	CategoryDao categoryDao = null;
	static Integer idUpdated = 0;
	static Integer idDeleted = 0;
	static Integer idFound = 0;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Category category = new Category();
		category.setCategoryName("test");
		Product product1t = new Product();
		product1t.setProductName("test1");
		Product product2t = new Product();
		product2t.setProductName("test2");
		List<Product> listproducts = new ArrayList<Product>();
		listproducts.add(product1t);
		listproducts.add(product2t);
		category.setProducts(listproducts);

		Category category1 = new Category();
		category1.setCategoryName("deleted");
		Product product1d = new Product();
		product1d.setProductName("deleted1");
		Product product2d = new Product();
		product2d.setProductName("deleted2");
		List<Product> listproducts1 = new ArrayList<Product>();
		listproducts1.add(product1d);
		listproducts1.add(product2d);
		category1.setProducts(listproducts1);

		Category category2 = new Category();
		category2.setCategoryName("found");
		Product product1f = new Product();
		product1f.setProductName("found1");
		Product product2f = new Product();
		product2f.setProductName("found2");

		List<Product> listproducts2 = new ArrayList<Product>();
		listproducts2.add(product1f);
		listproducts2.add(product2f);
		category2.setProducts(listproducts2);

		CategoryDao categoryDaoBeforeClass = new CategoryDao();

		idUpdated = categoryDaoBeforeClass.save(category);
		idDeleted = categoryDaoBeforeClass.save(category1);
		idFound = categoryDaoBeforeClass.save(category2);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		categoryDao = new CategoryDao();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSaveOrUpdate() {
		Category category = new Category();
		category.setCategoryName("tools");
		Product product1 = new Product();
		product1.setProductName("hammer");
		Product product2 = new Product();
		product2.setProductName("nails");

		List<Product> listproducts = new ArrayList<Product>();
		listproducts.add(product1);
		listproducts.add(product2);
		category.setProducts(listproducts);

		assertTrue(categoryDao.saveOrUpdate(category));

	}

	@Test
	public void testSave() {
		Category category = new Category();
		category.setCategoryName("toys");
		Product product1 = new Product();
		product1.setProductName("ball");
		Product product2 = new Product();
		product2.setProductName("doll");

		List<Product> listproducts = new ArrayList<Product>();
		listproducts.add(product1);
		listproducts.add(product2);
		category.setProducts(listproducts);

		assertTrue(categoryDao.save(category) > 0);
	}

	@Test
	public void testMerge() {
		assertTrue(categoryDao.merge(idUpdated) != null);
	}

	@Test
	public void testFindAll() {
		assertTrue(categoryDao.findAll().size() > 1);
	}

	@Test
	public void testFindById() {
		assertTrue(categoryDao.findById(idFound) != null);
	}

	@Test
	public void testDelete() {
		assertTrue(categoryDao.delete(idDeleted));
	}

}
