package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class SearchTest extends BaseTest{
	
	@BeforeClass
	public void searchSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));;
	}
	
	@Test
	public void searchTest() {
		searchResultPage = accPage.doSearch("macbook");
		prductInfoPage = searchResultPage.selectProduct("MacBook Pro");
		String actualHeader = prductInfoPage.getProductHeader();
		Assert.assertEquals(actualHeader, "MacBook Pro");
	}

}
