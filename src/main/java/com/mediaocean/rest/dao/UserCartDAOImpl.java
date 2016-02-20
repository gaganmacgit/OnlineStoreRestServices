package com.mediaocean.rest.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.mediaocean.rest.model.Product;
import com.mediaocean.rest.model.UserCart;
import com.mediaocean.rest.model.UserCartItem;
import com.mediaocean.rest.model.UserCartItemsWrapper;
import com.mediaocean.rest.model.UserVO;

@Repository("userCartDAO")
public class UserCartDAOImpl implements UserCartDAO<UserCart> {

	@PersistenceContext(name = "testingSetup")
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Autowired
	private ProductDAO<Product> productDAO;

	@Override
	public UserCart createUserCart(final UserVO userObject) {

		UserCart cart = null;
		final String findExistingCartQuery = "select uc from UserCart uc where uc.status=:cartStatus and uc.userEmail=:emailId";
		final Query createQuery = entityManager.createQuery(findExistingCartQuery);

		createQuery.setParameter("cartStatus", "INPROCESS");
		createQuery.setParameter("emailId", userObject.getUserEmail());

		final List resultList = createQuery.getResultList();

		if (CollectionUtils.isEmpty(resultList)) {
			// we need to create an empty cart for the user
			cart = new UserCart();
			cart.setStatus("INPROCESS");
			cart.setUserName(userObject.getUserName());
			cart.setUserEmail(userObject.getUserEmail());
			cart.setCreatedDate(new Date());
			cart.setUpdatedDate(new Date());

			entityManager.persist(cart);

		} else {
			cart = (UserCart) resultList.get(0);
		}
		return cart;
	}

	@Override
	public UserCartItem addProductToUserCart(final String userEmailId, final UserCartItem userCartProduct) {
		// 1. Pull the Pending user cart for a given user based on user's email
		// id.
		// 2. In that cart add the Product.
		UserCart cart = null;
		final String findExistingCartQuery = "select uc from UserCart uc where uc.status=:cartStatus and uc.userEmail=:emailId";
		final Query createQuery = entityManager.createQuery(findExistingCartQuery);

		createQuery.setParameter("cartStatus", "INPROCESS");
		createQuery.setParameter("emailId", userEmailId);

		@SuppressWarnings("rawtypes")
		final List resultList = createQuery.getResultList();
		if (!CollectionUtils.isEmpty(resultList)) {
			cart = (UserCart) resultList.get(0);
			Hibernate.initialize(cart.getCartItems());
			cart.getCartItems().add(userCartProduct);
			userCartProduct.setUserCart(cart);
			setProductPriceAndSalesTax(userCartProduct);
			entityManager.merge(cart);
		}
		return userCartProduct;
	}

	/**
	 * Method is responsible for setting <li>The Product price based on quantity
	 * </li> <li>The sales tax on product based on product category</li>
	 * 
	 * @param userCartProduct
	 */
	private void setProductPriceAndSalesTax(final UserCartItem userCartProduct) {
		if (userCartProduct != null) {
			final Integer productQuantity = userCartProduct.getProductQuantity();

			final Product searchedProduct = productDAO.findProduct(userCartProduct.getProductCode());
			if (searchedProduct != null) {
				final BigDecimal productCategorySalesTax = searchedProduct.getCategory().getSalesTax();
				final BigDecimal productUnitPrice = searchedProduct.getPrice();

				// setting the product total price = unit price*quantity
				final BigDecimal productTotalPrice = productUnitPrice.multiply(new BigDecimal(productQuantity));
				userCartProduct.setPrice(productTotalPrice);

				// setting the total sales tax = total price*product category
				// sales tax%
				final BigDecimal salexTax = productTotalPrice.multiply(productCategorySalesTax).divide(new BigDecimal(100));
				userCartProduct.setProductSalesTax(salexTax);
			}
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public UserCartItemsWrapper getProductsInUserCart(final String userEmailId) {
		final String findItemsInUserCart = "select ucitm from UserCartItem ucitm left join ucitm.userCart uc where uc.userEmail=:userEmail and uc.status=:currentstatus";
		final Query userCartItemsQuery = entityManager.createQuery(findItemsInUserCart);
		userCartItemsQuery.setParameter("userEmail", userEmailId);
		userCartItemsQuery.setParameter("currentstatus", "INPROCESS");

		final List resultList = userCartItemsQuery.getResultList();
		final UserCartItemsWrapper cartItemsWrapper = new UserCartItemsWrapper();
		cartItemsWrapper.setUserCartItems(resultList);
		return cartItemsWrapper;
	}

	@Override
	/**
	 * Method performs following tasks
	 *<li> Verify if there are any items in the cart, then update the cart status to complete </li>
	  <li>If there are no items don't update the cart status.</li>
	  <li>In case items are there in cart, calculate the total checkout price.</li>
	 */
	public UserCartItemsWrapper performCartCheckout(final String userEmailId) {
		final UserCartItemsWrapper productsInUserCart = getProductsInUserCart(userEmailId);

		if (productsInUserCart != null) {

			final List<UserCartItem> userCartItems = productsInUserCart.getUserCartItems();
			if (!CollectionUtils.isEmpty(userCartItems)) {
				updateCartStatus(userEmailId, userCartItems);
				final BigDecimal checkoutPrice = calculateTotalCheckoutPrice(userCartItems);
				productsInUserCart.setTotalCheckoutPrice(checkoutPrice);
			}

		}
		return productsInUserCart;
	}

	private BigDecimal calculateTotalCheckoutPrice(final List<UserCartItem> userCartItems) {

		BigDecimal totalCheckoutPrice = new BigDecimal(0);
		for (final UserCartItem userCartItem : userCartItems) {
			totalCheckoutPrice = totalCheckoutPrice.add(userCartItem.getPrice()).add(userCartItem.getProductSalesTax());
		}
		return totalCheckoutPrice;
	}

	private void updateCartStatus(final String userEmailId, final List<UserCartItem> userCartItems) {

		final String updateCartStatusQuery = "update UserCart uc set uc.status=:newstatus where uc.userEmail=:userEmail and uc.status=:currentstatus";
		final Query updateCartQuery = entityManager.createQuery(updateCartStatusQuery);
		updateCartQuery.setParameter("newstatus", "COMPLETED");
		updateCartQuery.setParameter("userEmail", userEmailId);
		updateCartQuery.setParameter("currentstatus", "INPROCESS");
		updateCartQuery.executeUpdate();

	}
}