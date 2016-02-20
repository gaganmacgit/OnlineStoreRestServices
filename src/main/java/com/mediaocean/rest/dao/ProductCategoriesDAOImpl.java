package com.mediaocean.rest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.mediaocean.rest.model.ProductCategories;
import com.mediaocean.rest.model.ProductCategory;

@Repository("productCategoryDAO")
public class ProductCategoriesDAOImpl implements ProductCategoriesDAO<ProductCategory> {


	@PersistenceContext(name = "testingSetup")
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public ProductCategory findProductCategory(final String productCategory) {

		final String sql = "SELECT pc FROM ProductCategory pc where categoryCode=:productCategory ";

		final Query createQuery = entityManager.createQuery(sql);
		createQuery.setParameter("productCategory", productCategory);

		@SuppressWarnings("unchecked")
		final
		List<ProductCategory> productCategoriesList = createQuery.getResultList();

		return productCategoriesList != null ? productCategoriesList.get(0) : null;
	}

	@Override
	public ProductCategories findAllProductCategories() {

		System.out.println(" entered the method ....");
		final String sql = "SELECT pc FROM ProductCategory  pc";
		final Query createQuery = entityManager.createQuery(sql);

		@SuppressWarnings("unchecked")
		final
		List<ProductCategory> productCategoriesList = createQuery.getResultList();

		ProductCategories  productCategories = new ProductCategories();
		productCategories.setProductCategoriesList(productCategoriesList);
		return productCategories;
	};

}