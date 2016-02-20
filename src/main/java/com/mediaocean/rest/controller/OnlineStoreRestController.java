package com.mediaocean.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mediaocean.rest.model.OnlineAppRestURIConstants;
import com.mediaocean.rest.model.Product;
import com.mediaocean.rest.model.ProductCategories;
import com.mediaocean.rest.model.ProductCategory;
import com.mediaocean.rest.model.ProductWrapper;
import com.mediaocean.rest.model.UserCart;
import com.mediaocean.rest.model.UserCartItem;
import com.mediaocean.rest.model.UserCartItemsWrapper;
import com.mediaocean.rest.model.UserVO;
import com.mediaocean.rest.service.OnlineStoreServices;

@RestController
public class OnlineStoreRestController {

	@Autowired
	private OnlineStoreServices onlineStoreServices;

	/**
	 * <b>POST Method</b> This method creates an empty user cart for a user
	 * based on user information coming in Request Body.OR it returns the
	 * INPROCESS(existing) user cart for this user<br/>
	 * <b>REST URL</b>: /rest/user/cart
	 * 
	 * @param userObject
	 *            : User information coming in Request Body
	 * @return
	 */
	@RequestMapping(value = OnlineAppRestURIConstants.CREATE_CART, method = RequestMethod.POST)
	public ResponseEntity<UserCart> createUserCart(@RequestBody final UserVO userObject) {
		final UserCart createUserCart = onlineStoreServices.createUserCart(userObject);
		return new ResponseEntity<UserCart>(createUserCart, HttpStatus.CREATED);

	}

	/**
	 * <b>POST Method</b> This method adds a product to the existing user cart.<br/>
	 * <b>REST URL</b>: /rest/user/{emailid}/cart/product
	 * 
	 * @param userEmailId
	 *            : User EmailId - which identifies a unique user in the system
	 * @param userCartProduct
	 *            : The request body containing the product details.Example
	 * 
	 *            <pre>
	 *  {@code
	 * <cartitem>
	 * 		<product_code>PC001</product_code>
	 * 		<product_category>A</product_category>
	 * 		<product_quantity>2</product_quantity>
	 * </cartitem>
	 *  }
	 * </pre>
	 * @return
	 */
	@RequestMapping(value = OnlineAppRestURIConstants.ADD_PRODUCT_TO_CART, method = RequestMethod.POST)
	public ResponseEntity<UserCartItem> addProductToUserCart(@PathVariable("emailid") final String userEmailId,
			@RequestBody final UserCartItem userCartProduct) {
		final UserCartItem userCartItem = onlineStoreServices.addProductToUserCart(userEmailId, userCartProduct);
		return new ResponseEntity<UserCartItem>(userCartItem, HttpStatus.CREATED);

	}

	/**
	 * <b>GET Method</b> This method displays the products present in the user
	 * cart..<br/>
	 * <b>REST URL</b>: /rest/user/{emailid}/cart/product
	 * 
	 * @param userEmailId
	 *            : User EmailId - which identifies a unique user in the
	 *            system,for which the products are to be displayed.
	 * @return
	 */
	@RequestMapping(value = OnlineAppRestURIConstants.GET_PRODUCTS_FROM_USER_CART, method = RequestMethod.GET)
	public ResponseEntity<UserCartItemsWrapper> getProductsInUserCart(@PathVariable("emailid") final String userEmailId) {
		UserCartItemsWrapper cartItemsWrapper = onlineStoreServices.getProductsInUserCart(userEmailId);
		return new ResponseEntity<UserCartItemsWrapper>(cartItemsWrapper, HttpStatus.OK);
	}

	/**
	 * <b>PUT Method</b> This method is invoked when a user checkout his cart,
	 * i.e complete his transaction.It updates the user cart of this user as
	 * COMPLETE in DB and output the product wise cost and sales tax and also
	 * returns the total checkout price for the cart.<br/>
	 * 
	 * <b>REST URL</b>: /rest/user/{emailid}/cart/checkout
	 * 
	 * @param userEmailId
	 *            : User EmailId - which identifies a unique user in the
	 *            system,for whom the cart checkout process is triggered.
	 * @return
	 */
	@RequestMapping(value = OnlineAppRestURIConstants.USER_CART_CHECKOUT, method = RequestMethod.POST)
	public ResponseEntity<UserCartItemsWrapper> performCartCheckout(@PathVariable("emailid") final String userEmailId) {
		UserCartItemsWrapper cartItemsWrapper = onlineStoreServices.performCartCheckout(userEmailId);
		if (cartItemsWrapper != null) {
			List<UserCartItem> userCartItems = cartItemsWrapper.getUserCartItems();
			if (!CollectionUtils.isEmpty(userCartItems)) {
				return new ResponseEntity<UserCartItemsWrapper>(cartItemsWrapper, HttpStatus.OK);
			}
		}
		return new ResponseEntity<UserCartItemsWrapper>(cartItemsWrapper, HttpStatus.NOT_MODIFIED);
	}

	/**
	 * <b>GET Method</b> <br/>
	 * <b>REST URL</b>: /rest/productcategories<br/>
	 * This method returns all the pre-existing product categories that exists
	 * in the DB.
	 * 
	 * @return
	 */
	@RequestMapping(value = OnlineAppRestURIConstants.GET_ALL_PRODUCT_CATEGORIES, method = RequestMethod.GET)
	public ProductCategories getAllProductCategories() {
		return onlineStoreServices.findAllProductCategories();
	}

	/**
	 * <b>GET Method</b> <br/>
	 * <b>REST URL</b>: /rest/productcategories/{id}<br/>
	 * This method returns a given product category from DB based on the id sent
	 * in request.
	 * 
	 * @return
	 */
	@RequestMapping(value = OnlineAppRestURIConstants.GET_PRODUCT_CATEGORY, method = RequestMethod.GET)
	public ResponseEntity<ProductCategory> getProductCategory(@PathVariable("id") final String categoryId) {
		final ProductCategory searchedProductCategory = onlineStoreServices.findProductCategory(categoryId);
		return new ResponseEntity<ProductCategory>(searchedProductCategory, HttpStatus.OK);
	}

	/**
	 * <b>GET Method</b> <br/>
	 * <b>REST URL</b>: /rest/products<br/>
	 * This method returns all the pre-existing products that exists in the DB.
	 * 
	 * @return
	 */
	@RequestMapping(value = OnlineAppRestURIConstants.GET_ALL_PRODUCTS, method = RequestMethod.GET)
	public ProductWrapper getAllProducts() {
		return onlineStoreServices.findAllProducts();
	}

	/**
	 * <b>GET Method</b> <br/>
	 * <b>REST URL</b>: /rest/products/{id}<br/>
	 * This method returns a given product from DB based on the id sent in
	 * request.
	 * 
	 * @return
	 */
	@RequestMapping(value = OnlineAppRestURIConstants.GET_PRODUCT, method = RequestMethod.GET)
	public ResponseEntity<Product> getProducts(@PathVariable("productcode") final String productCode) {
		final Product findProduct = onlineStoreServices.findProduct(productCode);
		return new ResponseEntity<Product>(findProduct, HttpStatus.OK);

	}

}