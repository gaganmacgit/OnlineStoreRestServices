package com.mediaocean.rest.dao;

import java.io.Serializable;

import com.mediaocean.rest.model.ProductCategories;
import com.mediaocean.rest.model.ProductCategory;

public interface ProductCategoriesDAO<T extends Serializable> {

	/**
	 * Method for returning specific product category from DB based on category
	 * code.
	 * 
	 * @param categoryCode
	 * @return ProductCategory
	 */
	ProductCategory findProductCategory(String categoryCode);

	/**
	 * This method returns all the existing product categories in the DB.
	 * 
	 * @return ProductCategories - which is wrapper for returning List of
	 *          product categories.
	 */
	ProductCategories findAllProductCategories();
}
