package com.rbc.gam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.rbc.gam.utilis.InitElements;

public class ProductCategoryPage extends BasePage {

	By add_to_cart_btn_2 = By.name("Buy");
	By alert_message = By.xpath("//div[@class='alert addtocart'][@style='display: block;']");
	
	public ProductCategoryPage(InitElements b_ie) {
		super(b_ie);
		// TODO Auto-generated constructor stub
	}
	
	public void select_item_from_page(String item_name) throws Exception {
	
		WebElement selected_item = driver.findElement(By.linkText(item_name));
		waitForElement(selected_item);
		selected_item.click();
		
		logger.info("Item "+ item_name + " selected.");
	}
	
	public void add_to_cart_button() throws Exception {
		
		waitForElement(driver.findElement(add_to_cart_btn_2));
		driver.findElement(add_to_cart_btn_2).click();
		
		logger.info("Clicked Add to cart button.");
	}
	
	public String get_alert_message() throws Exception {
		
		waitForElementToLoad(alert_message);
		
		//waitForElement(driver.findElement(alert_message));
		String result_value = driver.findElement(alert_message).getText();
		logger.info("result message retrieved:\n"+ alert_message);
		return result_value;
	}
	
	public void addItemToCart(String item) {
		try {
			select_item_from_page(item);
			add_to_cart_button();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
