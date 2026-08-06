package com.qa.Trading.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.Trading.Base.BaseTest;
import static com.qa.Trading.constants.AppConstants.*;

public class ForgottenPasswordPageTest extends BaseTest{

	@BeforeClass
	public void pageSetup() {
		forgottenPwdPage = loginPage.clickForgottenPassword();
	}
	
	@Test
	public void forgotPwdPageTitleTest() {
		String title =forgottenPwdPage.getForgottenPwdPageTitle();
		Assert.assertEquals(title, FORGOTTEN_PASSWORD_PAGE_TITLE);
	}
}
