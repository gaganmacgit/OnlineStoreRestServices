package com.mediaocean.rest.services.test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.mediaocean.rest.model.OnlineAppRestURIConstants;
import com.mediaocean.rest.model.Product;
import com.mediaocean.rest.model.ProductCategories;
import com.mediaocean.rest.model.ProductCategory;
import com.mediaocean.rest.model.UserCart;
import com.mediaocean.rest.model.UserCartItem;
import com.mediaocean.rest.model.UserCartItemsWrapper;
import com.mediaocean.rest.model.UserVO;

public class OnlineStoreRestServicesTest extends BaseTestClass {

	public static final String SERVER_URI = "http://localhost:8080/onlinestore";

	@Before
	public void init() {
		// Hook for anything that needs to be done at starting
	}

	/**
	 * This test simply verifies that the total number of product categories in
	 * the system are equal to given number.
	 */
	@Test
	public void getAllProductCategiesTest() {
		final RestTemplate restTemplate = new RestTemplate();
		final ProductCategories productCategories = restTemplate.getForObject(SERVER_URI
				+ OnlineAppRestURIConstants.GET_ALL_PRODUCT_CATEGORIES, ProductCategories.class);
		if (productCategories != null) {
			final List<ProductCategory> productCategoriesList = productCategories.getProductCategoriesList();
			Assert.assertEquals(productCategoriesList.size(), 3);
		}
	}

	/**
	 * This test verifies the sales tax for different product categories, based
	 * on the requirements given.
	 */
	@Test
	public void verifyProductCategiesSalesTaxTest() {
		final RestTemplate restTemplate = new RestTemplate();
		final ProductCategories productCategories = restTemplate.getForObject(SERVER_URI
				+ OnlineAppRestURIConstants.GET_ALL_PRODUCT_CATEGORIES, ProductCategories.class);
		if (productCategories != null) {
			final List<ProductCategory> productCategoriesList = productCategories.getProductCategoriesList();
			if (CollectionUtils.isEmpty(productCategoriesList)) {
				for (final ProductCategory productCategory : productCategoriesList) {
					if ("A".equalsIgnoreCase(productCategory.getCategoryCode())) {
						Assert.assertEquals(productCategory.getSalesTax(), new BigDecimal(10));
					} else if ("B".equalsIgnoreCase(productCategory.getCategoryCode())) {
						Assert.assertEquals(productCategory.getSalesTax(), new BigDecimal(20));
					} else if ("C".equalsIgnoreCase(productCategory.getCategoryCode())) {
						Assert.assertEquals(productCategory.getSalesTax(), new BigDecimal(0));
					}
				}
			}

		}
	}

	/**
	 * This test verifies the following functionalities.<br/>
	 * <li>A valid user cart object(Not NULL) is created for given user.</li>
	 * <li>The cart created has initial status of INPROCESS.</li> <li>The cart
	 * created has the username and useremail same as requesting user's username
	 * ans email for which cart is created.
	 */
	@Test
	public void verifyEmptyCartCreationTest() {
		final RestTemplate restTemplate = new RestTemplate();

		// Creating a dummy user object for which empty cart has to be
		// created.
		final UserVO userVO = new UserVO();
		userVO.setUserEmail("dummyuser@gmail.com");
		userVO.setUserName("dummyuser");

		final UserCart userCart = restTemplate.postForObject(SERVER_URI + OnlineAppRestURIConstants.CREATE_CART,
				userVO, UserCart.class);

		Assert.assertNotNull(userCart);
		Assert.assertEquals(userCart.getStatus(), "INPROCESS");
		Assert.assertEquals(userCart.getUserEmail(), userVO.getUserEmail());
		Assert.assertEquals(userCart.getUserName(), userVO.getUserName());

	}

	/**
	 * This method test the following functionalities<br/>
	 * <li>Add the product to the user cart and verifies that the response
	 * returned (usercart) is not NULL.</li> <li>The UserCartItem returned from
	 * DB has the same ProductCode and Categories which the user added in the
	 * cart. <li>Verifies that the total price for this item matches the formula
	 * : unit price of product*quantity requested
	 * 
	 */
	@Test
	public void addProductToCartTest() {

		final RestTemplate restTemplate = new RestTemplate();

		// 1. Create a UserCartItem object for a given UserCart and post it to
		// the URL.
		// 2. Verify the number of items present in the cart for a given user.

		/* Preparing the USerCartItem to be posted */
		final UserCartItem cartItem = new UserCartItem();
		cartItem.setProductCategory("A");
		cartItem.setProductCode("PC001");
		cartItem.setProductQuantity(2);

		/* Preparing the useremailId param to be sent as PathVariable */
		final Map<String, String> addPructToCartParams = new HashMap<String, String>();
		addPructToCartParams.put("emailid", "dummyuser@gmail.com");
		final UserCartItem userCartItem = restTemplate.postForObject(SERVER_URI
				+ OnlineAppRestURIConstants.ADD_PRODUCT_TO_CART, cartItem, UserCartItem.class, addPructToCartParams);

		Assert.assertNotNull(userCartItem);
		Assert.assertEquals(userCartItem.getProductCategory(), cartItem.getProductCategory());
		Assert.assertEquals(userCartItem.getProductCode(), cartItem.getProductCode());

		/* Getting the product price from DB for the given product code */
		final Map<String, String> getProductParams = new HashMap<String, String>();
		getProductParams.put("productcode", cartItem.getProductCode());
		final Product product = restTemplate.getForObject(SERVER_URI + OnlineAppRestURIConstants.GET_PRODUCT,
				Product.class, getProductParams);
		if (product != null) {
			final BigDecimal productUnitPrice = product.getPrice();

			Assert.assertEquals(productUnitPrice.multiply(new BigDecimal(cartItem.getProductQuantity())),
					userCartItem.getPrice());

		}

	}

	/**
	 * This method verifies the following functionalities<br/>
	 * <li> The method return should clearly display the itemized billing </li>
	 * 
	 */
	@Test
	public void performCartCheckoutTest() {
		final RestTemplate restTemplate = new RestTemplate();

		final Map<String, String> cartCheckoutParams = new HashMap<String, String>();
		cartCheckoutParams.put("emailid", "dummyuser@gmail.com");
		final UserCartItemsWrapper userCartItemWrapper = restTemplate.postForObject(SERVER_URI
				+ OnlineAppRestURIConstants.USER_CART_CHECKOUT, null,UserCartItemsWrapper.class, cartCheckoutParams);
		System.out.println(userCartItemWrapper);

	}
}
