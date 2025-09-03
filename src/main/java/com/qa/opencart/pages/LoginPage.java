package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//private By locators: page objects
	private final By emailID = By.id("input-email");
	private final By password = By.id("input-password");
	private final By loginBtn = By.xpath("//input[@value='Login']");
	private final By forgotPwdLink = By.linkText("Forgotten Password");
	private final By header = By.tagName("h2");
	private final By registerLink = By.linkText("Register");
	
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	@Step("getting login page title....")
	public  String getLoginPageTitle() {
		   //String title = driver.getTitle();
		   String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_SHORT_WAIT);
		   System.out.println(title);
		   return title;
	}
	
	@Step("getting login url title....")
	public String getLoginPageURL() {
		   //String URL = driver.getCurrentUrl();
		   String URL = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_FRACTION_URL, AppConstants.DEFAULT_SHORT_WAIT);
		   System.out.println(URL);
		   return URL;	  
	}
	
	@Step("forgot pwd link exist...")
	public boolean isForgetPwdLinkExist() {
		  //return driver.findElement(forgotPwdLink).isDisplayed();
		  return eleUtil.isElementDisplayed(forgotPwdLink);
	}
	
	@Step("page header exist...")
	public boolean isHeaderkExist() {
		//return driver.findElement(header).isDisplayed();
		return eleUtil.isElementDisplayed(header);
	}

	@Step("login with correct username: {0} and password: {1}")
	public AccountsPage doLogin(String appusername,String apppassword) {
		System.out.println("Application crendentials :" + appusername + ":" + apppassword);
//		driver.findElement(emailID).sendKeys(appusername);
//		driver.findElement(password).sendKeys(apppassword);
//		driver.findElement(loginBtn).click();
//		//return driver.getTitle();
		
		eleUtil.waitForElementVisible(emailID, AppConstants.DEFAULT_MEDIUM_WAIT).sendKeys(appusername);
		eleUtil.doSendKeys(password, apppassword);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
			 
	}
	
	@Step("navigating to register page...")
	public RegisterPage nagigateToRegiterPage() {
		
		eleUtil.waitForElementVisible(registerLink,AppConstants.DEFAULT_MEDIUM_WAIT).click();
		return new RegisterPage(driver);
	}
	
	
}
