package com.qa.Trading.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.qa.Trading.constants.AppConstants.*;

import java.util.List;

import com.qa.Trading.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private final By headers = By.cssSelector("div#content>h2");
	private final By searchBox = By.cssSelector("input[name='search']");
	private final By searchButton = By.cssSelector("div#search button");
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getAccPageTitle() {
		String title = eleUtil.waitForTitleIs(ACCOUNT_PAGE_TITLE, DEFAULT_TIME);
		return title;
	}
	
	public String getAccPageUrl() {
		return eleUtil.waitForURLContains(ACCOUNT_PAGE_FRACTION_URL, DEFAULT_TIME);
	}
	
	public List<String> getAccPageHeaders() {
		List<String> headersValList = eleUtil.getElementsTextList(headers);
		System.out.println("Account Page Headers: "+headersValList);
		return headersValList;
	}
	
	public SearchResultsPage doSearch(String search) {
		eleUtil.doSendKeys(searchBox, search);
		eleUtil.doClick(searchButton);
		
		String title = driver.getTitle();
		System.out.println("Search page title: "+title);
		
		return new SearchResultsPage(driver);
	}
}
