package com.sinensia.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "categoryId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryId;
	@Column(name = "categoryName")
	private String categoryName;
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Product> products;

	public Category() {

	}

	public Category(int categoryId, String categoryName, List<Product> products) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.products = products;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
