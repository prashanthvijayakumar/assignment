package com.rbc.gam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.rbc.gam.utilis.InitElements;

public class HomePage extends BasePage {

	@FindBy(id = "Home")
	private WebElement home_link;

	@FindBy(id = "Product Category")
	private WebElement product_category_link;

	By cart_icon = By.id("header_cart");
	
		
	public HomePage(InitElements b_ie) {
		super(b_ie);
		// TODO Auto-generated constructor stub
	}

	public void click_home_tab() throws Exception {
		waitForElement(home_link);
		home_link.click();
		logger.info("Home tab clicked");
	}
	
	public void click_product_category_tab() throws Exception {
		waitForElement(product_category_link);
		product_category_link.click();
		logger.info("product category clicked");
	}
	
	public void navigate_accessories_tab() {
		driver.get(app_url+ "/products-page/product-category/accessories/");
		logger.info("navigated to accessories.");
	}
	
	public void click_checkout_cart() throws Exception {
		waitForElement(driver.findElement(cart_icon));
		driver.findElement(cart_icon).click();
		logger.info("cart icon clicked");
	}
	
	
}
