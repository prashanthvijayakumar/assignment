package com.rbc.gam.scenarios;

import java.time.Duration;
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

	String app_url = "http://store.demoqa.com";
	
	Integer page_timeout = 120;
	Integer implicit_timeout = 60;

	
	Duration element_wait = Duration.ofSeconds(60);
	Duration element_poll = Duration.ofSeconds(2);
	
	
	public static Logger logger = Logger.getLogger(InitTest.class.getName());
	WebDriver driver;
	
	Wait<WebDriver> wait; 
	
	InitElements init_elements;
	

	
	@BeforeClass
	public void init() {
		
		PropertyConfigurator.configure(".\\properties\\log4j.properties");
		logger.info("Beginning init function. \n Initializing driver and wait times.");
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver_win32\\chromedriver.exe");
		
		driver = new ChromeDriver();
		//driver.manage().timeouts().implicitlyWait(implicit_timeout, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(page_timeout, TimeUnit.SECONDS);
	
		wait = new FluentWait<WebDriver>(driver).withTimeout(element_wait).pollingEvery(element_poll);
		
		init_elements = new InitElements(driver,logger, wait, app_url);
		
		driver.get(app_url);
		driver.manage().window().maximize();
	}
	
	@AfterClass
	public void endtest() {
		
		logger.info("End all classes.");
		driver.close();
		driver.quit();
	}
}
