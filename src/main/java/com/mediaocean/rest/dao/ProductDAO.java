package com.mediaocean.rest.dao;

import java.io.Serializable;

import com.mediaocean.rest.model.Product;
import com.mediaocean.rest.model.ProductWrapper;

public interface ProductDAO<T extends Serializable> {

	Product findProduct(String productCode);

	ProductWrapper findAllProducts();
}
