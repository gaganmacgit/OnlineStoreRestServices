package com.mediaocean.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "category_product")
@XmlRootElement(name = "productcategory")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4403029880810470491L;

	@Id
	@Column(name = "category_code")
	@XmlElement
	private String categoryCode;

	@Column(nullable = false, length = 100)
	@XmlElement
	private String description;

	@Column(name = "tax")
	@XmlElement
	private BigDecimal salesTax;

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(final String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public BigDecimal getSalesTax() {
		return salesTax;
	}

	public void setSalesTax(BigDecimal salesTax) {
		this.salesTax = salesTax;
	}

}