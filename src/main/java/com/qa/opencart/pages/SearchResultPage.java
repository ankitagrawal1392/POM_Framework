package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {
	
	private WebDriver driver;
	ElementUtil eleUtil;
	
	public SearchResultPage(WebDriver driver){
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private final By serachResults = By.cssSelector("div#content img");
	private final By resultHeader = By.cssSelector("div#content h1");
 

	public int getSearchResultsCount() {
		int resultCount = eleUtil.waitForElementsVisible(serachResults,AppConstants.DEFAULT_MEDIUM_WAIT).size();
		System.out.println("results cont" + resultCount );
		return resultCount;
	}
	
	public String  getResultsHeaderValue() {
		String header = eleUtil.doElementGetText(resultHeader);
		System.out.println("Results header" + header);
		return header;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		By productValue = By.linkText(productName);
		eleUtil.doClick(productValue);
		return new ProductInfoPage(driver);
	}
	
	
	
}
