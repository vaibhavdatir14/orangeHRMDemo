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
import com.orengeHRM.utility.ExtentManager;
import com.orengeHRM.utility.Log;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static Properties prop;
	public static WebDriver driver;
	public static Logger logger;

	@BeforeSuite(groups = {"All", "Smoke", "Regression"})
	public void loadConfig() {

		ExtentManager.setExtent();
		
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

	@Parameters("browser")
	@BeforeMethod(groups = {"All", "Smoke", "Regression"})
	public static void launchBrowser(String browserName) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
//		String browserName = prop.getProperty("browser");		//We use @Parameter to read from properties file

		if (browserName.contains("Chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.contains("FireFox")) {
			driver = new FirefoxDriver();
		} else if (browserName.contains("IE")) {
			driver = new InternetExplorerDriver();
		}

		Action.implicitWait(driver, 10);
		Action.pageLoadTimeOut(driver, 30);

		Log.info("url entered in browser");
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();

		// for logging, in String we write ProjectName to load log4j2.xml
		logger = LogManager.getLogger("OrengeHRM");

	}

	@AfterMethod
	public void tearDown() throws InterruptedException {
		if (driver != null) {
			Thread.sleep(3000);
			Log.info("Browser is clossing");
			driver.quit();
		}
	}
	
	@AfterSuite
	public void afterSuite() {
		ExtentManager.endReport();
	}

}
