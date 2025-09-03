package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("EP-100: Design the Open Cart App Login Page")
@Feature("F-101: design open cart login feature")
@Story("US-50: develop login core features: title, url, user is able to login")
public class LoginPageTest extends BaseTest {
	
	
	
	@Description("login page title test....")
	@Link("")
	@Owner("Ankit Agrawal")
	@Severity(SeverityLevel.MINOR)
	@Test
	public  void loginPageTitleTest() {
		String actualTitle = loginPage.getLoginPageTitle();
		ChainTestListener.log("Login Page Title" + actualTitle);
		Assert.assertEquals(actualTitle, "Account Login");
	}
	
	
	@Description("login page url test....")
	@Owner("Ankit Agrawal")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public  void loginPageURLTest() {
		String actualURL = loginPage.getLoginPageURL();
		ChainTestListener.log("Login page URl" + actualURL);
		Assert.assertTrue(actualURL.contains("account/login"));
	}
	
	@Description("forgot password link exist test....")
	@Owner("Ankit Agrawal")
	@Severity(SeverityLevel.CRITICAL)
	
	@Test
	public void isForgetPassLinkExistTest() {
		  Assert.assertTrue(loginPage.isForgetPwdLinkExist());
	}

	@Description("login page header test....")
	@Owner("Ankit Agrawal")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void isLoginPageHeaderExistTest() {
		Assert.assertTrue(loginPage.isHeaderkExist());
	}
	
	@Description("user is able to login to app with the correct credentials....")
	@Owner("Ankit Agrawal")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = Integer.MAX_VALUE)
	
	public void loginTest() {
		 accPage =  loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		//Assert.assertEquals(actAccPageTitle, "My Account");
		 Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	
	
}
