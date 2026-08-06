package com.qa.Trading.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.qa.Trading.constants.AppConstants.*;
import com.qa.Trading.utils.ElementUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private final By resultsProduct = By.cssSelector("div.product-thumb");
	
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String searchResPageTitle() {
		return eleUtil.waitForTitleContains(SEARCH_RESULTS_PAGE_FRACTION_TITLE, DEFAULT_TIME);
	}
	
	public String searchResPageUrl() {
		return eleUtil.waitForURLContains(SEARCH_RESULTS_PAGE_FRACTION_URL, DEFAULT_TIME);
	}
	
	public int getResultsProductCount() {
		int count = eleUtil.waitForAllElementsVisibile(resultsProduct, DEFAULT_TIME).size();
		System.out.println("Total number of search products: "+count);
		return count;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		System.out.println("Product Name Selected On Search Results: "+productName);
		eleUtil.doClick(By.linkText(productName));
		return new ProductInfoPage(driver);
	}
	
}
