package com.mediaocean.rest.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user_cart")
@XmlRootElement(name = "cart")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserCart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1659400297397932704L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@XmlElement
	private Integer id;

	@XmlElement
	@Column(name = "username")
	private String userName;

	@Column(nullable = false, name = "useremail")
	@XmlElement
	private String userEmail;

	@Column(name = "status")
	@XmlElement
	private String status;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="userCart")
	@XmlTransient
	@JsonIgnore
	private List<UserCartItem> cartItems;

	@Column(name = "createddate")
	@XmlElement
	private Date createdDate;

	@Column(name = "updateddate")
	@XmlElement
	private Date updatedDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public List<UserCartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<UserCartItem> cartItems) {
		this.cartItems = cartItems;
	}

}