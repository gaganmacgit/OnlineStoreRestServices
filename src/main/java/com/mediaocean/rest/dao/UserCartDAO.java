package com.mediaocean.rest.dao;

import java.io.Serializable;

import com.mediaocean.rest.model.UserCart;
import com.mediaocean.rest.model.UserCartItem;
import com.mediaocean.rest.model.UserCartItemsWrapper;
import com.mediaocean.rest.model.UserVO;

public interface UserCartDAO<T extends Serializable> {

	public UserCart createUserCart(UserVO userObject);

	public UserCartItem addProductToUserCart(String userEmailId, UserCartItem userCartProduct);

	public UserCartItemsWrapper getProductsInUserCart(String userEmailId);

	public UserCartItemsWrapper performCartCheckout(String userEmailId);
}
