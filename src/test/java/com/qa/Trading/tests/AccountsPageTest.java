package com.qa.Trading.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static com.qa.Trading.constants.AppConstants.*;

import java.util.List;

import com.qa.Trading.Base.BaseTest;

public class AccountsPageTest extends BaseTest{

	@BeforeClass
	public void accPageSetUp() {
		acctPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void accPageTitleTest() {
		Assert.assertEquals(acctPage.getAccPageTitle(), ACCOUNT_PAGE_TITLE);
	}
	
	@Test
	public void accPageUrlTest() {
		Assert.assertTrue(acctPage.getAccPageUrl().contains(ACCOUNT_PAGE_FRACTION_URL));
	}
	
	@Test
	public void accPageHeadersTest() {
		List<String> actHeadersList = acctPage.getAccPageHeaders();
		Assert.assertEquals(actHeadersList, expHeadersList);
	}
	
	
}
