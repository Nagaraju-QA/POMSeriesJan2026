package com.qa.Trading.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.qa.Trading.constants.AppConstants.*;
import com.qa.Trading.Base.BaseTest;

public class SearchResultsPageTest extends BaseTest{

	@BeforeClass
	public void searchResultsPageSetup() {
		acctPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority = 3)
	public void searchResPageTitleTest() {
		String actTitle = searchResPage.searchResPageTitle();
		Assert.assertTrue(actTitle.contains(SEARCH_RESULTS_PAGE_FRACTION_TITLE));
	}
	
	@Test(priority = 2)
	public void searchResPageUrl() {
		String actUrl = searchResPage.searchResPageUrl();
		Assert.assertTrue(actUrl.contains(SEARCH_RESULTS_PAGE_FRACTION_URL));
	}
	
	@Test(priority = 1)
	public void getResultsProductCountTest() {
		searchResPage = acctPage.doSearch("macbook");
		int actualResultsCount = searchResPage.getResultsProductCount();
		Assert.assertEquals(actualResultsCount, 3);
	}
}
