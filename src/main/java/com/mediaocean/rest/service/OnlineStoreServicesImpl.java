package com.mediaocean.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mediaocean.rest.dao.ProductCategoriesDAO;
import com.mediaocean.rest.dao.ProductDAO;
import com.mediaocean.rest.dao.UserCartDAO;
import com.mediaocean.rest.model.Product;
import com.mediaocean.rest.model.ProductCategories;
import com.mediaocean.rest.model.ProductCategory;
import com.mediaocean.rest.model.ProductWrapper;
import com.mediaocean.rest.model.UserCart;
import com.mediaocean.rest.model.UserCartItem;
import com.mediaocean.rest.model.UserCartItemsWrapper;
import com.mediaocean.rest.model.UserVO;

@Service
public class OnlineStoreServicesImpl implements OnlineStoreServices {

	@Autowired
	private ProductCategoriesDAO<ProductCategory> productCategoriesDAO;

	@Autowired
	private ProductDAO<Product> productDAO;

	@Autowired
	private UserCartDAO<UserCart> userCartDAO;

	@Override
	@Transactional
	public ProductCategory findProductCategory(final String productCategory) {
		return productCategoriesDAO.findProductCategory(productCategory);
	}

	@Override
	@Transactional
	public ProductCategories findAllProductCategories() {
		return productCategoriesDAO.findAllProductCategories();
	}

	@Override
	@Transactional
	public ProductWrapper findAllProducts() {
		return productDAO.findAllProducts();
	}

	@Override
	public Product findProduct(final String productCode) {
		return productDAO.findProduct(productCode);
	}

	@Override
	@Transactional
	public UserCart createUserCart(final UserVO userObject) {
		return userCartDAO.createUserCart(userObject);
	}

	@Override
	@Transactional
	public UserCartItem addProductToUserCart(String userEmailId, UserCartItem userCartProduct) {
		return userCartDAO.addProductToUserCart(userEmailId, userCartProduct);
	}

	@Override
	public UserCartItemsWrapper getProductsInUserCart(String userEmailId) {
		return userCartDAO.getProductsInUserCart(userEmailId);
	}

	@Override
	@Transactional
	public UserCartItemsWrapper performCartCheckout(String userEmailId) {
		return userCartDAO.performCartCheckout(userEmailId);
	}
}