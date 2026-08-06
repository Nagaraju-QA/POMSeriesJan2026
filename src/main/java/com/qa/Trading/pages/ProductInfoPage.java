package com.qa.Trading.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.qa.Trading.constants.AppConstants.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.qa.Trading.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String, String> productMap;

	private static final By productHeader = By.tagName("h1");
	private static final By productImages = By.cssSelector("ul.thumbnails img");
	private static final By productMetaData = By.cssSelector(".col-sm-4>ul:nth-of-type(1)>li");
	private static final By productPriceData = By.cssSelector(".col-sm-4>ul:nth-of-type(2)>li");
	
	private static final By addToCartBtn = By.cssSelector("button#button-cart");
	private static final By succesMsg = By.cssSelector(".alert-success");
	private static final By totalCart = By.cssSelector("#cart-total");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getProductHeader() {
		String header = eleUtil.waitForElementVisibile(productHeader, DEFAULT_TIME).getText();
		System.out.println("Product Header is: " + header);
		return header;
	}

	public int getProductImagesCount() {
		int imgCount = eleUtil.waitForAllElementsVisibile(productImages, DEFAULT_TIME).size();
		System.out.println("Total number of Images: " + imgCount);
		return imgCount;
	}

	private void getProductMetaData() {
		List<WebElement> metaList = eleUtil.waitForAllElementsVisibile(productMetaData, DEFAULT_TIME);
		for (WebElement e : metaList) {
			String metaData = e.getText();
			String[] meta = metaData.split(":");
			String metaKey = meta[0];
			String metaValue = meta[1].trim();
			productMap.put(metaKey, metaValue);
		}
	}

	private void getPriceData() {
		List<WebElement> priceList = eleUtil.waitForAllElementsVisibile(productPriceData, DEFAULT_TIME);
		String productPrice = priceList.get(0).getText();
		String exTaxPrice = priceList.get(1).getText().split(":")[1].trim();
		productMap.put("ProductPrice", productPrice);
		productMap.put("ExTax", exTaxPrice);
	}

	public Map<String, String> getProductDetailsMap() {
		productMap = new LinkedHashMap<String, String>();

		productMap.put("ProductHeader", getProductHeader());
		productMap.put("ProductImages", String.valueOf(getProductImagesCount()));

		getProductMetaData();
		getPriceData();

		System.out.println("Full product details: "+productMap);
		return productMap;
	}
	
	public String addToCart() {
		eleUtil.clickWithWait(addToCartBtn, DEFAULT_TIME);
		String msg = eleUtil.waitForElementVisibile(succesMsg, DEFAULT_TIME).getText();
		return msg;
	}
	
	public String getTotalAddedItems() {
		String total = eleUtil.waitForElementVisibile(totalCart, DEFAULT_TIME).getText();
		return total;
	}
	
}
