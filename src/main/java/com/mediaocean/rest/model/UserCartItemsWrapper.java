package com.mediaocean.rest.model;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.util.CollectionUtils;

@XmlRootElement(name = "usercart")
public class UserCartItemsWrapper {

	private List<UserCartItem> userCartItems;
	private BigDecimal totalCheckoutPrice;

	public List<UserCartItem> getUserCartItems() {
		return userCartItems;
	}

	public void setUserCartItems(final List<UserCartItem> userCartItems) {
		this.userCartItems = userCartItems;
	}

	public BigDecimal getTotalCheckoutPrice() {
		return totalCheckoutPrice;
	}

	public void setTotalCheckoutPrice(final BigDecimal totalCheckoutPrice) {
		this.totalCheckoutPrice = totalCheckoutPrice;
	}

	@Override
	public String toString() {
		final StringBuilder checkoutCartProductDescriptionBuilder = new StringBuilder();
		if (!CollectionUtils.isEmpty(userCartItems)) {
			for (final UserCartItem userCartItem : userCartItems) {

				checkoutCartProductDescriptionBuilder.append("[Product_Code : ").append(userCartItem.getProductCode())
						.append(" ,Product_Category : ").append(userCartItem.getProductCategory())
						.append(" ,Product_Quantity : ").append(userCartItem.getProductQuantity())
						.append(" ,Product_Price : ").append(userCartItem.getPrice()).append(" ,Product_SalesTax : ")
						.append(userCartItem.getProductSalesTax()).append("]\n");

			}
			checkoutCartProductDescriptionBuilder.append("Total Checkout Price : ").append(totalCheckoutPrice);
		}
		return checkoutCartProductDescriptionBuilder.toString();
	}
}