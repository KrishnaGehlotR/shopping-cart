package org.shoppingcart.service;

import org.shoppingcart.dao.ProductDAO;
import org.shoppingcart.model.PaginationResult;
import org.shoppingcart.model.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainService {

	@Autowired
	ProductDAO productDAO;

	public PaginationResult<ProductInfo> getPaginationResult(int page, int maxResult, int maxNavigationPage,
			String likeName) {
		return productDAO.queryProducts(page, maxResult, maxNavigationPage, likeName);
	}

}
