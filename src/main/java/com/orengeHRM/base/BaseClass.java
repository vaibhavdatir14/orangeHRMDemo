package com.orengeHRM.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;

import com.orengeHRM.actiondriver.Action;


import io.github.bonigarcia.wdm.WebDriverManager;



public class BaseClass {

	public static Properties prop;
	public static WebDriver driver;
	public static Logger logger;
		
			
	@BeforeTest
	public void loadConfig() {

		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "\\Configuration\\config.properties");

			prop.load(fis);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public static void launchBrowser() throws InterruptedException {

				
		WebDriverManager.chromedriver().setup();
		String browserName = prop.getProperty("browser");

		//for logging
		logger = LogManager.getLogger("OrengeHRM");
		
		if (browserName.contains("Chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.contains("FireFox")) {
			driver = new FirefoxDriver();
		} else if (browserName.contains("IE")) {
			driver = new InternetExplorerDriver();
		}

		Action.implicitWait(driver, 10);
		Action.pageLoadTimeOut(driver, 30);

		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
	
	}

	
//	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
