package com.qa.Trading.pages;

import org.openqa.selenium.WebDriver;

import static com.qa.Trading.constants.AppConstants.*;
import com.qa.Trading.utils.ElementUtil;

public class ForgottenPasswordPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public ForgottenPasswordPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getForgottenPwdPageTitle() {
		String title =eleUtil.waitForTitleIs(FORGOTTEN_PASSWORD_PAGE_TITLE, DEFAULT_TIME);
		return title;
	}
}
