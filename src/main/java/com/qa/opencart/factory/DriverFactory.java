package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory {
	
	public WebDriver driver;
	public Properties prop;
	public static String highlightEle;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	private static final Logger log = LogManager.getLogger(DriverFactory.class);
	
	public OptionsManager optionsManager;
	
	public WebDriver initDriver(Properties prop) {
		String browserName =  prop.getProperty("browser");
		highlightEle = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);
		//System.out.println("browser name :" + browserName );
		log.info("browser name :" + browserName );
		
		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			//driver = new ChromeDriver();
			tlDriver.set( new ChromeDriver(optionsManager.getChromeOptions()));
			break;
			
		case "edge":
			//driver = new EdgeDriver();
			tlDriver.set( new EdgeDriver(optionsManager.getEdgeOptions()));
			break;
			
		case "firefox":
			//driver = new FirefoxDriver();
			tlDriver.set( new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
			
		case "safari":
			//driver = new SafariDriver();
			tlDriver.set( new SafariDriver());
			break;

		default:
			System.out.println(AppError.INVALID_BROWSER_MESG);
			log.error(AppError.INVALID_BROWSER_MESG);
			throw new FrameworkException("=====INVALID BROWSER");
		}
		
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		
		
		return getDriver();
		
	}
	
	
	
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	public Properties intProp() {
		prop = new Properties();
		try {
			FileInputStream fi = new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(fi);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
	
	
	/**
	 * takescreenshot
	 */

	public static File getScreenshotFile() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp dir
		return srcFile;
	}

	public static byte[] getScreenshotByte() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);// temp dir

	}

	public static String getScreenshotBase64() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);// temp dir

	}
	

}
