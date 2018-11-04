package com.rbc.gam.scenarios;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.*;

import com.rbc.gam.utilis.InitElements;


public class InitTest {
	
	Properties prop = new Properties();
	InputStream app_details = null;
	
	public static Logger logger = Logger.getLogger(InitTest.class.getName());
	WebDriver driver;
	
	Wait<WebDriver> wait; 
	
	InitElements init_elements;
		
	@BeforeClass
	public void init() {
		
		try {

			app_details = new FileInputStream(".\\properties\\app_details.properties");

			// load a properties file
			prop.load(app_details);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (app_details != null) {
				try {
					app_details.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		Duration element_wait = Duration.ofSeconds(Long.parseLong(prop.getProperty("element_wait")));
		Duration element_poll = Duration.ofSeconds(Long.parseLong(prop.getProperty("element_poll")));
		
		PropertyConfigurator.configure(".\\properties\\log4j.properties");
		logger.info("Beginning init function. \n Initializing driver and wait times.");
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver_win32\\chromedriver.exe");
		
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(Long.parseLong(prop.getProperty("page_timeout")), TimeUnit.SECONDS);
	
		wait = new FluentWait<WebDriver>(driver).withTimeout(element_wait).pollingEvery(element_poll);
		
		init_elements = new InitElements(driver,logger, wait, prop.getProperty("app_url"));
		
		driver.get(prop.getProperty("app_url"));
		driver.manage().window().maximize();
	}
	
	@AfterClass
	public void endtest() {
		
		logger.info("End all classes.");
		driver.close();
		driver.quit();
	}
}
