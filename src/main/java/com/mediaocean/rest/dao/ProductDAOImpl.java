package com.mediaocean.rest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.mediaocean.rest.model.Product;
import com.mediaocean.rest.model.ProductWrapper;

@Repository("productDAO")
public class ProductDAOImpl implements ProductDAO<Product> {

	@PersistenceContext(name = "testingSetup")
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Product findProduct(String productCode) {
		final String sql = "SELECT p FROM Product p where productCode=:productCode ";

		final Query createQuery = entityManager.createQuery(sql);
		createQuery.setParameter("productCode", productCode);

		@SuppressWarnings("unchecked")
		final List<Product> productList = createQuery.getResultList();

		return productList != null ? productList.get(0) : null;
	}

	@Override
	public ProductWrapper findAllProducts() {
		final String sql = "SELECT p FROM Product p";

		final Query createQuery = entityManager.createQuery(sql);

		@SuppressWarnings("unchecked")
		final List<Product> productList = createQuery.getResultList();
		ProductWrapper productWrapper = new ProductWrapper();
		productWrapper.setProductsList(productList);

		return productWrapper;

	}

}
