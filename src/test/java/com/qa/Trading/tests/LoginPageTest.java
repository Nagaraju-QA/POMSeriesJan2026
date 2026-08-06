package com.qa.Trading.tests;

import org.testng.annotations.Test;
import org.testng.Assert;

import com.qa.Trading.Base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import static com.qa.Trading.constants.AppConstants.*;

@Epic("Epic 100: Design pages for trading application")
@Feature("Fea 121: Open cart login feature")
@Story("Story 141: Implement login page on open cart application")

public class LoginPageTest extends BaseTest{

	@Description("Check open cart login page title")
	@Severity(SeverityLevel.MINOR)
	@Owner("TDIT")
	@Test
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, LOGIN_PAGE_TITLE);
	}
	
	@Description("Checking open cart login page URL")
	@Severity(SeverityLevel.NORMAL)
	@Owner("TDIT")
	@Test
	public void loginPageUrlTest() {
		String actUrl = loginPage.getLoginPageURL();
		Assert.assertTrue(actUrl.contains(LOGIN_PAGE_FRACTION_URL));
	}
	
	@Description("Checking open cart Forgot password link")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("TDIT")
	@Test
	public void forgotLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkText());
	}
	
	@Description("Checking open cart login page with valid credentials")
	@Severity(SeverityLevel.BLOCKER)
	@Owner("TDIT")
	@Test(priority = 100)
	public void dologinTest() {
		
		acctPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(acctPage.getAccPageTitle(), ACCOUNT_PAGE_TITLE);
	}
}
