package com.rbc.gam.pages;

import org.openqa.selenium.By;

import com.rbc.gam.utilis.InitElements;
import com.rbc.gam.utilis.TestData;

public class CheckOutPage extends BasePage {

	By continue_btn = By.xpath("//span[text()='Continue']");
	By quantity_txt = By.xpath("//input[@name='quantity']");
	By update_btn = By.xpath("//input[@value='Update']");
	
	By email_txt = By.id("wpsc_checkout_form_9");
	By first_name_txt = By.id("wpsc_checkout_form_2");
	By last_name_txt = By.id("wpsc_checkout_form_3");
	By address_txt = By.id("wpsc_checkout_form_4");
	By city_txt = By.id("wpsc_checkout_form_5");

	By province_txt = By.id("wpsc_checkout_form_6");
	By country_dd = By.id("wpsc_checkout_form_7");
	By postal_code_txt = By.id("wpsc_checkout_form_8");
	By phone_txt = By.id("wpsc_checkout_form_18");
	
	By same_as_billing_chk = By.id("shippingSameBilling");
	
	By purchase_btn = By.xpath("//input[@value='Purchase']");
	
	By result_message = By.xpath("//div[@class='wpsc-transaction-results-wrap']");
	

	
	public CheckOutPage(InitElements b_ie) {
		super(b_ie);
		// TODO Auto-generated constructor stub
	}

	public void click_continue_btn() throws Exception {
		
		try {
			waitForElement(driver.findElement(continue_btn));
			click_element(driver.findElement(continue_btn));
			logger.info("Clicked continue button");
		} catch (Exception e) {
			logger.info("Exception in click_continue_btn.");
			throw e;
		}
	}
	
	public void enter_quantity(String value) throws Exception {
		
		waitForElement(driver.findElement(quantity_txt));
		driver.findElement(quantity_txt).clear();
		driver.findElement(quantity_txt).sendKeys(value);
		logger.info("Quantity entered");
		
	}
	
	public String get_quantity() throws Exception {
		
		waitForElementToLoad(quantity_txt);
		logger.info("Value retrieved:"+driver.findElement(quantity_txt).getAttribute("value"));
		return driver.findElement(quantity_txt).getAttribute("value");
	}

	public void click_update_btn() throws Exception {
		waitForElement(driver.findElement(update_btn));
		driver.findElement(update_btn).click();
		logger.info("Clicked update button");
	}
	
	public void enter_email_address(String email) throws Exception {
		waitForElement(driver.findElement(email_txt));
		driver.findElement(email_txt).sendKeys(email);
		logger.info("Email entered");
	}
	
	public void enter_first_name(String first_name) throws Exception {
		waitForElement(driver.findElement(first_name_txt));
		driver.findElement(first_name_txt).sendKeys(first_name);
		logger.info("First name entered");
	}
	
	public void enter_last_name(String last_name) throws Exception {
		waitForElement(driver.findElement(last_name_txt));
		driver.findElement(last_name_txt).sendKeys(last_name);
		logger.info("Last name entered");
	}
	
	public void enter_address(String address) throws Exception {
		waitForElement(driver.findElement(address_txt));
		driver.findElement(address_txt).sendKeys(address);
		logger.info("Address entered");
	}
	
	public void enter_city(String city) throws Exception {
		waitForElement(driver.findElement(city_txt));
		driver.findElement(city_txt).sendKeys(city);
		logger.info("City entered");
	}
	
	public void enter_province(String province) throws Exception {
		waitForElement(driver.findElement(province_txt));
		driver.findElement(province_txt).sendKeys(province);
		logger.info("Province entered");
	}
	
	public void select_country(String value) throws Exception {
		//waitForElement(driver.findElement(country_dd));
		select_drop_down(driver.findElement(country_dd), value);
		logger.info("Country selected");
	}
	
	public void enter_postal_code(String postal_code) throws Exception {
		waitForElement(driver.findElement(postal_code_txt));
		driver.findElement(postal_code_txt).sendKeys(postal_code);
		logger.info("Postal code entered");
	}
	
	public void enter_phone_number(String phone_number) throws Exception {
		waitForElement(driver.findElement(phone_txt));
		driver.findElement(phone_txt).sendKeys(phone_number);
		logger.info("Phone number entered");
	}
	
	public void select_same_as_billing_address_checkbox() throws Exception {
		waitForElement(driver.findElement(same_as_billing_chk));
		if(!driver.findElement(same_as_billing_chk).isSelected()) {
			driver.findElement(same_as_billing_chk).click();
		}
		logger.info("Same as billing address checkbox selected.");
	}
	
	public void click_purchase_button() throws Exception {
		waitForElement(driver.findElement(purchase_btn));
		driver.findElement(purchase_btn).click();
		logger.info("Clicked purchase button");
	}
	
	public String get_result_message() throws Exception {
		waitForElement(driver.findElement(result_message));
		String result_value = driver.findElement(result_message).getText();
		logger.info("result message retrieved:\n"+ result_value);
		return result_value;
	}
	
	public void update_quantity(String quantity) throws Exception {
		enter_quantity(quantity);
		click_update_btn();
	}
	
	public void enter_shipment_details_and_purchase(TestData test_data) throws Exception {
		
		try {
			click_continue_btn();
			enter_email_address(test_data.getData().get("email"));
			enter_first_name(test_data.getData().get("first_name"));
			enter_last_name(test_data.getData().get("last_name"));
			enter_address(test_data.getData().get("address"));
			enter_city(test_data.getData().get("city"));
			enter_province(test_data.getData().get("province"));
			select_country(test_data.getData().get("country"));
			enter_postal_code(test_data.getData().get("postal_code"));
			enter_phone_number(test_data.getData().get("phone_number"));
			select_same_as_billing_address_checkbox();
			click_purchase_button();
		} catch (Exception e) {
			logger.info("Exception in enter shipment details function.");
			throw e;
		}
	}
	
	public void isPageLoaded() {
		
	}
}
