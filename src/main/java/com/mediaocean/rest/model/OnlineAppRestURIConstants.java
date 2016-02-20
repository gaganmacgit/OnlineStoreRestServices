package com.mediaocean.rest.model;
public class OnlineAppRestURIConstants {
 
    public static final String GET_ALL_PRODUCT_CATEGORIES = "/rest/productcategories";
    public static final String GET_PRODUCT_CATEGORY = "/rest/productcategories/{id}";
    public static final String GET_ALL_PRODUCTS = "/rest/products";
    public static final String GET_PRODUCT= "/rest/products/{productcode}";
    public static final String CREATE_CART = "/rest/user/cart";
    public static final String ADD_PRODUCT_TO_CART = "/rest/user/{emailid}/cart/product";
    public static final String GET_PRODUCTS_FROM_USER_CART = "/rest/user/{emailid}/cart/product";
    public static final String USER_CART_CHECKOUT = "/rest/user/{emailid}/cart/checkout";


}