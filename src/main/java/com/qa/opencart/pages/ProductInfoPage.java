package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
		ElementUtil eleUtil;
		
		public Map<String,String> productMap;
		
		public ProductInfoPage(WebDriver driver){
			this.driver = driver;
			eleUtil = new ElementUtil(driver);
		}

		
		private final By headers = By.tagName("h1");
		private final By productImages = By.cssSelector("ul.thumbnails img");
		private final By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
		private final By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
		
		public String getProductHeader() {
			String headerValue = eleUtil.waitForElementPresence(headers,AppConstants.DEFAULT_MEDIUM_WAIT).getText();
			System.out.println("Header Value" + headerValue );
			return headerValue;
		}
		
		public int getProductImages() {
			int imagesCount = 
					eleUtil.waitForElementsVisible(productImages, AppConstants.DEFAULT_MEDIUM_WAIT).size();
			System.out.println("Total number of images : "+ imagesCount);
			return imagesCount;
		}
		
		
		
		public Map<String,String> getProductData() {
			//productMap = new HashMap<String,String>();
			productMap = new LinkedHashMap<String,String>();
			//productMap = new TreeMap<String,String>();
			
			productMap.put("productname", getProductHeader());
			productMap.put("productimages",String.valueOf(getProductImages()));
			
			getProductMetaData();
			getProductPriceData();
			
			System.out.println("Product Data : \n" + productMap);
			
			return productMap;
			
			
		}
		
		
		private void getProductMetaData() {
			List <WebElement> metaList = eleUtil.waitForElementsVisible(productMetaData,AppConstants.DEFAULT_MEDIUM_WAIT);
			
			System.out.println("Total meta data  "+ metaList.size());
			
			
			
			for(WebElement e : metaList ) {
				String metaData = e.getText();
				String meta[] = metaData.split(":");
				String metakey = meta[0].trim();
				String metaValue = meta[1].trim();
				productMap.put(metakey, metaValue);
				
			}
					
		}
		
		private void getProductPriceData() {
			List<WebElement> priceList = eleUtil.waitForElementsVisible(productPriceData,AppConstants.DEFAULT_MEDIUM_WAIT);
			
			System.out.println("Total Price Data   " + priceList.size());
			
			String priceValue = priceList.get(0).getText();
			String exTaxValue = priceList.get(1).getText().split(":")[1].trim();
			
			productMap.put("productprice", priceValue);
			productMap.put("extaxprice", exTaxValue);
			
			
			
					
		}
}
