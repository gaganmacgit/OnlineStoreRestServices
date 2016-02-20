package com.mediaocean.rest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "productcategories")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductCategories {

	private List<ProductCategory> productCategoriesList;

	public List<ProductCategory> getProductCategoriesList() {
		return productCategoriesList;
	}

	public void setProductCategoriesList(List<ProductCategory> productCategoriesList) {
		this.productCategoriesList = productCategoriesList;
	}

}
