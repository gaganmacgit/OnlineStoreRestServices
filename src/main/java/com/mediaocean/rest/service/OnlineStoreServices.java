package com.mediaocean.rest.service;

import com.mediaocean.rest.model.Product;
import com.mediaocean.rest.model.ProductCategories;
import com.mediaocean.rest.model.ProductCategory;
import com.mediaocean.rest.model.ProductWrapper;
import com.mediaocean.rest.model.UserCart;
import com.mediaocean.rest.model.UserCartItem;
import com.mediaocean.rest.model.UserCartItemsWrapper;
import com.mediaocean.rest.model.UserVO;

public interface OnlineStoreServices {

	public ProductCategory findProductCategory(String productCategory);

	public ProductCategories findAllProductCategories();

	public ProductWrapper findAllProducts();

	public Product findProduct(String productCode);

	public UserCart createUserCart(UserVO userObject);

	public UserCartItem addProductToUserCart(String userEmailId, UserCartItem userCartProduct);

	public UserCartItemsWrapper getProductsInUserCart(String userEmailId);

	public UserCartItemsWrapper performCartCheckout(String userEmailId);
}
