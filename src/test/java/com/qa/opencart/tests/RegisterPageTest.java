package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.CsvUtil;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtils;

public class RegisterPageTest extends BaseTest {
	
	//BT(chrome+login url) --> BC(move to register page) --> @Test
	
	
	@BeforeClass
	public void goToRegisterPage() {
		registerPage = loginPage.nagigateToRegiterPage();
	}
	
	
	@DataProvider
	public Object[][] getRegData() {
		return new Object[][] {
			{"Ankit","Automation","87878799988","Ankit@123","yes"},
			{"Roshani","Gupta","85874758588","Rosey@123","no"}
		};
	}
	
	
	@DataProvider
	public Object[][] getRegSheetData() {
		return ExcelUtil.getTestData("register");
	}
	
	@DataProvider
	public Object[][] getRegCSVData() {
		return CsvUtil.csvData("register");
	}
	
	
	@Test(dataProvider="getRegCSVData")
	public void registerTest(String fisrtName,String lastName,String telephone,String password,String subscribe) {
		
		Assert.assertTrue(registerPage.userRegister(fisrtName,lastName,StringUtils.getRandomEmail(),telephone,password,subscribe ));
	}

}
