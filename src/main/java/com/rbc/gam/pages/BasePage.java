package com.rbc.gam.pages;

import org.apache.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.rbc.gam.utilis.InitElements;

public class BasePage {
	
	public WebDriver driver;
	public Logger logger;
	public Wait<WebDriver> wait;
	public String app_url;
	
	public BasePage(InitElements b_ie) {
		this.driver = b_ie.getDriver();
		this.logger = b_ie.getLogger();
		this.wait = b_ie.getWait();
		this.app_url = b_ie.getURL();
	}
	
	public void waitForElement(WebElement el) throws Exception {
		try {
			wait.until(ExpectedConditions.visibilityOf(el));
			wait.until(ExpectedConditions.elementToBeClickable(el));
			logger.info("Element visible and clickble");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("Exception in finding visiblity of element"+ e.getMessage());
			throw new Exception();
		}
	}
	
	public void waitForElementToLoad(By el) throws Exception {
		try {

			WebElement Element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(el));
			logger.info("Element visible and clickble");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("Exception in finding presemce of element"+ e.getMessage());
			throw new Exception();
		}
	}
	
	public void select_drop_down(WebElement el, String value) throws InterruptedException {
		Select dd = new Select(el);
		el.click();
		//Thread.sleep(2000);
		//dd.selectByVisibleText(value);
		el.sendKeys(value);
	}
	
	public void verify_result(String actual, String expected) {
		if(actual.trim().length() > 0) {
			
			if(actual.trim().contains(expected)) {
				
				logger.info("Actual result MATCHED expected: \nActual:"+ actual+ " \nExpected:"+expected);
				Assert.assertTrue(true);
				
			}
			else {
				logger.info("Actual result MIS MATCHED expected: \nActual:"+ actual+ " \nExpected:"+expected);
				Assert.assertTrue(false);
			}
		}
		else {
			logger.info("Actual result empty");
			Assert.assertTrue(false);
		}
	}
	
	// get test data from json and store in list
	
	// store value in DB.

}