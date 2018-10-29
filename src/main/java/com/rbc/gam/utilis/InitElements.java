package com.rbc.gam.utilis;

import org.apache.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

public class InitElements {

	String app_url;
	WebDriver driver;
	Logger logger;
	Wait<WebDriver> wait;
	
	public InitElements(WebDriver driver, Logger logger, Wait wait, String app_url) {
		this.driver = driver;
		this.logger = logger;
		this.wait = wait;
		this.app_url = app_url;
	}
	
	public WebDriver getDriver() {
		return this.driver;
	}
	
	public Logger getLogger() {
		return this.logger;
	}

	public Wait<WebDriver> getWait() {
		return this.wait;
	}
	
	public String getURL() {
		return this.app_url;
	}
}
