package org.shoppingcart.controller;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.shoppingcart.dao.ProductDAO;
import org.shoppingcart.entity.Product;
import org.shoppingcart.model.CartInfo;
import org.shoppingcart.model.CustomerInfo;
import org.shoppingcart.model.PaginationResult;
import org.shoppingcart.model.ProductInfo;
import org.shoppingcart.service.MainService;
import org.shoppingcart.util.Utils;
import org.shoppingcart.validator.CustomerInfoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
// Enable Hibernate Transaction.
@Transactional
// Need to use RedirectAttributes
@EnableWebMvc
public class MainController {

	@Autowired
	ProductDAO productDAO;

	@Autowired
	private CustomerInfoValidator customerInfoValidator;

	@InitBinder
	public void myInitBinder(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();

		if (target == null) {
			return;
		}

		// For Cart Form.
		// @ModelAttribute("cartForm") @Validated CartInfo cartForm
		if (target.getClass() == CartInfo.class) {

		}
		// For Customer Form.
		// @ModelAttribute("customerForm") @Validated CustomerInfo customerForm
		else if (target.getClass() == CustomerInfo.class) {
			dataBinder.setValidator(customerInfoValidator);
		}
	}

	@RequestMapping("/403")
	public String accessDenied() {
		return "/403";
	}

	@RequestMapping("/")
	public String home() {
		return "index";
	}

	// Product List Page
	@RequestMapping({ "/productList" })
	public String listProductHandler(Model model, @RequestParam(value = "name", defaultValue = "") String likeName,
			@RequestParam(name = "page", defaultValue = "1") int page) {
		final int maxResult = 5;
		final int maxNavigationPage = 10;

		PaginationResult<ProductInfo> result = productDAO.queryProducts(page, maxResult, maxNavigationPage,
				likeName);
		model.addAttribute("paginationProducts", result);
		return "productList";
	}

	// Buy Product
	@RequestMapping({ "/buyProduct" })
	public String listProductHandler(HttpServletRequest request, Model model,
			@RequestParam(value = "code", defaultValue = "") String code) {

		Product product = null;
		if (code != null && code.length() > 0) {
			product = productDAO.findProduct(code);
		}

		if (product != null) {
			// Cart info stored in Session
			CartInfo cartInfo = Utils.getCartInSession(request);
			ProductInfo productInfo = new ProductInfo(product);
			cartInfo.addProduct(productInfo, 1);
		}

		// Redirect to ShoppigCart Page
		return "redirect:shoppingCart";
	}
	
	
}