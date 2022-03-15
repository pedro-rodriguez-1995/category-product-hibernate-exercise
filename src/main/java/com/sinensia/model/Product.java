package com.sinensia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "productId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	@Column(name = "productName")
	private String productName;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId")
	private Category category;

	public Product() {

	}

	public Product(int productId, String productName, Category category) {
		this.productId = productId;
		this.productName = productName;
		this.category = category;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
