package org.shoppingcart.controller;

import javax.transaction.Transactional;

import org.shoppingcart.model.CartInfo;
import org.shoppingcart.model.CustomerInfo;
import org.shoppingcart.model.PaginationResult;
import org.shoppingcart.model.ProductInfo;
import org.shoppingcart.service.MainService;
import org.shoppingcart.validator.CustomerInfoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	private MainService mainService;

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
		
		PaginationResult<ProductInfo> result = mainService.getPaginationResult(page, maxResult, maxNavigationPage, likeName);
		model.addAttribute("paginationProducts", result);
		return "productList";
	}
}