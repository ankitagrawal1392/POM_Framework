package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class SearchTest extends BaseTest{
	
	@BeforeClass
	public void searchSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));;
	}
	
	@DataProvider
	public Object doSearch() {
		return new Object[][] {
			{"macbook","MacBook Pro"}
					
		};
		
		
	}
	
	@Test(dataProvider = "doSearch")
	public void searchTest(String CompanyName,String ProductName) {
		searchResultPage = accPage.doSearch(CompanyName);
		prductInfoPage = searchResultPage.selectProduct(ProductName);
		String actualHeader = prductInfoPage.getProductHeader();
		Assert.assertEquals(actualHeader, ProductName);
	}

}
