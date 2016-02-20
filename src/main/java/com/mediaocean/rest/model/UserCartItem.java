package com.mediaocean.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user_cart_item")
@XmlRootElement(name = "cartitem")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserCartItem implements Serializable {

	/**
	 * user_cart_id INTEGER , product_code VARCHAR(10), product_category
	 * VARCHAR(1), product_quantity integer, purchase_date timestamp
	 */
	private static final long serialVersionUID = -5198884929184306093L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@XmlElement
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_cart_id")
	@XmlTransient
	@JsonIgnore
	private UserCart userCart;

	@Column(name = "product_code")
	@XmlElement(name = "product_code")
	private String productCode;

	@Column(name = "product_category")
	@XmlElement(name = "product_category")
	private String productCategory;

	@Column(name = "product_quantity")
	@XmlElement(name = "product_quantity")
	private Integer productQuantity;

	@Column(name = "purchase_date")
	 @XmlElement(name="purchase_date")
	private Date purchaseDate;

	@Column(name = "product_price")
	@XmlElement(name = "product_price")
	private BigDecimal price;

	@Column(name = "product_sales_tax")
	@XmlElement(name = "product_sales_tax")
	private BigDecimal productSalesTax;

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(final String productCode) {
		this.productCode = productCode;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(final String productCategory) {
		this.productCategory = productCategory;
	}

	public Integer getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(final Integer productQuantity) {
		this.productQuantity = productQuantity;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(final Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(final BigDecimal price) {
		this.price = price;
	}

	public UserCart getUserCart() {
		return userCart;
	}

	public void setUserCart(final UserCart userCart) {
		this.userCart = userCart;
	}

	public BigDecimal getProductSalesTax() {
		return productSalesTax;
	}

	public void setProductSalesTax(final BigDecimal productSalesTax) {
		this.productSalesTax = productSalesTax;
	}
}
