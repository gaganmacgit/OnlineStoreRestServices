package com.mediaocean.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "product")
@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1659400297397932704L;

	@Id
	@Column(name = "product_code")
	@XmlElement
	private String productCode;

	@ManyToOne
	@JoinColumn(name = "id_category")
	@XmlElement
	@Fetch(FetchMode.JOIN)
	private ProductCategory category;

	@Column(nullable = false, name = "product_name")
	@XmlElement
	private String productName;

	@Column(nullable = false, name = "product_info")
	@XmlElement
	private String productInfo;

	@Column(nullable = false)
	@XmlElement
	private BigDecimal price;

	@Column(nullable = false, name = "product_stock_qty")
	@XmlElement
	private Integer productStockQuantity;

	public Integer getProductStockQuantity() {
		return productStockQuantity;
	}

	public void setProductStockQuantity(final Integer productStockQuantity) {
		this.productStockQuantity = productStockQuantity;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(final ProductCategory category) {
		this.category = category;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(final String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(final String productName) {
		this.productName = productName;
	}

	public String getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(final String productInfo) {
		this.productInfo = productInfo;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(final BigDecimal price) {
		this.price = price;
	}

}